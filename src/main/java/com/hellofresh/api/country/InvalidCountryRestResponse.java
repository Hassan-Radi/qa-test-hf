package com.hellofresh.api.country;

import java.util.Arrays;

public class InvalidCountryRestResponse {

	private String[] messages;

	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

	@Override
	public String toString() {
		return "InvalidCountryRestResponse [messages=" + Arrays.toString(messages) + "]";
	}
}