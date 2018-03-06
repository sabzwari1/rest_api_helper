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
import java.util.LinkedList;

import org.apache.http.HttpStatus;
import org.junit.Ignore;
import org.junit.Test;
import io.restassured.http.ContentType;

public class TestPostMethod extends BaseClass {
	@Ignore
	@Test
	public void testPost() {
		/**
		 * Given when accept content type as xml with payload type as Json and
		 * when do post then assert that status code is SC_OK and assert that
		 * return body have Laptop id
		 */

		String id = (int) (1000 * (Math.random())) + "";
		String payLoad = "{\"BrandName\": \"LENOVO\"," + "\"Features\": " + "{\"Feature\": " + "[\"i5 8650K\","
				+ "\"8GB RAM\"," + "\"128 SSD\"]}," + "\"Id\": " + id + "," + "\"LaptopName\": \"xps\"}";
		given().accept(ContentType.XML).with().contentType(ContentType.JSON).and().body(payLoad).when().post("/add")
				.then().assertThat().statusCode(HttpStatus.SC_OK).and().body("Laptop.Id", equalTo(id));
	}

	@Ignore
	@Test
	public void testPostNegativeScnario() {
		/**
		 * Given when accept content type as xml without payload type as Json
		 * and when do post then assert that status code is SC_Bad_Request
		 */
		given().accept(ContentType.XML).with().contentType(ContentType.JSON).and().when().post("/add").then()
				.assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);

	}

	@Test
	public void testPostWithObjectMapping() {
		/***
		 * Create Mapping Class Create object of Mapping Class Initialize the
		 * filed value present in mapping class Send the object along with post
		 * request
		 */
		String id = (int) (1000 * (Math.random())) + "";
		Model_Laptop modelLaptop = new Model_Laptop();
		modelLaptop.setBrandName("Microsoft");
		modelLaptop.setId(id);
		modelLaptop.setLaptopName("Surface Pro Book 2");
		Features feature = new Features();
		feature.setFeature(new ArrayList<>(Arrays.asList("i7 8600U", "16 GB RAM", "256 GB SSD", "Intel 620")));
		modelLaptop.setFeatures(feature);

		/**
		 * Given when accept content type as json with payload type as xml and
		 * when do post then assert that status code is SC_OK and assert that
		 * return body have Laptop id
		 */
		//Request send as Json and validating repose as Json
		given()
				// .log()//this line is is going to log the request to console
				// .headers() //this is logging headers it can also be body()
				.accept(ContentType.JSON).with().contentType(ContentType.JSON).body(modelLaptop).post("/add").then()
				.assertThat().statusCode(HttpStatus.SC_OK).and().assertThat().body("Id", equalTo(Integer.parseInt(id)));

		// Request send as XML and validating repose as XML
		given()
				// .log()//this line is is going to log the request to console
				// .headers() //this is logging headers it can also be body()
				.accept(ContentType.XML).with().contentType(ContentType.XML).body(modelLaptop).post("/add").then()
				.assertThat().statusCode(HttpStatus.SC_OK).and().assertThat().body("Laptop.Id", equalTo(id));

	}

}
