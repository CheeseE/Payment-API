package com.form3.coding.exercise.paymentapi.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Money {
    private String amount;
    private String currency;
}