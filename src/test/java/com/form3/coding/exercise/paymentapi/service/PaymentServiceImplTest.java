package com.form3.coding.exercise.paymentapi.service;

import com.form3.coding.exercise.paymentapi.exception.NotFoundException;
import com.form3.coding.exercise.paymentapi.model.Payment;
import com.form3.coding.exercise.paymentapi.repository.PaymentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Attila on 14/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository repository;

    @InjectMocks
    private PaymentServiceImpl service;
    private UUID id = UUID.randomUUID();

    @Test(expected = NotFoundException.class)
    public void testGetByIdThrowsException() throws Exception {
        when(repository.findOne(id)).thenReturn(null);
        service.getById(id);
    }

    @Test
    public void testUpdate() throws Exception {
        Payment updatePayment = new Payment();
        updatePayment.setOrganisationId("org1");
        when(repository.findOne(id)).thenReturn(new Payment());

        service.update(id, updatePayment);
        verify(repository).save(updatePayment);
    }

    @Test
    public void testCreate() throws Exception {
        Payment payment = new Payment();
        payment.setOrganisationId("org1");

        service.create(payment);
        verify(repository).save(payment);
    }

    @Test
    public void testFindAll() throws Exception{
        when(repository.findAll()).thenReturn(Arrays.asList(new Payment()));
        service.getAll();
        verify(repository).findAll();
    }

    @Test
    public void testDelete() throws Exception{
        Payment payment = new Payment();
        when(repository.findOne(id)).thenReturn(payment);
        service.delete(id);
        verify(repository).delete(payment);
    }

}