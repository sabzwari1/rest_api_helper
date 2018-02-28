package com.api.rest.api.helper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;
import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestGetMethod {

	@Test
	public void testGetPingAlive() {
		String url = "http://localhost:8080/laptop-bag/webapi/api/ping/BisMillah";
		RestResponse response = RestApiHelper.performGetRequest(url, null);
		Assert.assertEquals(HttpStatus.SC_OK, response.getResponseStatusCode());
		Assert.assertEquals("Hi! BisMillah", response.getResponseBody());
	}

	@Test
	public void testGetAll() {
		String url = "http://localhost:8080/laptop-bag/webapi/api/all";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		RestResponse response = RestApiHelper.performGetRequest(url, headers);
		Assert.assertTrue("Expected status code not found", (HttpStatus.SC_OK == response.getResponseStatusCode())
				|| (HttpStatus.SC_NO_CONTENT == response.getResponseStatusCode()));
		// System.out.println(response.getResponseBody());
	}

	@Test
	public void testGetFindwithID() {
		String url = "http://localhost:8080/laptop-bag/webapi/api/find/100";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		RestResponse response = RestApiHelper.performGetRequest(url, headers);
		Assert.assertTrue("Expected status code not found", (HttpStatus.SC_OK == response.getResponseStatusCode())
				|| (HttpStatus.SC_NOT_FOUND == response.getResponseStatusCode()));
		// System.out.println(response.getResponseBody());
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		Assert.assertEquals(100, body.Id);
		Assert.assertEquals("DELL", body.BrandName);
		Assert.assertEquals("xps", body.LaptopName);
		/*List<String> expectedFeature = new ArrayList<>(Arrays.asList("i7 7000U", "16GB RAM", "512 SSD"));
		System.out.println(body.Features.toString());
		 Assert.assertEquals(expectedFeature, body.Features.Features1);
*/
	}

}
