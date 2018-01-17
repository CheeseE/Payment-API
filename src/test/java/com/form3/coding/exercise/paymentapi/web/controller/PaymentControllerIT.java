package com.form3.coding.exercise.paymentapi.web.controller;

import com.form3.coding.exercise.paymentapi.PaymentApiApplication;
import com.form3.coding.exercise.paymentapi.util.ResourceTestUtils;
import com.form3.coding.exercise.paymentapi.web.controller.steps.PaymentApiSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.util.UUID;

/**
 * Created by Attila on 14/01/2018.
 */
@RunWith(SerenityRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = PaymentApiApplication.class)
public class PaymentControllerIT {

    @LocalServerPort
    int port;

    @Steps
    PaymentApiSteps steps;

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Before
    public void setup(){
        steps.init(port);
    }

    @Test
    public void verifyGetEmptyResponse() {
        steps.getAllPayments();
        steps.verifySuccessResponse();
        steps.verifyNumberOfElements(0);
    }

    @Test
    public void verifyCreatePayment() throws Exception {
        String payment = ResourceTestUtils.readFile("json/payment.json");
        steps.createPayment(payment);
        UUID id = steps.verifyPaymentCreated();
        steps.verifyContent(payment, id, "1");

        steps.getAllPayments();
        steps.verifySuccessResponse();
        steps.verifyNumberOfElements(1);
    }

    @Test
    public void verifyUpdatePayment() throws Exception {
        String payment = ResourceTestUtils.readFile("json/payment.json");
        String updatePayment = ResourceTestUtils.readFile("json/payment.json");
        steps.createPayment(payment);
        UUID id = steps.verifyPaymentCreated();

        steps.updatePayment(updatePayment, id);
        steps.verifySuccessResponse();
        steps.getPaymentById(id);
        steps.verifyContent(updatePayment, id, "2");
    }

    @Test
    public void verifyDeletePayment() throws Exception {
        String payment = ResourceTestUtils.readFile("json/payment.json");
        steps.createPayment(payment);
        UUID id = steps.verifyPaymentCreated();
        steps.getPaymentById(id);
        steps.verifySuccessResponse();

        steps.deleteById(id);
        steps.verifyNoContentResponse();

        steps.getPaymentById(id);
        steps.verifyNotFounndResponse();
    }


}