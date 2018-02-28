package com.api.rest.api.helper;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.Test;

import com.api.rest.api.model.ResponseBody;
import com.api.rest.api.model.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class TestPutMethod {

	@Test
	public void testPostMethod() throws JsonParseException, JsonMappingException, IOException {
		String id = (int) (1000 * (Math.random())) + "";
		String xmlBody = "<Laptop>" + "<BrandName>DELL</BrandName>" + "<Features>" + "<Feature>i7 7000U</Feature>"
				+ "<Feature>16GB RAM</Feature>" + "<Feature>512 SSD</Feature>" + "</Features>" + "<Id>" + id + "</Id>"
				+ "<LaptopName>xps</LaptopName>" + "</Laptop>";
		Map<String, String> headers = new LinkedHashMap<String, String>();
		headers.put("Accept", "application/xml");
		headers.put("Content-Type", "application/xml");
		RestResponse response = RestApiHelper.performPostRequest("http://localhost:8080/laptop-bag/webapi/api/add",
				xmlBody, ContentType.APPLICATION_XML, headers);
		assertEquals(response.getResponseStatusCode(), HttpStatus.SC_OK);
		xmlBody = "<Laptop>" + "<BrandName>DELL</BrandName>" + "<Features>" + "<Feature>i7 7000U</Feature>"
				+ "<Feature>16GB RAM</Feature>" + "<Feature>512 SSD</Feature>"
				+ "<Feature>This is updated with put</Feature>" + "</Features>" + "<Id>" + id + "</Id>"
				+ "<LaptopName>xps</LaptopName>" + "</Laptop>";
		response = RestApiHelper.performPutRequest("http://localhost:8080/laptop-bag/webapi/api/update", xmlBody,
				ContentType.APPLICATION_XML, headers);
		assertEquals(response.getResponseStatusCode(), HttpStatus.SC_OK);
		response = RestApiHelper.performGetRequest("http://localhost:8080/laptop-bag/webapi/api/find/" + id, headers);
		XmlMapper mapper = new XmlMapper();
		mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		ResponseBody body = mapper.readValue(response.getResponseBody(), ResponseBody.class);
		assertEquals("xps", body.LaptopName);
	}

}
