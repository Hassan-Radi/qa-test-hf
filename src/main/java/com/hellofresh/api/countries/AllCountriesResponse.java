package com.hellofresh.api.countries;

public class AllCountriesResponse {

	private AllCountriesRestResponse RestResponse;

	public AllCountriesRestResponse getRestResponse() {
		return RestResponse;
	}

	public void setRestResponse(AllCountriesRestResponse restResponse) {
		RestResponse = restResponse;
	}

	@Override
	public String toString() {
		return "CountryResponse [RestResponse=" + RestResponse + "]";
	}
}