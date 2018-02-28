package com.api.rest.api.model;

public class RestResponse {

	private int responseStatusCode;
	private String responseBody;

	public int getResponseStatusCode() {
		return responseStatusCode;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public RestResponse(int responseStatusCode, String responseBody) {
		this.responseStatusCode = responseStatusCode;
		this.responseBody = responseBody;
	}

	public String toString() {

		return "Response Status Code: " + getResponseStatusCode() + " Response Body: " + getResponseBody();
	}

}
