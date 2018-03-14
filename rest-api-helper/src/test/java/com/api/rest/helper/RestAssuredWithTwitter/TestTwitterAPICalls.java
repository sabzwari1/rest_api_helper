/**
 * 
 */
package com.api.rest.helper.RestAssuredWithTwitter;

/**
 * @author sss033
 *
 */

import static io.restassured.RestAssured.*;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Test;

public class TestTwitterAPICalls {
	private final String ConsumerKey = "ihxQ1LvO09M3QuM4jxQSRNUIx";
	private final String ConsumerSecret = "uDoI3cYcyCgV6oEMDnZ4gPJpSE1W9Fj2CIRo7YmSIBi1e8ML02";
	private final String TokenKey = "1126506205-Svmd242cCcPIp9pNP7slNUlB4rjjjR9dqUNXvWw";
	private final String TokenSecret = "4Oe9bdGeCHYRchxU27U14ybGXPqXW41qlQVpALeSzVH05";

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
