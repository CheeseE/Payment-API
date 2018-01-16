package com.form3.coding.exercise.paymentapi.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Attila on 14/01/2018.
 */
@Data
@Embeddable
public class Attributes {

    //TODO: add relationships and expose the new entities to a rest endpoint

    private String amount;
    @Embedded
    private Party beneficiaryParty;
    @Embedded
    private Party debtorParty;
    @Embedded
    private ChargesInformation chargesInformation;
    private String currency;
    private String endToEndReference;
    @Embedded
    private Fx fx;
    private String numericReference;
    private String paymentId;
    private String paymentPurpose;
    private String paymentScheme;
    private String paymentType;
    private String processingDate;
    private String reference;
    private String schemePaymentSubType;
    private String schemePaymentType;
    @Embedded
    private Party sponsorParty;

}