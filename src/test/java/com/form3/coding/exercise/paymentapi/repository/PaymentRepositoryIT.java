package com.form3.coding.exercise.paymentapi.repository;

import com.form3.coding.exercise.paymentapi.model.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by Attila on 14/01/2018.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentRepositoryIT {

    @Autowired
    private PaymentRepository repository;

    @Test
    public void testSave(){
        Payment result = repository.save(new Payment());
        assertNotNull(repository.findOne(result.getId()));
    }


}