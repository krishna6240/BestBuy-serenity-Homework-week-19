package com.bestbuy.crudtest;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

public class GetAllProducts extends TestBase {
    @Test
    public void gettallProducts(){
        SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_PRODUCTS)
                .then()
                .log().all()
                .statusCode(200);
    }
}
