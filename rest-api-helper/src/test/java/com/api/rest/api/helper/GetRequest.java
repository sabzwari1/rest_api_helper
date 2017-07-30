package com.api.rest.api.helper;

import org.apache.http.StatusLine;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class GetRequest {

	public static void main(String[] args) {

		HttpGet get = new HttpGet("http://localhost:8080/laptop-bag/webapi/api/ping/BisMillah");

		try (CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse response = client.execute(get)) {
			StatusLine status = response.getStatusLine();
			System.out.println(status.getStatusCode());
			System.out.println(status.getProtocolVersion());
			ResponseHandler<String> body = new BasicResponseHandler();
			String getBody = body.handleResponse(response);
			System.out.println(getBody);
			/*
			 * When using try catch block and opening clinet and reponse obejcts in try
			 * block, try catch block will close the repose and client by its self
			 */
			// client.close();
			// response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
