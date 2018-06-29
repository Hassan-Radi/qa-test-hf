package com.hellofresh.api;

import java.util.Arrays;

public class RestResponse {

	private String[] messages;
	private Result[] result;

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

	public Result[] getResult() {
		return result;
	}

	public void setResult(Result[] result) {
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
		for (Result currentResult : result) {
			if (currentResult.getAlpha2_code().equalsIgnoreCase(countryCode)) {
				return true;
			}
		}
		return false;
	}
}