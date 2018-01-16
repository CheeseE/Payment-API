package com.form3.coding.exercise.paymentapi.web.resource;

import com.form3.coding.exercise.paymentapi.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by Attila on 14/01/2018.
 */
@Getter
@AllArgsConstructor
public class PaymentCollectionResource extends ResourceSupport{
    private final List<Payment> data;
}
