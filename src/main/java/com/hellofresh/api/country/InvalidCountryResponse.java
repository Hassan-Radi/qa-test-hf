package com.hellofresh.api.country;

public class InvalidCountryResponse {

	private InvalidCountryRestResponse RestResponse;

	public InvalidCountryRestResponse getRestResponse() {
		return RestResponse;
	}

	public void setRestResponse(InvalidCountryRestResponse restResponse) {
		RestResponse = restResponse;
	}

	@Override
	public String toString() {
		return "InvalidCountryResponse [RestResponse=" + RestResponse + "]";
	}
}