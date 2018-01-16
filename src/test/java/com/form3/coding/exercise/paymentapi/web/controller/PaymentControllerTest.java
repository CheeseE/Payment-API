package com.form3.coding.exercise.paymentapi.web.controller;

import com.form3.coding.exercise.paymentapi.exception.NotFoundException;
import com.form3.coding.exercise.paymentapi.model.Payment;
import com.form3.coding.exercise.paymentapi.service.PaymentService;
import com.form3.coding.exercise.paymentapi.web.resource.PaymentResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.hateoas.Link;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Attila on 14/01/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController api;

    @Before
    public void setup() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }

    @Test(expected = NotFoundException.class)
    public void testGetPaymentThrowsException() {
        UUID id = UUID.randomUUID();

        when(paymentService.getById(id)).thenThrow(new NotFoundException(id));
        api.getById(id);
    }

    @Test
    public void testGetPaymentLinks() {
        UUID id = UUID.randomUUID();
        Payment payment = new Payment();
        payment.setId(id);

        when(paymentService.getById(id)).thenReturn(payment);
        PaymentResource response = api.getById(id);
        assertEquals(1, response.getLinks().size());
        Link link = response.getLinks().get(0);

        assertEquals("self", link.getRel());
        assertEquals(String.format("http://localhost/v1/payments/%s", id), link.getHref());
    }
}