package com.anand.payment.modal;

import com.anand.payment.domain.PaymentMethod;
import com.anand.payment.domain.PaymentOrderStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long amount;
    @Column(nullable = false)
    private PaymentOrderStatus paymentOrderStatus=PaymentOrderStatus.PENDING;
    @Column(nullable = false)
    private PaymentMethod paymentMethod;
    private String paymentLinkId;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long bookingId;
    @Column(nullable = false)
    private Long onlineAppId;
}
