package com.hellofresh.api.country;

public class CountryResponse {

	private CountryRestResponse RestResponse;

	public CountryRestResponse getRestResponse() {
		return RestResponse;
	}

	public void setRestResponse(CountryRestResponse restResponse) {
		RestResponse = restResponse;
	}

	@Override
	public String toString() {
		return "CountryResponse [RestResponse=" + RestResponse + "]";
	}
}