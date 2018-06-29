package com.hellofresh.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hellofresh.constants.TestData;

/**
 * Page object that represents all the elements on the current website page
 */
public class ProfilePage extends PageObject {

	public ProfilePage() {
		/**
		 * Make sure that the page loaded successfully before interacting with the page
		 */
		wait.until(ExpectedConditions.visibilityOf(pageHeader));
		wait.until(ExpectedConditions.visibilityOf(getLinkByText(TestData.WOMEN)));
	}

	@FindBy(css = "h1")
	private WebElement pageHeader;

	@FindBy(className = "account")
	private WebElement accountNameElement;

	@FindBy(className = "info-account")
	private WebElement welcomeToAccountMessageElement;

	@FindBy(className = "logout")
	private WebElement logoutButton;

	public String getHeaderText() {
		return pageHeader.getText();
	}

	private WebElement getLinkByText(String linkText) {
		return driver.findElement(By.linkText(linkText));
	}

	public CatalogPage navigateToCatalogPage(String catalogName) {
		LOGGER.info("Clicking on the '" + catalogName + "' link...");
		getLinkByText(catalogName).click();
		return new CatalogPage();
	}

	public String getAccountNameText() {
		return accountNameElement.getText();
	}

	public String getWelcomeToAccountMessageText() {
		return welcomeToAccountMessageElement.getText();
	}

	public boolean isLogOutButtonDisplayed() {
		return logoutButton.isDisplayed();
	}
}