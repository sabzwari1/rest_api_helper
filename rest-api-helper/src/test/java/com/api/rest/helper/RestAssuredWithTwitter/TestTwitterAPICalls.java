/**
 * 
 */
package com.api.rest.helper.RestAssuredWithTwitter;

/**
 * @author sss033
 *
 */

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

public class TestTwitterAPICalls {
	private final String ConsumerKey = "";
	private final String ConsumerSecret = "";
	private final String TokenKey = "";
	private final String TokenSecret = "";

	@Test
	public void testPostStatusUpdate() throws URISyntaxException {
		/**
		 * Sample Call
		 * https://api.twitter.com/1.1/statuses/update.json?status=Maybe%20he%27ll%20finally%20find%20his%20keys.%20%23peterfalk
		 */
		// Building URI for post request and reference from
		// https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-update
		URI uri = new URIBuilder().setScheme("https").setHost("api.twitter.com").setPath("1.1/statuses/update.json")
				.addParameter("status", "Tesing AOuth for twitter API with rest assured libarary").build();

		given()
		.auth()
		.oauth(ConsumerKey, ConsumerSecret, TokenKey, TokenSecret)
		.when()
		.post(uri)
		.then()
		.assertThat()
		.statusCode(HttpStatus.SC_OK);

	}

}
