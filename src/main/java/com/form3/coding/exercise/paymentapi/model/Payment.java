package com.form3.coding.exercise.paymentapi.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by Attila on 14/01/2018.
 */
@Entity
@Data
public class Payment extends BaseEntity {
    @NotEmpty
    private String type;
    @NotEmpty
    private String organisationId;
    @Embedded
    @NotNull
    private Attributes attributes;

}