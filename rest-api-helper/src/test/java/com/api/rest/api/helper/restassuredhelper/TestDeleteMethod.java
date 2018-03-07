/**
 * 
 */
package com.api.rest.api.helper.restassuredhelper;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.junit.Ignore;
import org.junit.Test;

import io.restassured.http.ContentType;

public class TestDeleteMethod extends BaseClass {

	@Ignore
	@Test
	public void testDeleteMethod() {

		String id = (int) (1000 * (Math.random())) + "";
		Model_Laptop modelLaptop = new Model_Laptop();
		modelLaptop.setId(id);
		modelLaptop.setBrandName("HP");
		modelLaptop.setLaptopName("Elite Book");
		Features features = new Features();
		features.setFeature(new ArrayList<>(Arrays.asList("i9 8976", "16 GB RAM")));
		modelLaptop.setFeatures(features);
		given().when().accept(ContentType.JSON).log().uri().with().contentType(ContentType.JSON).body(modelLaptop)
				.post("/add").then().assertThat().statusCode(HttpStatus.SC_OK);

		// This is old school approach for Rest assured 1.
		expect().statusCode(HttpStatus.SC_OK).when().delete("/delete/" + id);

	}

	@Test
	public void testDeleteMethodWithNewApproach() {

		String id = (int) (1000 * (Math.random())) + "";
		Model_Laptop modelLaptop = new Model_Laptop();
		modelLaptop.setId(id);
		modelLaptop.setBrandName("HP");
		modelLaptop.setLaptopName("Elite Book");
		Features features = new Features();
		features.setFeature(new ArrayList<>(Arrays.asList("i9 8976", "16 GB RAM")));
		modelLaptop.setFeatures(features);
		given().when().accept(ContentType.JSON).with().contentType(ContentType.JSON).body(modelLaptop).post("/add")
				.then().assertThat().statusCode(HttpStatus.SC_OK).and().assertThat()
				.body("Id", equalTo(Integer.parseInt(id)));
		// This is new approach
		given().when().contentType(ContentType.JSON).delete("/delete/" + id).then().assertThat()
				.statusCode(HttpStatus.SC_OK);

	}

}
