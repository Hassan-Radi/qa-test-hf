package com.hellofresh.api;

public class CountryResponse {

	private RestResponse RestResponse;

	public RestResponse getRestResponse() {
		return RestResponse;
	}

	public void setRestResponse(RestResponse restResponse) {
		RestResponse = restResponse;
	}

	@Override
	public String toString() {
		return "CountryResponse [RestResponse=" + RestResponse + "]";
	}
}