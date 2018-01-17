package com.form3.coding.exercise.paymentapi.model;


import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by Attila on 14/01/2018.
 */
@Data
@Embeddable
public class Fx {

    private String contractReference;
    private String exchangeRate;
    private String originalAmount;
    private String originalCurrency;

}