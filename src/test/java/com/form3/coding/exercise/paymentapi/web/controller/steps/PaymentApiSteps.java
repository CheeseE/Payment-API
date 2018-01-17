package com.form3.coding.exercise.paymentapi.web.controller.steps;

import com.form3.coding.exercise.paymentapi.model.Payment;
import com.form3.coding.exercise.paymentapi.web.resource.PaymentCollectionResource;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Attila on 14/01/2018.
 */
public class PaymentApiSteps {

    private static final String PATH = "v1/payments/";

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
    public void verifyNotFounndResponse() {
        response.then().assertThat().statusCode(404);
    }

    @Step
    public void verifyNoContentResponse() {
        response.then().assertThat().statusCode(204);
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
    public void verifyContent(String payment, UUID id, String version) throws Exception {
        payment = payment.replaceAll("(?<=\"id\": \").*?(?=\")", id.toString());
        payment = payment.replaceAll("(?<=\"version\": \").*?(?=\")", version);
        JSONCompare.compareJSON(payment, response.body().asString(), new DefaultComparator(JSONCompareMode.LENIENT));
    }

    @Step
    public void updatePayment(String message, UUID id) {
        response = SerenityRest.rest()
                .contentType(ContentType.JSON)
                .body(message)
                .when().put(PATH + id.toString());
    }

    @Step
    public void getPaymentById(UUID id) {
        response = SerenityRest.rest()
                .contentType(ContentType.JSON)
                .when().get(PATH + id.toString());
    }

    @Step
    public void deleteById(UUID id) {
        response = SerenityRest.rest()
                .contentType(ContentType.JSON)
                .when().delete(PATH + id.toString());
    }

}
