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

	@FindBy(id = "email")
	private WebElement loginEmailAddressTextBox;

	@FindBy(id = "passwd")
	private WebElement loginPasswordTextBox;

	@FindBy(id = "SubmitLogin")
	private WebElement loginButton;

	public RegisterAccount navigateToRegisterAccountPage(String emailAddress) {
		LOGGER.info("Typing the email address " + emailAddress + " in the email address textbox.");
		createEmailAddressTextBox.sendKeys(emailAddress);
		LOGGER.info("Clicking on the 'Register account' button...");
		createAnAccountButton.click();

		return new RegisterAccount();
	}

	/**
	 * Logs in to the system using the given credentials.
	 * 
	 * @param email
	 *            The email to use when logging in to the system.
	 * @param password
	 *            The password to use when logging in to the system.
	 * @return A page object that represents the ProfilePage
	 */
	public ProfilePage login(String email, String password) {
		LOGGER.info(String.format("Logging in using credentials (Email = %s, Password = %s).", email, password));
		loginEmailAddressTextBox.sendKeys(email);
		loginPasswordTextBox.sendKeys(password);
		LOGGER.info("Clicking on the login button...");
		loginButton.click();

		return new ProfilePage();
	}
}