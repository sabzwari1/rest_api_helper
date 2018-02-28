package com.api.rest.api.helper.restassuredhelper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
//THis is static import by using this we dont need to use RestAssured call name e.g. RestAssured.when() we can directly use when()
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestGet extends BaseClass {
	@Ignore
	@Test
	public void testGet() throws URISyntaxException {
		/***
		 * Given accept the response in json format When I perform the get
		 * request
		 */
		Response response = given().accept(ContentType.JSON).when()
				.get(new URI("http://localhost:8080/laptop-bag/webapi/api/all"));
		System.out.println(response.asString());
	}

	@Ignore
	@Test
	public void testStatusCode() throws URISyntaxException {
		// int code =
		given().accept(ContentType.JSON).when().get(new URI("http://localhost:8080/laptop-bag/webapi/api/all")).then()
				.assertThat().statusCode(HttpStatus.SC_OK);
		/*
		 * //*in order to capture status code and body use .thenreturn() method
		 * other wise use //in order to only validate response use then()
		 * .thenReturn() .statusCode(); Assert.assertEquals(HttpStatus.SC_OK,
		 * code);
		 */
	}

	@Ignore
	@Test
	public void testGetWithId() throws URISyntaxException {
		/***
		 * Given accept the content in json format When I perform the get
		 * request with id 203 Then assertthat status code should be 200 Ok
		 */
		String body = given().accept(ContentType.JSON).when().get(new URI("/find/200")).thenReturn().body().asString();
		System.out.println(body);

	}

	@Ignore
	@Test
	public void testGetWithInvalidId() throws URISyntaxException {
		/***
		 * Given accept the content in json format When I perform the get
		 * request with id 200 Then assertthat status code should be 404 not
		 * found
		 */

		given().accept(ContentType.JSON).when().get(new URI("/find/203")).then().assertThat()
				.statusCode(HttpStatus.SC_NOT_FOUND);

	}

	@Ignore
	@Test
	public void testGetWithIdWithHeaders() throws URISyntaxException {
		/***
		 * Given accept the content in json format When I perform the get
		 * request with custom header and id 200 Then assertthat status code
		 * should be 200 Ok
		 */
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/xml");
		// headers.put("Content-Type", "application/json");
		String body = given().headers(headers).when().get(new URI("/find/200")).thenReturn().body().asString();
		System.out.println(body);

	}

	@Test
	public void test1Content() throws URISyntaxException {
		/***
		 * Given accept the content in json format, when i perform the get
		 * request with id 200 then assert that brand name should be DELL
		 */
		given().accept(ContentType.JSON).when().get(new URI("/find/200")).then().body("BrandName",
				equalToIgnoringCase("Dell"),"LaptopName",equalToIgnoringCase("xps"),"Id",equalTo(200));

	}

}
