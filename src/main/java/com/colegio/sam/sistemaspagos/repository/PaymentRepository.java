package com.colegio.sam.sistemaspagos.repository;

import com.colegio.sam.sistemaspagos.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
