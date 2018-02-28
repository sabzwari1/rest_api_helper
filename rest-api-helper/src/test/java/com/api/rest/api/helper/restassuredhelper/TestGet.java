package com.api.rest.api.helper.restassuredhelper;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
//THis is static import by using this we dont need to use RestAssured call name e.g. RestAssured.when() we can directly use when()
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class TestGet {

	@Test
	public void testGet() throws URISyntaxException {
		/***
		 * Given accept the response in json format
		 * When I perform the get request
		 */
		Response response = given()
				.accept(ContentType.JSON)
				.when()
				.get(new URI("http://localhost:8080/laptop-bag/webapi/api/all"));
		System.out.println(response.asString());
	}
	
	public void testStatusCode() throws URISyntaxException {
		int code =given()
		.accept(ContentType.JSON)
		.when()
		.get(new URI("http://localhost:8080/laptop-bag/webapi/api/all"))
		.thenReturn()
		.statusCode();
		Assert.assertEquals(HttpStatus.SC_OK, code);
	}
	
	
	

}
