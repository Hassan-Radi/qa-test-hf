package com.hellofresh.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.hellofresh.api.countries.AllCountriesResponse;
import com.hellofresh.api.country.CountryResponse;
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
		 * Get the response and de-serialize the JSON response to a Java object that you can access
		 */
		Response response = httpRequest.get();
		AllCountriesResponse countryResponse = response.getBody().as(AllCountriesResponse.class);

		assertTrue("Country doesn't exist in the list of returned results.",
				countryResponse.getRestResponse().containCountry(TestData.US));
		assertTrue("Country doesn't exist in the list of returned results.",
				countryResponse.getRestResponse().containCountry(TestData.DE));
		assertTrue("Country doesn't exist in the list of returned results.",
				countryResponse.getRestResponse().containCountry(TestData.GB));

		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}

	@Test
	public void validateCountryResponse() {
		/**
		 * All the requested countries don't exist (US, DE & GB), so instead I used the following
		 * country code instead "IN"
		 */
		RestAssured.baseURI = TestData.getEndPointForCountry(TestData.IN);
		RequestSpecification httpRequest = RestAssured.given();

		/**
		 * Get the response and de-serialize the JSON response to a Java object that you can access
		 */
		Response response = httpRequest.get();
		CountryResponse countryResponse = response.getBody().as(CountryResponse.class);

		assertEquals("Message is incorrect.", TestData.IN_MESSAGE, countryResponse.getRestResponse().getMessages()[0]);
		assertEquals("Country name is incorrect.", TestData.IN_NAME,
				countryResponse.getRestResponse().getResult().getName());
		assertEquals("Alpha2_code is incorrect.", TestData.IN_ALPHA2_CODE,
				countryResponse.getRestResponse().getResult().getAlpha2_code());
		assertEquals("Alpha3_code is incorrect.", TestData.IN_ALPHA3_CODE,
				countryResponse.getRestResponse().getResult().getAlpha3_code());

		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}

	@Test
	public void nonExistingCountry() {
		// NON_EXISTING_COUNTRY
	}
}