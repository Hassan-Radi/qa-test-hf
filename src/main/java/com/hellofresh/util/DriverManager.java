package com.hellofresh.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

	public static String getUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Returns true if the currently used browser is Firefox
	 */
	public static boolean isFirefox() {
		if (driver == null) {
			return false;
		} else if (driver instanceof FirefoxDriver) {
			return true;
		}

		return false;
	}

	/**
	 * Returns true if the currently used browser is Chrome
	 */
	public static boolean isChrome() {
		if (driver == null) {
			return false;
		} else if (driver instanceof ChromeDriver) {
			return true;
		}

		return false;
	}

	/**
	 * Sometimes we need to click with JavaScript instead of relying on the Selenium native click
	 * method.
	 * 
	 * @param element
	 *            The element to click on
	 */
	public static void clickWithJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}
}