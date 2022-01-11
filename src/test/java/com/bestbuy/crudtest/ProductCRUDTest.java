package com.bestbuy.crudtest;


import com.bestbuy.steps.ProductsSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.bestbuy.utils.TestUtils.getRandomValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

//@RunWith(SerenityRunner.class)
    public class ProductCRUDTest extends TestBase {

    static String name = "Vodafone Mobile"+ TestUtils.getRandomValue();
    static String  type = "i 10" +TestUtils.getRandomValue();
    static String  upc = "Smart"+TestUtils.getRandomValue();
    static double  price = 90.99;
    static String  description = "This is a placeholder request for creating a new product.";
    static String  model = "N90"+ TestUtils.getRandomValue();
    static long productId;

    @Steps
    ProductsSteps productsSteps;

    @Title("This test will create a new products and verify its generated")
    @Test

    public void test001(){

        productId = productsSteps.createNewProduct(name,type,upc,price,description,model).log().all().statusCode(201).extract().response()
                .body().jsonPath().getLong("id");
        System.out.println("product id is : " + productId);

    }

    @Title("This test will get the product information by ID")
    @Test

    public void test002(){
        productsSteps.getProductById(productId).statusCode(200);

    }

    @Title("This test will update the product information and verify the updated information")
    @Test

    public void test003(){
        name = name+"_Changed";
        price = 90.99;
        upc = upc + "_added";
        productsSteps.updateProduct(productId,name,type,upc,price,description,model).statusCode(200);
        productsSteps.getProductById(productId).body("name",equalTo(name));

    }
    @Title("This test will delete the product information and verify the product is deleted ")
    @Test

    public void test004(){
        productsSteps.deleteProductById(productId).statusCode(200);
        productsSteps.getProductById(productId).statusCode(404);
    }


}