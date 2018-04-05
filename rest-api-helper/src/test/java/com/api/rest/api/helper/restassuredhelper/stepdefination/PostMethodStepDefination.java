/**
 * 
 */
package com.api.rest.api.helper.restassuredhelper.stepdefination;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import java.util.List;

import com.api.rest.api.helper.restassuredhelper.BaseClass;
import com.api.rest.api.helper.restassuredhelper.transform.TransformData;
import com.api.util.RestUtil;

/**
 * @author sss033
 *
 */
public class PostMethodStepDefination extends BaseClass {

	private RequestSpecification requestSpecification;
	private Response response;
	private String jsonBody;

	@Given("^Accept the contents in JSON format$")
	public void accept_the_contents_in_JSON_format() throws Throwable {
		requestSpecification = given().accept(ContentType.JSON);
	}

	@Given("^Content type as JSON$")
	public void content_type_as_JSON() throws Throwable {
		requestSpecification = requestSpecification.contentType(ContentType.JSON);
	}

	@When("^I perform the POST request with BrandName as \"([^\"]*)\", Features as \"([^\"]*)\", LaptopName as \"([^\"]*)\"$")
	public void i_perform_the_POST_request_with_BrandName_as_Features_as_LaptopName_as(String brandName, String feature,
			String laptopName) throws Throwable {
		String payLoad = RestUtil.getJsonBody(brandName, null, laptopName, Arrays.asList(feature.split(",")));
		response = requestSpecification.body(payLoad).post("/add");

	}

	@Then("^Status code \"([^\"]*)\" should return$")
	public void status_code_should_return(String statusCode) throws Throwable {
		response.then().assertThat().statusCode(Integer.parseInt(statusCode));
	}

	@And("^Response should have integer id$")
	public void response_should_have_integer_id() throws Throwable {
		response.then().assertThat().body("Id", instanceOf(Integer.class));
		// response.then().assertThat().body("Id", is(Integer.class));
	}

	@But("^I supply invalid json body$")
	public void i_supply_invalid_json_body() throws Throwable {
		jsonBody = "invalid body";
	}

	@When("^I perfom the post request$")
	public void i_perfom_the_post_request() throws Throwable {
		response = requestSpecification.body(jsonBody).post("/add");
	}

	@When("^I perform the POST request with details \"([^\"]*)\"$")
	public void i_perform_the_POST_request_with_details(@Transform(TransformData.class)List<String> arg1) throws Throwable {
		String payLoad = RestUtil.getJsonBody(arg1.get(0), null, arg1.get(arg1.size()-1), arg1.subList(1, arg1.size()-1));
		response = requestSpecification.body(payLoad).post("/add");
	}

}
