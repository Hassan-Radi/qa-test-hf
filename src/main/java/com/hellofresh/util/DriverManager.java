package com.hellofresh.util;

import org.openqa.selenium.WebDriver;

/**
 * Manages the WebDriver instance and allows access to it from anywhere.
 */
public class DriverManager {

	private static WebDriver driver;

	/**
	 * Private constructor, so no one can instantiate an object of this class.
	 */
	private DriverManager() {
	}

	public static WebDriver getDriver() {
		if (driver == null) {
			throw new RuntimeException("Tried to access the driver instance before it was initialised.");
		} else {
			return driver;
		}
	}

	public static void setWebDriver(WebDriver driver) {
		DriverManager.driver = driver;
	}
}