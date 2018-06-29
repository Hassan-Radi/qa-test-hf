package com.hellofresh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hellofresh.util.SelectOption;
import com.hellofresh.util.SelectionCriteriaEnum;

/**
 * Page object that represents all the elements on the current website page
 */
public class RegisterAccount extends PageObject {

	public RegisterAccount() {
		/**
		 * Make sure that the page loaded successfully before interacting with the page
		 */
		wait.until(ExpectedConditions.visibilityOf(titleMrsRadioButton));
	}

	@FindBy(id = "id_gender2")
	private WebElement titleMrsRadioButton;

	@FindBy(id = "customer_firstname")
	private WebElement firstNameTextBox;

	@FindBy(id = "customer_lastname")
	private WebElement lastNameTextBox;

	@FindBy(id = "passwd")
	private WebElement passwordTextBox;

	@FindBy(id = "days")
	private WebElement daysComboBox;

	@FindBy(id = "months")
	private WebElement monthsComboBox;

	@FindBy(id = "years")
	private WebElement yearsComboBox;

	@FindBy(id = "company")
	private WebElement companyTextBox;

	@FindBy(id = "address1")
	private WebElement address1TextBox;

	@FindBy(id = "address2")
	private WebElement address2TextBox;

	@FindBy(id = "city")
	private WebElement cityTextBox;

	@FindBy(id = "id_state")
	private WebElement stateComboBox;

	@FindBy(id = "postcode")
	private WebElement postCodeTextBox;

	@FindBy(id = "other")
	private WebElement additionalInfoTextBox;

	@FindBy(id = "phone")
	private WebElement homePhoneTextBox;

	@FindBy(id = "phone_mobile")
	private WebElement mobilePhoneTextBox;

	@FindBy(id = "alias")
	private WebElement addressAliasTextBox;

	@FindBy(id = "submitAccount")
	private WebElement registerButton;

	/**
	 * Registers a new user given a set of values
	 * 
	 * @param firstName
	 *            User first name
	 * @param surName
	 *            User surname
	 * @param password
	 *            User password
	 * @param dateOfBirthDay
	 *            The day of birth
	 * @param dateOfBirthMonth
	 *            The month of birth
	 * @param dateOfBirthYear
	 *            The year of birth
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
	 * @param additionalInfo
	 *            User additional information for the address
	 * @param homephone
	 *            User phone number
	 * @param mobilePhone
	 *            User mobile phone number
	 * @param addressAlias
	 *            User alias
	 * @return A page object that represents the Profile page
	 */
	public ProfilePage registerUser(String firstName, String surName, String password, String dateOfBirthDay,
			String dateOfBirthMonth, String dateOfBirthYear, String company, String address1, String address2,
			String city, String state, String postCode, String additionalInfo, String homephone, String mobilePhone,
			String addressAlias) {
		LOGGER.info("Choosing Mrs. from the titles radio-buttons...");
		titleMrsRadioButton.click();
		LOGGER.info(String.format("Typing first name = %s...", firstName));
		firstNameTextBox.sendKeys(firstName);
		LOGGER.info("Typing surname = " + surName + "...");
		lastNameTextBox.sendKeys(surName);
		passwordTextBox.sendKeys(password);
		LOGGER.info(String.format("Selecting %s from the days combo-box...", dateOfBirthDay));
		new SelectOption(daysComboBox).selectBy(SelectionCriteriaEnum.SELECT_BY_VALUE, dateOfBirthDay);
		LOGGER.info(String.format("Selecting %s from the months combo-box...", dateOfBirthMonth));
		new SelectOption(monthsComboBox).selectBy(SelectionCriteriaEnum.SELECT_BY_VALUE, dateOfBirthMonth);
		LOGGER.info(String.format("Selecting %s from the years combo-box...", dateOfBirthYear));
		new SelectOption(yearsComboBox).selectBy(SelectionCriteriaEnum.SELECT_BY_VALUE, dateOfBirthYear);
		LOGGER.info(String.format("Typing company = %s...", company));
		companyTextBox.sendKeys(company);
		LOGGER.info(String.format("Typing address 1 = %s...", address1));
		address1TextBox.sendKeys(address1);
		LOGGER.info(String.format("Typing address 2 = %s...", address2));
		address2TextBox.sendKeys(address2);
		LOGGER.info(String.format("Typing city = %s...", city));
		cityTextBox.sendKeys(city);
		LOGGER.info(String.format("Selecting state %s from the combo-box...", state));
		new SelectOption(stateComboBox).selectBy(SelectionCriteriaEnum.SELECT_BY_VISIBLE_TEXT, state);
		LOGGER.info(String.format("Typing post code = %s...", postCode));
		postCodeTextBox.sendKeys(postCode);
		LOGGER.info(String.format("Typing additional info = %s...", additionalInfo));
		additionalInfoTextBox.sendKeys(additionalInfo);
		LOGGER.info(String.format("Typing home phone = %s...", homephone));
		homePhoneTextBox.sendKeys(homephone);
		LOGGER.info(String.format("Typing mobile phone = %s...", mobilePhone));
		mobilePhoneTextBox.sendKeys(mobilePhone);
		LOGGER.info(String.format("Typing address alias = %s...", addressAlias));
		addressAliasTextBox.sendKeys(addressAlias);

		LOGGER.info("Clicking on the Register button...");
		registerButton.click();

		return new ProfilePage();
	}
}