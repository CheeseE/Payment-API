package com.form3.coding.exercise.paymentapi.web.resource;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.form3.coding.exercise.paymentapi.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Attila on 14/01/2018.
 */
@Getter
@AllArgsConstructor
public class PaymentResource extends ResourceSupport{
    @JsonUnwrapped
    private final Payment payment;
}
