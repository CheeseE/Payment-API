package com.form3.coding.exercise.paymentapi.web.controller;

import com.form3.coding.exercise.paymentapi.model.Payment;
import com.form3.coding.exercise.paymentapi.service.PaymentService;
import com.form3.coding.exercise.paymentapi.web.resource.PaymentCollectionResource;
import com.form3.coding.exercise.paymentapi.web.resource.PaymentResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Created by Attila on 14/01/2018.
 */
@RestController
@RequestMapping("v1/payments")
@Api(tags = "Payments", description = "Payments API")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping()
    @ApiOperation(value = "Get all the payments")
    @ApiResponse(code = 200, message= "ok", responseContainer="List", response = Payment.class)
    public PaymentCollectionResource findAll() {

        List<Payment> payment = paymentService.getAll();

        return toPaymentResource(payment);
    }

    @GetMapping(("/{id}"))
    @ApiOperation(value = "Get a payment identified by its id")
    @ApiResponse(code = 200, message= "ok", response = Payment.class)
    public PaymentResource getById(@PathVariable UUID id) {

        Payment payment = paymentService.getById(id);

        return toPaymentResource(payment);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Create a payment")
    public PaymentResource create(@Valid @RequestBody Payment criteria) {

        Payment payment = paymentService.create(criteria);

        return toPaymentResource(payment);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a payment identified by its id")
    public PaymentResource update(@PathVariable UUID id, @Valid @RequestBody Payment criteria) {

        Payment payment = paymentService.update(id, criteria);

        return toPaymentResource(payment);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation(value = "Delete a payment identified by its id")
    public void delete(@PathVariable UUID id) {
        paymentService.delete(id);
    }

    private PaymentResource toPaymentResource(Payment payment) {
        PaymentResource resource = new PaymentResource(payment);
        resource.add(linkTo(methodOn(PaymentController.class).getById(payment.getId())).withSelfRel());
        return resource;
    }

    private PaymentCollectionResource toPaymentResource(List<Payment> payments) {
        PaymentCollectionResource resource = new PaymentCollectionResource(payments);
        resource.add(linkTo(methodOn(PaymentController.class).findAll()).withSelfRel());
        return resource;
    }


}