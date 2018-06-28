package com.hellofresh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object that represents all the elements on the current website page
 */
public class AuthenticationPage extends PageObject {

	public AuthenticationPage() {
		/**
		 * Make sure that the page loaded successfully before interacting with the page
		 */
		wait.until(ExpectedConditions.visibilityOf(createEmailAddressTextBox));
	}

	@FindBy(id = "email_create")
	private WebElement createEmailAddressTextBox;

	@FindBy(id = "SubmitCreate")
	private WebElement createAnAccountButton;

	public RegisterAccount navigateToRegisterAccountPage(String emailAddress) {
		createEmailAddressTextBox.sendKeys(emailAddress);
		createAnAccountButton.click();

		return new RegisterAccount();
	}
}