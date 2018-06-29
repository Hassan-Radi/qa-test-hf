package com.hellofresh.api.country;

import java.util.Arrays;

import com.hellofresh.api.CountryResultEntry;

public class CountryRestResponse {

	private String[] messages;
	private CountryResultEntry result;

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

	public CountryResultEntry getResult() {
		return result;
	}

	public void setResult(CountryResultEntry result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ValidCountryRestResponse [messages=" + Arrays.toString(messages) + ", result=" + result + "]";
	}
}