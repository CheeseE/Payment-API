package com.form3.coding.exercise.paymentapi.web.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Created by Attila on 14/01/2018.
 */
public class PaymentControllerIT {

    @Test
    public void verifyWeGetEmptyResponse(){
        RestAssured.
                when().get("http://services.groupkt.com/country/get/iso2code/US").
                then().assertThat().statusCode(200).
                and().body("RestResponse.result.name", Matchers.is("United States of America"));
    }

}