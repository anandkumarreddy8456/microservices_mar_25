package com.anand.payment.serviceImpl;

import com.anand.payment.Repository.PaymentRepository;
import com.anand.payment.domain.PaymentMethod;
import com.anand.payment.domain.PaymentOrderStatus;
import com.anand.payment.modal.PaymentOrder;
import com.anand.payment.payload.BookingDto;
import com.anand.payment.payload.PaymentLinkResponse;
import com.anand.payment.payload.UserDto;
import com.anand.payment.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository  paymentRepository;
    @Value("stripe.api.secret")
    private String stripeSecretKey;
    @Value("razorpay.api.secret")
    private String razorpayApiKey;
    @Value("razorpay.api.key")
    private String razorpaySecretKey;
    @Override
    public PaymentLinkResponse createOrder(UserDto userDto, BookingDto bookingDto, PaymentMethod paymentMethod) throws RazorpayException, StripeException {
        Long amount= (long) bookingDto.getTotalPrice();
        PaymentOrder paymentOrder=new PaymentOrder();
        paymentOrder.setAmount(amount);
        paymentOrder.setPaymentMethod(paymentMethod);
        paymentOrder.setBookingId(bookingDto.getId());
        paymentOrder.setOnlineAppId(bookingDto.getOnlineAppId());
        PaymentOrder savedOrder=paymentRepository.save(paymentOrder);
        PaymentLinkResponse paymentLinkResponse=new PaymentLinkResponse();
        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
            PaymentLink paymentLink=createRazopayPaymentLink(userDto,savedOrder.getAmount(),savedOrder.getId());
            String paymentUrl=paymentLink.get("");
            String paymentUrlId=paymentLink.get("");
            savedOrder.setPaymentLinkId(paymentUrlId);
            paymentLinkResponse.setGetPayment_link_id(paymentUrlId);
            paymentLinkResponse.setPayment_link_url(paymentUrl);
            paymentRepository.save(savedOrder);
        }else if(paymentMethod.equals(PaymentMethod.STRIVE)){
            String paymentUrl=createStripePaymentLink(userDto,savedOrder.getAmount(), savedOrder.getId());
            paymentLinkResponse.setPayment_link_url(paymentUrl);

        }
        return paymentLinkResponse;
    }

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        PaymentOrder paymentOrder=paymentRepository.findById(id).orElse(null);
        if(paymentOrder==null){
            throw new Exception("payment order not found");
        }
        return paymentOrder;
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String id) {
        return paymentRepository.findByPaymentLinkId(id);
    }

    @Override
    public PaymentLink createRazopayPaymentLink(UserDto userDto, Long amount, Long orderId) throws RazorpayException {
        Long Amount=amount*100;
        RazorpayClient razorpayClient=new RazorpayClient(razorpayApiKey,razorpaySecretKey);
        JSONObject paymentLinkRequest=new JSONObject();
        paymentLinkRequest.put("amount",amount);
        paymentLinkRequest.put("Currency","INR");

        JSONObject customer=new JSONObject();
        customer.put("name",userDto.getFullName());
        customer.put("email",userDto.getEmail());

        JSONObject notifyObject=new JSONObject();
        notifyObject.put("email",true);
        paymentLinkRequest.put("notify",notifyObject);
        paymentLinkRequest.put("customer",customer);
        paymentLinkRequest.put("remainder_enable",true);
        paymentLinkRequest.put("callback_url","http://localhost:3000/payment"+orderId);
        paymentLinkRequest.put("callback_method","get");
        return razorpayClient.paymentLink.create(paymentLinkRequest);
    }

    @Override
    public String createStripePaymentLink(UserDto userDto, Long amount, Long orderId) throws StripeException {
        Stripe.apiKey=stripeSecretKey;
        SessionCreateParams params=SessionCreateParams
                .builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-sucess"+orderId)
                .setCancelUrl("http://localhost:3000/payment/cancel"+orderId)
                .addLineItem(SessionCreateParams.LineItem
                        .builder()
                        .setQuantity(1L)
                        .setPrice(String.valueOf(SessionCreateParams
                                .LineItem
                                .PriceData.builder()
                                .setCurrency("USD")
                                .setUnitAmount(amount*100)
                                .setProductData(SessionCreateParams
                                        .LineItem
                                        .PriceData
                                        .ProductData
                                        .builder()
                                        .setName("Online App Apointment Booking")
                                        .build())
                                .build()))
                        .build())
                .build();

        Session session=Session.create(params);

        return session.getUrl();
    }

    @Override
    public Boolean procedPayment(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {
        if(paymentOrder.getPaymentOrderStatus().equals(PaymentOrderStatus.PENDING)){
            if(paymentOrder.getPaymentMethod().equals(PaymentMethod.RAZORPAY)){
                    RazorpayClient razorpayClient=new RazorpayClient(razorpayApiKey,razorpaySecretKey);
                    Payment payment=razorpayClient.payments.fetch(paymentId);
                    Integer amount=payment.get("amount");
                    String status=payment.get("status");
                    if(status.equals("captured")){
                        paymentOrder.setPaymentOrderStatus(PaymentOrderStatus.SUCCESS);
                        paymentRepository.save(paymentOrder);
                        return true;
                    }
                    return false;
            }else{
                paymentOrder.setPaymentOrderStatus(PaymentOrderStatus.SUCCESS);
                paymentRepository.save(paymentOrder);
                return true;
            }
        }
        return false;
    }
}
