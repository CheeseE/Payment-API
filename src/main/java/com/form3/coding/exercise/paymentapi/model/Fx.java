package com.form3.coding.exercise.paymentapi.model;


import lombok.Data;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

/**
 * Created by Attila on 14/01/2018.
 */
@Data
@Embeddable
public class Fx {

    private String contractReference;
    private BigDecimal exchangeRate;
    private BigDecimal originalAmount;
    private String originalCurrency;

}