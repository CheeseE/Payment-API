package com.form3.coding.exercise.paymentapi.web.controller.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.form3.coding.exercise.paymentapi.PaymentApiApplication;
import com.form3.coding.exercise.paymentapi.model.Payment;
import com.form3.coding.exercise.paymentapi.web.resource.PaymentCollectionResource;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContentAssert;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static io.restassured.RestAssured.port;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Attila on 14/01/2018.
 */
public class PaymentApiSteps {

    private static final String PATH = "v1/payments";

    private Response response;

    public void init(int port) {
        RestAssured.port = port;
    }

    @Step
    public void getAllPayments() {
        response = SerenityRest.rest()
                .contentType(ContentType.JSON)
                .when().get(PATH);
    }

    @Step
    public void verifySuccessResponse() {
        response.then().assertThat().statusCode(200);
    }

    @Step
    public void verifyNumberOfElements(int number) {
        PaymentCollectionResource elements = response.body().as(PaymentCollectionResource.class, ObjectMapperType.GSON);
        assertEquals(number, elements.getData().size());
    }

    @Step
    public void createPayment(String message) {
        response = SerenityRest.rest()
                .contentType(ContentType.JSON)
                .body(message)
                .when().post(PATH);
    }

    @Step
    public UUID verifyPaymentCreated() throws Exception {
        response.then().assertThat().statusCode(201);
        Payment payment = response.body().as(Payment.class, ObjectMapperType.GSON);
        assertNotNull(payment);
        return payment.getId();
    }

    @Step
    public void verifyContent(String payment) {
        new JsonContentAssert(Payment.class, response.body().asString()).isEqualTo(payment);
    }

}
