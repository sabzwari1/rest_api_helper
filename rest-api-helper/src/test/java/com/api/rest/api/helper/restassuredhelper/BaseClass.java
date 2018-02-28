/**
 * 
 */
package com.api.rest.api.helper.restassuredhelper;
import static io.restassured.RestAssured.*;

import org.junit.BeforeClass;
/**
 * @author sss033
 *
 */
public class BaseClass {

@BeforeClass
	public static void setup(){
	baseURI="http://localhost";
	port=8080;
	basePath="/laptop-bag/webapi/api";
	} 
}
