package com.api.rest.api.helper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.Assert;
import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.XmlTypeResolverBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestPostMethod {

	// @Test
	public void testPost() {

		String id = (int) (1000 * (Math.random())) + "";
		String payLoad = "{\"BrandName\": \"LENOVO\"," + "\"Features\": " + "{\"Feature\": " + "[\"i5 8650K\","
				+ "\"8GB RAM\"," + "\"128 SSD\"]}," + "\"Id\": " + id + "," + "\"LaptopName\": \"xps\"}";
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add",
				payLoad, ContentType.APPLICATION_JSON, headers);
		Assert.assertEquals(HttpStatus.SC_OK, response.getResponseStatusCode());
		response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.serializeNulls().setPrettyPrinting().create();
		ResponseBody body = gson.fromJson(response.getResponseBody(), ResponseBody.class);
		Assert.assertEquals(id, String.valueOf(body.Id));
		Assert.assertEquals("xps", body.LaptopName);
		System.out.println(body.Features.Feature);
	}

	@Test
	public void testPostResultWithXML() throws JsonParseException, JsonMappingException, IOException {

		String id = (int) (1000 * (Math.random())) + "";
		String payLoad = "{\"BrandName\": \"LENOVO\"," + "\"Features\": " + "{\"Feature\": " + "[\"i5 8650K\","
				+ "\"8GB RAM\"," + "\"128 SSD\"]}," + "\"Id\": " + id + "," + "\"LaptopName\": \"xps\"}";
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/xml");
		headers.put("Content-Type", "application/json");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add",
				payLoad, ContentType.APPLICATION_JSON, headers);
		response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		XmlMapper xml = new XmlMapper();
		xml.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		ResponseBody body = xml.readValue(response.getResponseBody(), ResponseBody.class);
		Assert.assertEquals(id, String.valueOf(body.Id));
		Assert.assertEquals("LENOVO", body.BrandName);
		Assert.assertEquals("xps", body.LaptopName);
	}

}
