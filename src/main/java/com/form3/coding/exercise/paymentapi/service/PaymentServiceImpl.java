package com.form3.coding.exercise.paymentapi.service;

import com.form3.coding.exercise.paymentapi.exception.NotFoundException;
import com.form3.coding.exercise.paymentapi.model.Payment;
import com.form3.coding.exercise.paymentapi.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Attila on 14/01/2018.
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository repository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Payment getById(UUID id) {
        return Optional.ofNullable(repository.findOne(id)).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public Payment create(Payment criteria) {
        return repository.save(criteria);
    }

    @Override
    public Payment update(UUID id, Payment criteria) {
        Payment payment = getById(id);
        payment.setOrganisationId(criteria.getOrganisationId());
        payment.setAttributes(criteria.getAttributes());
        return repository.save(criteria);
    }

    @Override
    public void delete(UUID id) {
        Payment payment = getById(id);
        repository.delete(payment);
    }
}
