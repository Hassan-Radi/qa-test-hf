package com.hellofresh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object that represents all the elements on the current website page
 */
public class OrderConfirmationPage extends PageObject {

	public OrderConfirmationPage() {
		/**
		 * Make sure that the page loaded successfully before interacting with the page
		 */
		wait.until(ExpectedConditions.visibilityOf(pageHeader));
	}

	@FindBy(css = "h1")
	private WebElement pageHeader;

	@FindBy(xpath = "//li[@class='step_done step_done_last four']")
	private WebElement shippingStepCompletedLink;

	@FindBy(xpath = "//li[@id='step_end' and @class='step_current last']")
	private WebElement lastStepCompletedLink;

	@FindBy(xpath = "//*[@class='cheque-indent']/strong")
	private WebElement orderCompletedMessageElement;

	public String getHeaderText() {
		return pageHeader.getText();
	}

	public boolean isStep4Completed() {
		return shippingStepCompletedLink.isDisplayed();
	}

	public boolean isLastStepCompleted() {
		return lastStepCompletedLink.isDisplayed();
	}

	public String getOrderCompletedMessageText() {
		return orderCompletedMessageElement.getText();
	}
}