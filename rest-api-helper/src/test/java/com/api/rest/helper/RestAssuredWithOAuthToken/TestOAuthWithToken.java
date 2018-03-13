/**
 * 
 */
package com.api.rest.helper.RestAssuredWithOAuthToken;

/**
 * @author sss033
 *
 */

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.apache.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.path.json.JsonPath;

public class TestOAuthWithToken {

	 static String token ;
	
	/***
	 * 
	 * Generating the token with API call in getToken method getting executed before Class, then using this token in test method.
	 */
	
	@BeforeClass
	public static void getToken(){
		
		baseURI="http://coop.apps.knpuniversity.com";
		JsonPath result = given()
				.formParam("client_id", "testOAuthAPICall")
				.formParam("client_secret", "d5fa6ebb18e8335523b20db7fc27531b")
				.formParam("grant_type", "client_credentials")
				//.formParam("redirect_uri ", arg1)
				//.formParam("code ", arg1)
		.when()
		.post("/token")
		.thenReturn()
		.jsonPath();
		token = result.getString("access_token");
	}
		
	@Test
	public void testGetEggsCollect(){
		given()
		.auth()
		.oauth2(token)
		.when()
		.post("/api/2418/eggs-count")
		.then()
		.assertThat().body("action", equalTo("eggs-count"));
	}
	
	@Test
	public void testGetEggsCount(){
		given()
		.auth()
		.oauth2(token)
		.when()
		.post("/api/2418/eggs-collect")
		.then()
		.assertThat().body("action", equalTo("eggs-collect"));
	}
	@Test
	public void testGetChickensFeed(){
		given()
		.auth()
		.oauth2(token)
		.when()
		.post("/api/2418/chickens-feed")
		.then()
		.assertThat().body("action", equalTo("chickens-feed"));
	}
	@Test
	public void testGetinSufficientScope(){
		given()
		.auth()
		.oauth2(token)
		.when()
		.post("/api/2418/barn-unlock")
		.then()
		.assertThat().body("error", equalTo("insufficient_scope"));
	}
}
