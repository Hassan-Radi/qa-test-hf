package com.hellofresh.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hellofresh.constants.TestData;

/**
 * Page object that represents all the elements on the current website page
 */
public class CatalogPage extends PageObject {

	public CatalogPage() {
		/**
		 * Make sure that the page loaded successfully before interacting with the page
		 */
		wait.until(ExpectedConditions.visibilityOf(getProductElement(TestData.FADED_SHORT_SLEEVE_TSHIRTS)));
	}

	private WebElement getProductElement(String productName) {
		return driver.findElement(By.xpath(String.format("//a[@title='%s']/ancestor::li", productName)));
	}

	public ProductPage clickOnProductWithName(String productName) {
		/**
		 * First click would show the hidden container by default. Second click would navigate to
		 * the product page
		 */
		WebElement product = getProductElement(productName);
		product.click();
		product.click();

		return new ProductPage();
	}
}