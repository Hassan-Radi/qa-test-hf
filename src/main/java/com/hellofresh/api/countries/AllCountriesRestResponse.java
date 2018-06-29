package com.hellofresh.api.countries;

import java.util.Arrays;

import com.hellofresh.api.CountryResultEntry;

public class AllCountriesRestResponse {

	private String[] messages;
	private CountryResultEntry[] result;

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

	public CountryResultEntry[] getResult() {
		return result;
	}

	public void setResult(CountryResultEntry[] result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "RestResponse [messages=" + Arrays.toString(messages) + ", result=" + Arrays.toString(result) + "]";
	}

	/**
	 * Checks if the list of results contain a specific country or not.
	 * 
	 * @param countryCode
	 *            The country code to check for
	 * @return True if the country exists, false oherwise.
	 */
	public boolean containCountry(String countryCode) {
		for (CountryResultEntry currentResult : result) {
			if (currentResult.getAlpha2_code().equalsIgnoreCase(countryCode)) {
				return true;
			}
		}
		return false;
	}
}