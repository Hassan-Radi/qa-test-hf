package com.hellofresh.challenge;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hellofresh.api.CountryResponse;
import com.hellofresh.constants.TestData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITest {

	@Test
	public void certainCountriesAreInTheListOfAllCountries() {
		RestAssured.baseURI = TestData.API_ALL_COUNTRIES_END_POINT;
		RequestSpecification httpRequest = RestAssured.given();

		/**
		 * Get the response and deserialize the JSON response to a Java object that you can access
		 */
		Response response = httpRequest.get();
		CountryResponse countryResponse = response.getBody().as(CountryResponse.class);

		assertTrue("Country doesn't exist in the list of returned results.",
				countryResponse.getRestResponse().containCountry(TestData.US));
		assertTrue("Country doesn't exist in the list of returned results.",
				countryResponse.getRestResponse().containCountry(TestData.DE));
		assertTrue("Country doesn't exist in the list of returned results.",
				countryResponse.getRestResponse().containCountry(TestData.GB));

		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}
}