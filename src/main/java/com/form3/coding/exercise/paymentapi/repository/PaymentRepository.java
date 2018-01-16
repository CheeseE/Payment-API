package com.form3.coding.exercise.paymentapi.repository;

import com.form3.coding.exercise.paymentapi.model.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Created by Attila on 14/01/2018.
 */
@Repository
public interface PaymentRepository extends CrudRepository<Payment, UUID> {
}