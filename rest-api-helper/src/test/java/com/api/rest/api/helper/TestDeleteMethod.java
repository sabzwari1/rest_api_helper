package com.api.rest.api.helper;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Test;
import com.api.rest.api.model.RestResponse;

public class TestDeleteMethod {

	@Test
	public void testDelete() {
		String id = (int) (1000 * (Math.random())) + "";
		String payLoad = "{\"BrandName\": \"LENOVO\"," + "\"Features\": " + "{\"Feature\": " + "[\"i5 8650K\","
				+ "\"8GB RAM\"," + "\"128 SSD\"]}," + "\"Id\": " + id + "," + "\"LaptopName\": \"xps\"}";
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add",
				payLoad, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getResponseStatusCode());
		response = RestApiHelper.performDeleteRequest("http://localhost:8080/laptop-bag/webapi/api/delete/" + id, null);
		Assert.assertEquals(HttpStatus.SC_OK, response.getResponseStatusCode());
		response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getResponseStatusCode());
		response = RestApiHelper.performDeleteRequest("http://localhost:8080/laptop-bag/webapi/api/delete/" + id, null);
		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, response.getResponseStatusCode());
	}

}
