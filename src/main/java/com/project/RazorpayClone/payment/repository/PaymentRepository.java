package com.project.RazorpayClone.payment.repository;

import com.project.RazorpayClone.payment.Entity.OrderRecord;
import com.project.RazorpayClone.payment.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findByOrder_Id(OrderRecord order);
}
