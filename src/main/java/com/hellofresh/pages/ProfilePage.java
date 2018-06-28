package com.hellofresh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object that represents all the elements on the current website page
 */
public class ProfilePage extends PageObject {

	public ProfilePage() {
		/**
		 * Make sure that the page loaded successfully before interacting with the page
		 */
		wait.until(ExpectedConditions.visibilityOf(pageHeader));
	}

	@FindBy(css = "h1")
	private WebElement pageHeader;

	public String getHeaderText() {
		return pageHeader.getText();
	}
}