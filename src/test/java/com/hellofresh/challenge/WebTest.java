package com.hellofresh.challenge;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.hellofresh.constants.TestData;

public class WebTest extends BaseTest {

	@Test
	public void signInTest() {
		String headerText = registerUser(TestData.getRandomEmail(), TestData.FIRST_NAME, TestData.SURNAME,
				TestData.PASSWORD, TestData.COMPANY, TestData.ADDRESS_1, TestData.ADDRESS_2, TestData.CITY,
				TestData.STATE, TestData.POST_CODE, TestData.OTHER, TestData.PHONE, TestData.MOBILE_PHONE,
				TestData.ALIAS);

		// assert correctness of test case
		assertCorrectness(TestData.FULL_NAME_1, headerText);
	}

	@Test
	public void logInTest() {
		String headerText = login(TestData.EXISTING_USER_EMAIL, TestData.EXISTING_USER_PASSWORD);

		// assert correctness of test case
		assertCorrectness(TestData.FULL_NAME_2, headerText);
	}

	@Test
	public void checkoutTest() {
		login(TestData.EXISTING_USER_EMAIL, TestData.EXISTING_USER_PASSWORD);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Women"))).click();
		driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
		driver.findElement(By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("Submit"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("processAddress"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uniform-cgv"))).click();
		driver.findElement(By.name("processCarrier")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bankwire"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart_navigation']/button")))
				.click();
		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

		assertEquals("Incorrect order confirmation text.", TestData.ORDER_CONFIRMATION_TEXT, heading.getText());
		assertTrue(driver.findElement(By.xpath("//li[@class='step_done step_done_last four']")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//li[@id='step_end' and @class='step_current last']")).isDisplayed());
		assertTrue("Incorrect order completed message.",
				driver.findElement(By.xpath("//*[@class='cheque-indent']/strong")).getText()
						.contains(TestData.ORDER_IS_COMPLETE_MESSAGE));
		assertTrue("Page URL doesn't contain the expected text.",
				driver.getCurrentUrl().contains(TestData.ORDER_CONFIRMATION_URL_PART));
	}

	/**
	 * Assert the correctness of the test case (this code is shared between multiple test cases).
	 * 
	 * @param fullName
	 *            The name to assert against
	 * @param headerText
	 *            The text of the header of the page
	 */
	private void assertCorrectness(String fullName, String headerText) {
		assertEquals("My account text is incorrect", TestData.MY_ACCOUNT_TEXT, headerText);
		assertEquals("Incorrect account name.", fullName, driver.findElement(By.className("account")).getText());
		assertTrue("Incorrect welcome message text.",
				driver.findElement(By.className("info-account")).getText().contains(TestData.WELCOME_TO_ACCOUNT_TEXT));
		assertTrue("Logout button is not displayed.", driver.findElement(By.className("logout")).isDisplayed());
		assertTrue("Page URL doesn't contain the expected text.",
				driver.getCurrentUrl().contains(TestData.MY_ACCOUNT_URL_PART));
	}

	/**
	 * Logs in to the system using the given credentials.
	 * 
	 * @param email
	 *            The email to use when logging in to the system.
	 * @param password
	 *            The password to use when logging in to the system.
	 * @return A string that represents the header text of the page after login.
	 */
	private String login(String email, String password) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.id("SubmitLogin")).click();

		WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
		return header.getText();
	}

	/**
	 * Registers a new user given a set of values
	 * 
	 * @param emailAddress
	 *            User email address
	 * @param firstName
	 *            User first name
	 * @param surName
	 *            User surname
	 * @param password
	 *            User password
	 * @param company
	 *            User company
	 * @param address1
	 *            User address line 1
	 * @param address2
	 *            User address line 2
	 * @param city
	 *            User city
	 * @param state
	 *            User state
	 * @param postCode
	 *            User post code value
	 * @param other
	 *            User other field value
	 * @param phone
	 *            User phone number
	 * @param mobilePhone
	 *            User mobile phone number
	 * @param alias
	 *            User alias
	 * @return A string that represents the header text of the page that appears after registering
	 *         the user.
	 */
	private String registerUser(String emailAddress, String firstName, String surName, String password, String company,
			String address1, String address2, String city, String state, String postCode, String other, String phone,
			String mobilePhone, String alias) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login"))).click();
		driver.findElement(By.id("email_create")).sendKeys(emailAddress);
		driver.findElement(By.id("SubmitCreate")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("id_gender2"))).click();
		driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
		driver.findElement(By.id("customer_lastname")).sendKeys(surName);
		driver.findElement(By.id("passwd")).sendKeys(password);
		Select select = new Select(driver.findElement(By.id("days")));
		select.selectByValue("1");
		select = new Select(driver.findElement(By.id("months")));
		select.selectByValue("1");
		select = new Select(driver.findElement(By.id("years")));
		select.selectByValue("2000");
		driver.findElement(By.id("company")).sendKeys(company);
		driver.findElement(By.id("address1")).sendKeys(address1);
		driver.findElement(By.id("address2")).sendKeys(address2);
		driver.findElement(By.id("city")).sendKeys(city);
		select = new Select(driver.findElement(By.id("id_state")));
		select.selectByVisibleText(state);
		driver.findElement(By.id("postcode")).sendKeys(postCode);
		driver.findElement(By.id("other")).sendKeys(other);
		driver.findElement(By.id("phone")).sendKeys(phone);
		driver.findElement(By.id("phone_mobile")).sendKeys(mobilePhone);
		driver.findElement(By.id("alias")).sendKeys(alias);
		driver.findElement(By.id("submitAccount")).click();

		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
		return heading.getText();
	}
}