package com.hellofresh.challenge;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.hellofresh.constants.TestData;
import com.hellofresh.pages.AuthenticationPage;
import com.hellofresh.pages.CatalogPage;
import com.hellofresh.pages.Homepage;
import com.hellofresh.pages.OrderConfirmationPage;
import com.hellofresh.pages.ProductPage;
import com.hellofresh.pages.ProfilePage;
import com.hellofresh.pages.RegisterAccount;
import com.hellofresh.pages.ShoppingCartPage;

public class WebTest extends BaseTest {

	@Test
	public void signUpTest() {
		Homepage homepage = new Homepage();
		AuthenticationPage authenticationPage = homepage.navigateToAuthenticationPage();
		RegisterAccount registerAccount = authenticationPage.navigateToRegisterAccountPage(TestData.getRandomEmail());
		ProfilePage profilePage = registerAccount.registerUser(TestData.FIRST_NAME, TestData.SURNAME, TestData.PASSWORD,
				TestData.DAY, TestData.MONTH, TestData.YEAR, TestData.COMPANY, TestData.ADDRESS_1, TestData.ADDRESS_2,
				TestData.CITY, TestData.STATE, TestData.POST_CODE, TestData.ADDITIONAL_INFO, TestData.HOME_PHONE,
				TestData.MOBILE_PHONE, TestData.ADDRESS_ALIAS);

		assertEquals("My account text is incorrect", TestData.MY_ACCOUNT_TEXT, profilePage.getHeaderText());
		assertEquals("Incorrect account name.", TestData.FULL_NAME_1, profilePage.getAccountNameText());
		assertTrue("Incorrect welcome message text.",
				profilePage.getWelcomeToAccountMessageText().contains(TestData.WELCOME_TO_ACCOUNT_TEXT));
		assertTrue("Logout button is not displayed.", profilePage.isLogOutButtonDisplayed());
		assertTrue("Page URL doesn't contain the expected text.",
				driver.getCurrentUrl().contains(TestData.MY_ACCOUNT_URL_PART));
	}

	@Test
	public void logInTest() {
		Homepage homepage = new Homepage();
		AuthenticationPage authenticationPage = homepage.navigateToAuthenticationPage();
		ProfilePage profilePage = authenticationPage.login(TestData.EXISTING_USER_EMAIL,
				TestData.EXISTING_USER_PASSWORD);

		assertEquals("My account text is incorrect", TestData.MY_ACCOUNT_TEXT, profilePage.getHeaderText());
		assertEquals("Incorrect account name.", TestData.FULL_NAME_2, profilePage.getAccountNameText());
		assertTrue("Incorrect welcome message text.",
				profilePage.getWelcomeToAccountMessageText().contains(TestData.WELCOME_TO_ACCOUNT_TEXT));
		assertTrue("Logout button is not displayed.", profilePage.isLogOutButtonDisplayed());
		assertTrue("Page URL doesn't contain the expected text.", getUrl().contains(TestData.MY_ACCOUNT_URL_PART));
	}

	@Test
	public void checkoutTest() {
		Homepage homepage = new Homepage();
		AuthenticationPage authenticationPage = homepage.navigateToAuthenticationPage();

		ProfilePage profilePage = authenticationPage.login(TestData.EXISTING_USER_EMAIL,
				TestData.EXISTING_USER_PASSWORD);
		CatalogPage catalogPage = profilePage.navigateToCatalogPage(TestData.WOMEN);
		ProductPage productPage = catalogPage.clickOnProductWithName(TestData.FADED_SHORT_SLEEVE_TSHIRTS);
		ShoppingCartPage shoppingCartPage = productPage.addProductAndNavigateToShoppingCart();
		OrderConfirmationPage orderConfirmationPage = shoppingCartPage.completeCheckoutProcess();

		assertEquals("Incorrect order completed confirmation text.", TestData.ORDER_CONFIRMATION_TEXT,
				orderConfirmationPage.getHeaderText());
		assertTrue("Shipping step (#4) in not completed.", orderConfirmationPage.isStep4Completed());
		assertTrue("Last step is not completed.", orderConfirmationPage.isLastStepCompleted());
		assertTrue("Incorrect order completed message.",
				orderConfirmationPage.getOrderCompletedMessageText().contains(TestData.ORDER_IS_COMPLETE_MESSAGE));
		assertTrue("Page URL doesn't contain the expected text.",
				getUrl().contains(TestData.ORDER_CONFIRMATION_URL_PART));
	}
}