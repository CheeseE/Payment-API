package com.form3.coding.exercise.paymentapi.service;

import com.form3.coding.exercise.paymentapi.model.Payment;

import java.util.List;
import java.util.UUID;

/**
 * Created by Attila on 14/01/2018.
 */
public interface PaymentService {

    List<Payment> getAll();

    Payment getById(UUID id);

    Payment create(Payment criteria);

    Payment update(UUID id, Payment criteria);

    void delete(UUID id);
}
