/**
 * 
 */
package com.api.rest.api.helper.restassuredhelper;

import org.apache.http.HttpStatus;
import org.junit.Test;


import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

/**
 * @author sss033
 *
 */
public class TestQueryParameter extends BaseClass {

	@Test
	
	public void testQueryParm() {
		given()
				.accept(ContentType.JSON)
				.param("id", "100")
				.param("laptopName", "xps")
				.when()
				.get("/query")
				.then()
				.assertThat().statusCode(HttpStatus.SC_OK)
				.and()
				.assertThat()
				.body("Id", equalTo(100),"Features.Feature",hasItem("16GB RAM"));
	}
}
