/**
 * 
 */
package com.api.rest.helper.RestAssuredWithAuthentication;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.apache.http.HttpStatus;
import org.junit.Test;

import io.restassured.http.ContentType;

/**
 * @author sss033
 *
 */
public class TestGet_With_Authentication extends BaseClassSecure {

	@Test
	public void testGetWithHeaders() {
		// "YWRtaW46d2VsY29tZQ==" is base 64 encrypted equalent of
		// "userid:password"
		// Basic is added to it as Authentication type
		// Headers method is used to add this
		given()
				// Authentication will come from base class
				// .header("Authorization", "Basic YWRtaW46d2VsY29tZQ==")
				.accept(ContentType.JSON).when().get("/find/100").then().assertThat().statusCode(HttpStatus.SC_OK).and()
				.assertThat().body("Id", equalTo(100));
	}

	@Test
	public void testGetWithHeadersWithBasicPreemptiveAuthenticaion() {
		// Basic Preemptive method is used to authenticate service call.
		given()
				// Authentication now will come from base class
				/*
				 * .auth() .preemptive() .basic("admin", "welcome")
				 */
				.accept(ContentType.JSON).when().get("/find/300").then().assertThat().statusCode(HttpStatus.SC_OK).and()
				.assertThat().body("Id", equalTo(300));
	}

	@Test
	public void testGetAllWithHeadersWithBasicPreemptiveAuthenticaion() {
		// Basic Preemptive method is used to authenticate service call.
		given()
				// Authentication now will come from base class
				/*
				 * .auth() .preemptive() .basic("admin", "welcome")
				 */
				.accept(ContentType.JSON).when().get("/all").then().assertThat().statusCode(HttpStatus.SC_OK);
	}

}
