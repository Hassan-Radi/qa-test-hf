package com.hellofresh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object that represents all the elements on the current website page
 */
public class ShoppingCartPage extends PageObject {

	public ShoppingCartPage() {
		/**
		 * Make sure that the page loaded successfully before interacting with the page
		 */
		wait.until(ExpectedConditions.visibilityOf(summaryProceedToCheckoutButton));
	}

	@FindBy(xpath = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
	private WebElement summaryProceedToCheckoutButton;

	@FindBy(name = "processAddress")
	private WebElement addressProceedToCheckoutButton;

	@FindBy(name = "processCarrier")
	private WebElement shippingProceedToCheckoutButton;

	@FindBy(id = "uniform-cgv")
	private WebElement acceptTermsAndConditionsCheckBox;

	@FindBy(className = "bankwire")
	private WebElement payByBankWireLink;

	@FindBy(xpath = "//*[@id='cart_navigation']/button")
	private WebElement orderConfirmationButton;

	public OrderConfirmationPage completeCheckoutProcess() {
		// Step 1: Summary
		LOGGER.info("Step 1: Clicking on the proceed to checkout button.");
		summaryProceedToCheckoutButton.click();

		// Step 2: Sign in --> already signed in, moving on to the next step

		// Step 3: Address
		wait.until(ExpectedConditions.visibilityOf(addressProceedToCheckoutButton));
		LOGGER.info("Step 3: Clicking on the proceed to checkout button.");
		addressProceedToCheckoutButton.click();

		// Step 4: Shipping
		wait.until(ExpectedConditions.visibilityOf(shippingProceedToCheckoutButton));
		LOGGER.info("Accepting the terms and conditions check-box...");
		acceptTermsAndConditionsCheckBox.click();
		LOGGER.info("Step 4: Clicking on the proceed to checkout button.");
		shippingProceedToCheckoutButton.click();

		// Step 5: Payment
		wait.until(ExpectedConditions.visibilityOf(payByBankWireLink));
		LOGGER.info("Step 5: Clicking on the pay by bank wire link...");
		payByBankWireLink.click();

		// Final step: confirm order
		wait.until(ExpectedConditions.visibilityOf(orderConfirmationButton));
		LOGGER.info("Clicking on the confirm order button...");
		orderConfirmationButton.click();

		return new OrderConfirmationPage();
	}
}