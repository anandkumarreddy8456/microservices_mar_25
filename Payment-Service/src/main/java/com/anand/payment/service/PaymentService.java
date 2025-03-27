package com.anand.payment.service;

import com.anand.payment.domain.PaymentMethod;
import com.anand.payment.modal.PaymentOrder;
import com.anand.payment.payload.BookingDto;
import com.anand.payment.payload.PaymentLinkResponse;
import com.anand.payment.payload.UserDto;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {
    PaymentLinkResponse createOrder(UserDto userDto, BookingDto bookingDto, PaymentMethod paymentMethod) throws RazorpayException, StripeException;
    PaymentOrder getPaymentOrderById(Long id) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String id);
    PaymentLink createRazopayPaymentLink(UserDto userDto,Long amount,Long orderId) throws RazorpayException;
    String createStripePaymentLink(UserDto userDto,Long amount,Long orderId) throws StripeException;
    Boolean procedPayment(PaymentOrder paymentOrder,String PaymentId,String paymentLinkId) throws RazorpayException;
}
