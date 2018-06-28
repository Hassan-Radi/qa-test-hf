package com.hellofresh.constants;

import java.util.Date;

/**
 * Class to act as a container for all the test data in the project. When you need to change any of
 * these values, you change it in one location and it reflects everywhere else in the whole project.
 */
public class TestData {

	public static final String EXISTING_USER_EMAIL = "hf_challenge_123456@hf12345.com";
	public static final String EXISTING_USER_PASSWORD = "12345678";
	public static final String FIRST_NAME = "Firstname";
	public static final String SURNAME = "Lastname";
	public static final String FULL_NAME_1 = FIRST_NAME + " " + SURNAME;
	public static final String FULL_NAME_2 = "Joe Black";
	public static final String PASSWORD = "Qwerty";
	public static final String COMPANY = "Company";
	public static final String ADDRESS_1 = "Qwerty, 123";
	public static final String ADDRESS_2 = "zxcvb";
	public static final String CITY = "Qwerty";
	public static final String STATE = "Colorado";
	public static final String POST_CODE = "12345";
	public static final String ADDITIONAL_INFO = "Qwerty";
	public static final String HOME_PHONE = "12345123123";
	public static final String MOBILE_PHONE = "12345123123";
	public static final String ADDRESS_ALIAS = "hf";
	public static final int TEN_SECONDS = 10;
	public static final int FIFTY_MILLI_SECONDS = 50;
	public static final String DAY = "1";
	public static final String MONTH = "1";
	public static final String YEAR = "2000";

	public static final String MY_ACCOUNT_TEXT = "MY ACCOUNT";
	public static final String WELCOME_TO_ACCOUNT_TEXT = "Welcome to your account.";
	public static final String MY_ACCOUNT_URL_PART = "controller=my-account";
	public static final String ORDER_CONFIRMATION_TEXT = "ORDER CONFIRMATION";
	public static final String ORDER_IS_COMPLETE_MESSAGE = "Your order on My Store is complete.";
	public static final String ORDER_CONFIRMATION_URL_PART = "controller=order-confirmation";
	public static final String FADED_SHORT_SLEEVE_TSHIRTS = "Faded Short Sleeve T-shirts";
	public static final String WOMEN = "Women";

	public static String getRandomEmail() {
		String timestamp = String.valueOf(new Date().getTime());
		return "hf_challenge_" + timestamp + "@hf" + timestamp.substring(7) + ".com";
	}
}