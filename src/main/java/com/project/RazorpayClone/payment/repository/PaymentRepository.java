package com.project.RazorpayClone.payment.repository;

import com.project.RazorpayClone.common.enums.PaymentStatus;
import com.project.RazorpayClone.payment.DTO.response.PaymentResponse;
import com.project.RazorpayClone.payment.Entity.OrderRecord;
import com.project.RazorpayClone.payment.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findByOrder_Id(OrderRecord order);

   List<Payment> findByIdAndMerchantId(UUID paymentId, UUID merchantId);

    @Query("select p from Payment p")
    List<Payment> findByStatusAndCreatedAtBefore(PaymentStatus status, LocalDateTime globalWindow);
}
