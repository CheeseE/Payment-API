package com.form3.coding.exercise.paymentapi.model;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 * Created by Attila on 14/01/2018.
 */
@Data
@Embeddable
public class Party {

    private String accountNumber;
    private String accountName;
    private String accountNumberCode;
    private Integer accountType;
    private String address;
    private String bankId;
    private String bankIdCode;
    private String name;

}