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
		for (String[] countryEntryData : TestData.COUNTRIES_DATA) {
			String countryMessage = countryEntryData[0];
			String countryName = countryEntryData[1];
			String countryAlpha2Code = countryEntryData[2];
			String countryAlpha3Code = countryEntryData[3];

			RestAssured.baseURI = TestData.getEndPointForCountry(countryAlpha2Code);
			RequestSpecification httpRequest = RestAssured.given();

			/**
			 * Get the response and de-serialize the JSON response to a Java object that you can
			 * access
			 */
			Response response = httpRequest.get();
			CountryResponse countryResponse = response.getBody().as(CountryResponse.class);

			assertEquals("Message is incorrect.", countryMessage, countryResponse.getRestResponse().getMessages()[0]);
			assertEquals("Country name is incorrect.", countryName,
					countryResponse.getRestResponse().getResult().getName());
			assertEquals("Alpha2_code is incorrect.", countryAlpha2Code,
					countryResponse.getRestResponse().getResult().getAlpha2_code());
			assertEquals("Alpha3_code is incorrect.", countryAlpha3Code,
					countryResponse.getRestResponse().getResult().getAlpha3_code());

			response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
		}
	}

	@Test
	public void nonExistingCountry() {
		RestAssured.baseURI = TestData.getEndPointForCountry(TestData.NON_EXISTING_COUNTRY);
		RequestSpecification httpRequest = RestAssured.given();

		/**
		 * Get the response and de-serialize the JSON response to a Java object that you can access
		 */
		Response response = httpRequest.get();
		CountryResponse countryResponse = response.getBody().as(CountryResponse.class);

		assertEquals("Message is incorrect.",
				String.format(TestData.NON_EXISITNG_COUNTRY_RESPONSE_MESSAGE, TestData.NON_EXISTING_COUNTRY),
				countryResponse.getRestResponse().getMessages()[0]);

		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}
}