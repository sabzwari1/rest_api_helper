/**
 * 
 */
package com.api.rest.helper.RestAssuredWithSSL;

import org.junit.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestGetWithSSL extends BaseClassSSL {

	@Test
	public void testGet(){
		
		
		String response = given()
		.relaxedHTTPSValidation()
		.accept(ContentType.JSON)
		.when()
		.log().everything()
		.get("/all")
		.thenReturn()
		.asString();
		
		System.out.println(response);
	}
	
	
}
