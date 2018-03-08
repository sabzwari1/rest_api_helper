/**
 * 
 */
package com.api.rest.helper.RestAssuredWithAuthentication;

import static io.restassured.RestAssured.*;

import org.junit.BeforeClass;

/**
 * @author sss033
 *
 */
public class BaseClassSecure {

	@BeforeClass
	public static void setup() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "/laptop-bag/webapi/secure";
		authentication = preemptive().basic("admin", "welcome");
	}
}
