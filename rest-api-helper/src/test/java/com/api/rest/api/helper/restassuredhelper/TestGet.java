package com.api.rest.api.helper.restassuredhelper;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
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

	@Ignore
	@Test
	public void testContent() throws URISyntaxException {
		/***
		 * Given accept the content in json format, when i perform the get
		 * request with id 200 then assert that brand name should be DELL
		 * Verifying multiple things in body e.g. hasItem, hasItems,
		 * equalToIgnoreCase, equalTo, all these things are validated into
		 * single body method
		 */
		given().accept(ContentType.JSON).when().get(new URI("/find/200")).then().assertThat().body("BrandName",
				equalToIgnoringCase("Dell"), "LaptopName", equalToIgnoringCase("xps"), "Id", equalTo(200),
				"Features.Feature", hasItems("512 SSD", "i7 7000U"), "Features.Feature", hasItem("16GB RAM"),
				"Features.Feature", hasSize(3));
	}

	@Ignore
	@Test
	public void testXMLContent() throws URISyntaxException {
		/***
		 * Given accept the content in xml format, when i perform the get
		 * request with id 200 then assert that brand name should be DELL And
		 * the features should have 16GB RAM
		 */
		given().accept(ContentType.XML).when().get(new URI("/find/200")).then().assertThat()
				.body("Laptop.BrandName", equalToIgnoringCase("Dell"), "Laptop.Id", equalTo("200")).and().assertThat()
				.body("Laptop.Features.Feature", hasItem("16GB RAM"));
	}

	@Ignore
	@Test
	public void testXMLContentWithXMLPath() throws URISyntaxException {
		/***
		 * Given accept the content in xml format, when i perform the get
		 * request with id 200 then assert that id is equal to 200, brand name
		 * is equal to DELL And the features should have 16GB RAM
		 */
		String body = given().accept(ContentType.XML).when().get(new URI("/find/200")).thenReturn().asString();
		XmlPath xml = new XmlPath(body);
		Assert.assertEquals(200, xml.getInt("Laptop.Id"));
		Assert.assertEquals("DELL", xml.getString("Laptop.BrandName"));
		Assert.assertTrue(xml.getString("Laptop.Features.Feature").contains("16GB RAM"));
	}

	@Test
	public void testJsonContentWithJSONPath() throws URISyntaxException {
		/***
		 * Given accept the content in xml format, when i perform the get
		 * request with id 200 then assert that id is equal to 200, brand name
		 * is equal to DELL And the features should have 16GB RAM
		 */
		String body = given().accept(ContentType.JSON).when().get(new URI("/find/200")).thenReturn().asString();
		JsonPath json = new JsonPath(body);
		Assert.assertEquals(200, json.getInt("Id"));
		Assert.assertEquals("DELL", json.getString("BrandName"));
		Assert.assertTrue(json.getString("Features.Feature").contains("16GB RAM"));
	}

	// Some good examples:
	/**
	 * 
	 * <?xml version="1.0" encoding="UTF-8" ?> <cars>
	 * <car make="Alfa Romeo" model="Giulia"> <country>Italy</country>
	 * <year>2016</year> </car> <car make="Aston Martin" model="DB11">
	 * <country>UK</country> <year>1949</year> </car>
	 * <car make="Toyota" model="Auris"> <country>Japan</country>
	 * <year>2012</year> </car> </cars>
	 */

	// body("cars.car[0].country", equalTo("Italy"));
	// body("cars.car[-1].year", equalTo("2012")); using the [-1] index (this
	// points us to the last item in a list):
	// body("cars.car[1].@model", equalTo("DB11"));‘@’ notation to get the
	// attribute from node
	// body("cars.car.@make.grep(~/A.*/).size()", equalTo(2));" grep() method
	// (very similar to the Unix command). If we want to check the number of
	// cars in the list whose make starts with an ‘A’, we can do so like
	// body("cars.car.findAll{it.country in ['Italy','UK']}.size()", in operator
	// equalTo(2));
	// body("cars.car.findAll{it.country=='Japan'}.size()", equalTo(1));Find All
	// filter to the country element, and subsequently count the number of items
	// in the list using size():
}
