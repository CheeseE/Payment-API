package com.form3.coding.exercise.paymentapi.service;

import com.form3.coding.exercise.paymentapi.model.Payment;

import java.util.List;
import java.util.UUID;

/**
 * Created by Attila on 14/01/2018.
 */
public interface PaymentService {

    /**
     * Get all payments.
     * @return List of Payment objects
     */
    List<Payment> getAll();
    /**
     * Get payment by id
     * @param id uuid of the payment
     * @return Payment object
     */
    Payment getById(UUID id);

    /**
     * Create payment object
     * @param criteria payment object
     * @return created payment object
     */
    Payment create(Payment criteria);

    /**
     * Update an existing payment
     * @param id uuid of the payment
     * @param criteria payment object
     * @return updated payment object
     */
    Payment update(UUID id, Payment criteria);

    /**
     * Delete a payment object
     * @param id uid of the payment
     */
    void delete(UUID id);
}
