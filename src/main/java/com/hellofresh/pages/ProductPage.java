package com.hellofresh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Page object that represents all the elements on the current website page
 */
public class ProductPage extends PageObject {

	public ProductPage() {
		/**
		 * Make sure that the page loaded successfully before interacting with the page
		 */
		wait.until(ExpectedConditions.visibilityOf(addToCartButton));
	}

	@FindBy(name = "Submit")
	private WebElement addToCartButton;

	@FindBy(xpath = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
	private WebElement proceedToCheckoutButton;

	public ShoppingCartPage addProductAndNavigateToShoppingCart() {
		addToCartButton.click();

		wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton));
		proceedToCheckoutButton.click();

		return new ShoppingCartPage();
	}
}