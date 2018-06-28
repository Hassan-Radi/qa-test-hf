package com.hellofresh.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hellofresh.constants.TestData;
import com.hellofresh.util.DriverManager;

/**
 * Base page object to offer common functionality for page object creation.
 */
public class PageObject {

	protected static WebDriver driver;
	protected static WebDriverWait wait;

	public PageObject() {
		PageObject.driver = DriverManager.getDriver();
		wait = new WebDriverWait(driver, TestData.TEN_SECONDS, TestData.FIFTY_MILLI_SECONDS);

		PageFactory.initElements(driver, this);
	}
}