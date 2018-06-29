package com.hellofresh.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.json.simple.JSONObject;
import org.junit.Test;

import com.hellofresh.api.countries.AllCountriesResponse;
import com.hellofresh.api.country.CountryResponse;
import com.hellofresh.api.country.InvalidCountryResponse;
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

			getCountry(countryMessage, countryName, countryAlpha2Code, countryAlpha3Code);
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
		InvalidCountryResponse countryResponse = response.getBody().as(InvalidCountryResponse.class);

		assertEquals("Message is incorrect.",
				String.format(TestData.NON_EXISITNG_COUNTRY_RESPONSE_MESSAGE, TestData.NON_EXISTING_COUNTRY),
				countryResponse.getRestResponse().getMessages()[0]);

		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}

	@Test
	public void addNewCountry() {
		RestAssured.baseURI = TestData.API_POST_NEW_COUNTRY_END_POINT;
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put(TestData.COUNTRY_NAME_PARAM, TestData.TEST_COUNTRY_NAME);
		requestParams.put(TestData.COUNTRY_ALPHA2_PARAM, TestData.TEST_COUNTRY_ALPAH2);
		requestParams.put(TestData.COUNTRY_ALPHA3_PARAM, TestData.TEST_COUNTRY_ALPHA3);

		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.post();

		// Getting status code 201 "Created"
		response.then().assertThat().statusCode(201);

		// Try to get the country details and verify the results you get
		getCountry(TestData.TEST_COUNTRY_MESSAGE, TestData.TEST_COUNTRY_NAME, TestData.TEST_COUNTRY_ALPAH2,
				TestData.TEST_COUNTRY_ALPHA3);
	}

	/**
	 * Sends an API call to get a country details using the alpha2 country code.
	 * 
	 * @param countryMessage
	 *            The message that we get from the API end-point
	 * @param countryName
	 *            The country name that we get from the API end-point
	 * @param countryAlpha2Code
	 *            The Alpha 2 country code that we get from the API end-point
	 * @param countryAlpha3Code
	 *            The alpha 3 country code that we get from the API end-point
	 */
	private void getCountry(String countryMessage, String countryName, String countryAlpha2Code,
			String countryAlpha3Code) {
		RestAssured.baseURI = TestData.getEndPointForCountry(countryAlpha2Code);
		RequestSpecification httpRequest = RestAssured.given();

		/**
		 * Get the response and de-serialize the JSON response to a Java object that you can access
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