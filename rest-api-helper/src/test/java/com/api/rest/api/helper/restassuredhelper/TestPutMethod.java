/**
 * 
 */
package com.api.rest.api.helper.restassuredhelper;

/**
 * @author sss033
 *
 */

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.Assert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class TestPutMethod extends BaseClass {

	@Test
	public void testPutMethod() {
		/**
		 * Give accept the content in Json format with content type as Jason And
		 * Body when I perform the put request with id Then HttpStatus code 200
		 * Ok should be returned And details should get updated
		 */
		// add
		String id = (int) (1000 * (Math.random())) + "";
		Model_Laptop modelLaptop = new Model_Laptop();
		modelLaptop.setBrandName("Lenovo");
		modelLaptop.setId(id);
		modelLaptop.setLaptopName("T61");
		Features features = new Features();
		features.setFeature(new ArrayList<>(Arrays.asList("i7 8600U", "16 GB RAM", "256 GB SSD", "Intel 620")));
		modelLaptop.setFeatures(features);
		given().when().accept(ContentType.JSON).with().contentType(ContentType.JSON).and().body(modelLaptop)
				.post("/add").then().assertThat().statusCode(HttpStatus.SC_OK);
		// update
		features.setFeature(new ArrayList<>(
				Arrays.asList("i7 8600U", "16 GB RAM", "256 GB SSD", "Intel 620", "Updated with put method")));
		modelLaptop.setFeatures(features);
		given().when().accept(ContentType.JSON).with().contentType(ContentType.JSON).and().body(modelLaptop).when()
				.put("/update").then().assertThat().statusCode(HttpStatus.SC_OK).and().assertThat()
				.body("Features.Feature", hasItem("Updated with put method"));
	}

	@Test
	public void testPutMethodWithJsonPath() {
		/**
		 * Give accept the content in Json format with content type as Jason And
		 * Body when I perform the put request with id Then HttpStatus code 200
		 * Ok should be returned And details should get updated
		 */
		// add
		String id = (int) (1000 * (Math.random())) + "";
		Model_Laptop modelLaptop = new Model_Laptop();
		modelLaptop.setBrandName("Lenovo");
		modelLaptop.setId(id);
		modelLaptop.setLaptopName("T61");
		Features features = new Features();
		features.setFeature(new ArrayList<>(Arrays.asList("i7 8600U", "16 GB RAM", "256 GB SSD", "Intel 620")));
		modelLaptop.setFeatures(features);
		given().when().accept(ContentType.JSON).with().contentType(ContentType.JSON).and().body(modelLaptop)
				.post("/add").then().assertThat().statusCode(HttpStatus.SC_OK);
		// update
		features.setFeature(new ArrayList<>(
				Arrays.asList("i7 8600U", "16 GB RAM", "256 GB SSD", "Intel 620", "Updated with put method")));
		modelLaptop.setFeatures(features);
		JsonPath response = given().when().accept(ContentType.JSON).with().contentType(ContentType.JSON).and()
				.body(modelLaptop).when().put("/update").thenReturn().jsonPath();

		Assert.assertEquals(response.getString("BrandName"), "Lenovo");

	}

}
