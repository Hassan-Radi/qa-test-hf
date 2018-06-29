package com.hellofresh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object that represents all the elements on the current website page
 */
public class Homepage extends PageObject {

	public Homepage() {
		/**
		 * Make sure that the page loaded successfully before interacting with the page
		 */
		wait.until(ExpectedConditions.visibilityOf(loginButton));
	}

	@FindBy(className = "login")
	private WebElement loginButton;

	public AuthenticationPage navigateToAuthenticationPage() {
		LOGGER.info("Clicking on the login button...");
		loginButton.click();

		return new AuthenticationPage();
	}
}