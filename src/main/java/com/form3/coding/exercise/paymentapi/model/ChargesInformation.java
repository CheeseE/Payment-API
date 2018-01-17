package com.form3.coding.exercise.paymentapi.model;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Attila on 14/01/2018.
 */
@Data
@Embeddable
public class ChargesInformation {

    private String bearerCode;
    @ElementCollection
    private List<Money> senderCharges = new ArrayList<>();
    private String receiverChargesAmount;
    private String receiverChargesCurrency;
}
