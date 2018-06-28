package com.hellofresh.challenge;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hellofresh.constants.TestData;
import com.hellofresh.pages.AuthenticationPage;
import com.hellofresh.pages.Homepage;
import com.hellofresh.pages.ProfilePage;
import com.hellofresh.pages.RegisterAccount;

public class WebTest extends BaseTest {

	@Test
	public void signInTest() {
		Homepage homepage = new Homepage();
		AuthenticationPage authenticationPage = homepage.navigateToAuthenticationPage();
		RegisterAccount registerAccount = authenticationPage.navigateToRegisterAccountPage(TestData.getRandomEmail());

		ProfilePage profilePage = registerAccount.registerUser(TestData.FIRST_NAME, TestData.SURNAME, TestData.PASSWORD,
				TestData.DAY, TestData.MONTH, TestData.YEAR, TestData.COMPANY, TestData.ADDRESS_1, TestData.ADDRESS_2,
				TestData.CITY, TestData.STATE, TestData.POST_CODE, TestData.ADDITIONAL_INFO, TestData.HOME_PHONE,
				TestData.MOBILE_PHONE, TestData.ADDRESS_ALIAS);

		String headerText = profilePage.getHeaderText();

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
}