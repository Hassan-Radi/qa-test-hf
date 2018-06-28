package com.hellofresh.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.hellofresh.util.SelectOption;
import com.hellofresh.util.SelectionCriteria;

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
		titleMrsRadioButton.click();
		firstNameTextBox.sendKeys(firstName);
		lastNameTextBox.sendKeys(surName);
		passwordTextBox.sendKeys(password);
		new SelectOption(daysComboBox).selectBy(SelectionCriteria.SELECT_BY_VALUE, dateOfBirthDay);
		new SelectOption(monthsComboBox).selectBy(SelectionCriteria.SELECT_BY_VALUE, dateOfBirthMonth);
		new SelectOption(yearsComboBox).selectBy(SelectionCriteria.SELECT_BY_VALUE, dateOfBirthYear);
		companyTextBox.sendKeys(company);
		address1TextBox.sendKeys(address1);
		address2TextBox.sendKeys(address2);
		cityTextBox.sendKeys(city);
		new SelectOption(stateComboBox).selectBy(SelectionCriteria.SELECT_BY_VISIBLE_TEXT, state);
		postCodeTextBox.sendKeys(postCode);
		additionalInfoTextBox.sendKeys(additionalInfo);
		homePhoneTextBox.sendKeys(homephone);
		mobilePhoneTextBox.sendKeys(mobilePhone);
		addressAliasTextBox.sendKeys(addressAlias);

		registerButton.click();

		return new ProfilePage();
	}
}