/**
* 
*/
package com.api.rest.api.helper;

import java.io.Closeable;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.api.rest.api.model.RestResponse;

import io.restassured.RestAssured;

/**
 * @author Umair
 *
 */
public class GetRequest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * RestResponse response = RestApiHelper .performGetRequest(
		 * "http://localhost:8080/laptop-bag/webapi/api/ping/BisMillah",null);
		 * System.out.println(response.toString());
		 */

		// When using string object to post data
		/*
		 * String payLoad = "{\"BrandName\": \"LENOVO\"," + "\"Features\": " +
		 * "{\"Feature\": " + "[\"i5 8650K\"," + "\"8GB RAM\"," + "\"128 SSD\"]}," +
		 * "\"Id\": 300," + "\"LaptopName\": \"xps\"}";
		 */
		/*
		 * HttpPost post = new
		 * HttpPost("http://localhost:8080/laptop-bag/webapi/api/add");
		 * 
		 * try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
		 * post.addHeader("Content-Type", "application/json"); post.addHeader("Accept",
		 * "application/xml"); //When using string entity to post the data StringEntity
		 * data = new StringEntity(payLoad, ContentType.APPLICATION_JSON);
		 * post.setEntity(data);
		 * 
		 * //When using file entity to post the data File file = new
		 * File("TestDataFile"); FileEntity data = new FileEntity(file,
		 * ContentType.APPLICATION_JSON); post.setEntity(data);
		 * 
		 * CloseableHttpResponse response = client.execute(post);
		 * ResponseHandler<String> handler = new BasicResponseHandler(); RestResponse
		 * restResponse = new RestResponse(response.getStatusLine().getStatusCode(),
		 * handler.handleResponse(response));
		 * System.out.println(restResponse.toString());
		 * 
		 * }
		 * 
		 * catch (Exception e) {
		 * 
		 * }
		 */
		/*
		 * File file = new File("TestDataFile"); Map<String, String> headers = new
		 * LinkedHashMap<String, String>(); headers.put("Accept", "application/json");
		 * headers.put("Content-Type", "application/json");
		 * 
		 * // Can use file or payload object and perform post request method will
		 * determine // to use string or file entity for posting RestResponse response =
		 * RestApiHelper.performPostRequest(
		 * "http://localhost:8080/laptop-bag/webapi/api/add", payLoad,
		 * ContentType.APPLICATION_JSON, headers);
		 * System.out.println(response.getResponseStatusCode());
		 * System.out.println(response.getResponseBody());
		 */

		/*
		 * HttpUriRequest delete = RequestBuilder.delete(
		 * "http://localhost:8080/laptop-bag/webapi/api/delete/400").build();
		 * 
		 * try (CloseableHttpClient client = HttpClientBuilder.create().build();
		 * CloseableHttpResponse response = client.execute(delete);) {
		 * ResponseHandler<String> handler = new BasicResponseHandler(); RestResponse
		 * restResponse = new RestResponse(response.getStatusLine().getStatusCode(),
		 * handler.handleResponse(response));
		 * System.out.println(restResponse.toString()); } catch (Exception e) {
		 * e.printStackTrace(); }
		 */
		String xmlBody = "<Laptop>" + "<BrandName>DELL</BrandName>" + "<Features>" + "<Feature>i7 7000U</Feature>"
				+ "<Feature>16GB RAM</Feature>" + "<Feature>512 SSD</Feature>"
				+ "<Feature>This is updated with put</Feature>" + "</Features>" + "<Id>110</Id>"
				+ "<LaptopName>xps</LaptopName>" + "</Laptop>";

		RequestBuilder buildpost = RequestBuilder.put("http://localhost:8080/laptop-bag/webapi/api/update")
				.setHeader("Content-Type", "application/xml").setHeader("Accept", "application/xml");
		HttpUriRequest put = buildpost.setEntity(new StringEntity(xmlBody, ContentType.APPLICATION_XML)).build();

		try (CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse response = client.execute(put)) {
			ResponseHandler<String> handler = new BasicResponseHandler();
			RestResponse restResponse = new RestResponse(response.getStatusLine().getStatusCode(),
					handler.handleResponse(response));
			System.out.println(restResponse.toString());
		} catch (Exception e) {
		}

	}

}
