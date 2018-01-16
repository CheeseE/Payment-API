package com.form3.coding.exercise.paymentapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Attila on 14/01/2018.
 */
@Data
@Embeddable
public class ChargesInformation {

    private String bearerCode;
    @ElementCollection
    private List<Money> senderCharges;
    private BigDecimal receiverChargesAmount;
    private String receiverChargesCurrency;

    @Getter
    @Setter
    @Embeddable
    public static class Money{
        private BigDecimal amount;
        private String currency;
    }
}
