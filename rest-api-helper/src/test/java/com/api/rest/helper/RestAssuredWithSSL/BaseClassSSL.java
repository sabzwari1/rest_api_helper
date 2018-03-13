/**
 * 
 */
package com.api.rest.helper.RestAssuredWithSSL;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;
import static io.restassured.RestAssured.preemptive;

import org.junit.BeforeClass;

import io.restassured.authentication.CertificateAuthSettings;

/**
 * @author sss033
 *
 */
public class BaseClassSSL {

	
	@BeforeClass
	public static void setup() {
		baseURI = "http://localhost";
		port = 8080;
		basePath = "/laptop-bag/webapi/sslres";
	}
	
	
}
