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
import org.junit.Ignore;
import org.junit.Test;

import io.restassured.http.ContentType;

public class TestTwitterAPICalls {
	private final String ConsumerKey = "";
	private final String ConsumerSecret = "";
	private final String TokenKey = "";
	private final String TokenSecret = "";

	@Ignore
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
		given().auth().oauth(ConsumerKey, ConsumerSecret, TokenKey, TokenSecret).when().post(uri).then().assertThat()
				.statusCode(HttpStatus.SC_OK);

	}

	@Ignore
	@Test
	public void testGetFollowersList() throws URISyntaxException {
		/**
		 * Sample Call
		 * https://api.twitter.com/1.1/followers/list.json?cursor=-1&screen_name=twitterdev&skip_status=true&include_user_entities=false
		 */
		URI uri = new URIBuilder().setScheme("https").setHost("api.twitter.com").setPath("1.1/followers/list.json")
				.addParameter("screen_name", "NatGeo").addParameter("count", "2").addParameter("skip_status", "true")
				.addParameter("include_user_entities", "false").build();
		String response = given().when().accept(ContentType.JSON).auth()
				.oauth(ConsumerKey, ConsumerSecret, TokenKey, TokenSecret).get(uri).thenReturn().asString();

	}

	@Test
	public void testPostDeleteStatusUpdate() throws URISyntaxException {
		// Creating status update with twitter API
		// Building URI for post request and reference from
		// https://developer.twitter.com/en/docs/tweets/post-and-engage/api-reference/post-statuses-update
		URI uri = new URIBuilder().setScheme("https").setHost("api.twitter.com").setPath("1.1/statuses/update.json")
				.addParameter("status", "Tesing AOuth for twitter API with rest assured libarary" + Math.random())
				.build();

		TwitterModel response = given().auth().oauth(ConsumerKey, ConsumerSecret, TokenKey, TokenSecret).when()
				.post(uri).thenReturn().as(TwitterModel.class);
		// Deleting twitter status update with ID
		/***
		 * Sample Call
		 * https://api.twitter.com/1.1/statuses/destroy/240854986559455234.json
		 */
		uri = new URIBuilder().setScheme("https").setHost("api.twitter.com")
				.setPath("1.1/statuses/destroy/" + response.id_str + ".json").build();
		String s = given().auth().oauth(ConsumerKey, ConsumerSecret, TokenKey, TokenSecret).when().post(uri)
				.thenReturn().asString();
		System.out.println(s);

	}

}
