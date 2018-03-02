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
import org.apache.http.HttpStatus;
import org.junit.Test;
import io.restassured.http.ContentType;

public class TestPostMethod extends BaseClass {
	
	@Test
	public void testPost(){
		/**
		 * Given when accept content type as xml
		 * with payload type as Json
		 * and when do post then
		 * assert that status code is SC_OK and assert that return body have Laptop id 
		 */
		
		String id = (int) (1000 * (Math.random())) + "";
		String payLoad = "{\"BrandName\": \"LENOVO\"," + "\"Features\": " + "{\"Feature\": " + "[\"i5 8650K\","
				+ "\"8GB RAM\"," + "\"128 SSD\"]}," + "\"Id\": " + id + "," + "\"LaptopName\": \"xps\"}";
		given()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.body(payLoad)
		.when()
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK)
		.and()
		.body("Laptop.Id", equalTo(id));
	}
	
	
	@Test
	public void testPostNegativeScnario(){
		/**
		 * Given when accept content type as xml
		 * without payload type as Json
		 * and when do post then
		 * assert that status code is SC_Bad_Request 
		 */
		given()
		.accept(ContentType.XML)
		.with()
		.contentType(ContentType.JSON)
		.and()
		.when()
		.post("/add")
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_BAD_REQUEST);
		
	}
	
	
	
	
}
