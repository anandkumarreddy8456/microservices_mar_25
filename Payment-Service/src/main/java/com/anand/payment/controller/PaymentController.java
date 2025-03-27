package com.anand.payment.controller;

import com.anand.payment.domain.PaymentMethod;
import com.anand.payment.modal.PaymentOrder;
import com.anand.payment.payload.BookingDto;
import com.anand.payment.payload.PaymentLinkResponse;
import com.anand.payment.payload.UserDto;
import com.anand.payment.service.PaymentService;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    @PostMapping("/create")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@RequestBody BookingDto bookingDto,
    @RequestParam PaymentMethod payment) throws StripeException, RazorpayException {
        UserDto userDto=new UserDto();
        userDto.setId(1L);
        userDto.setFullName("Anand");
        userDto.setEmail("anand@gmail.com");
        return new ResponseEntity<>(paymentService.createOrder(userDto,bookingDto,payment), HttpStatus.OK);

    }
    @GetMapping("/getPaymentOrderById")
    public  ResponseEntity<PaymentOrder> getPaymentOrderById(@RequestParam String paymentOrderId){
        return new ResponseEntity<>(paymentService.getPaymentOrderByPaymentId(paymentOrderId),HttpStatus.OK);
    }
    @PatchMapping("/proceed")
    public  ResponseEntity<Boolean> proceedPayment(@RequestParam String paymentId,@RequestParam String paymentLinkId) throws RazorpayException {
        PaymentOrder  paymentOrder=paymentService.getPaymentOrderByPaymentId(paymentLinkId);
        return new ResponseEntity<>(paymentService.procedPayment(paymentOrder,paymentId,paymentLinkId),HttpStatus.OK);
    }


}
