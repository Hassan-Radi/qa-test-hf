package com.hellofresh.challenge;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.hellofresh.util.DriverManager;

/**
 * Base test class for the common setup and driver creation for all the test cases.
 */
public class BaseTest {

	protected final static Logger LOGGER = Logger.getLogger(BaseTest.class.getName());

	@Before
	public void beforeMethod() {
		/**
		 * Reusing the same driver instance would save us some time for the browser initialization
		 * part. That's why we need to navigate to the homepage at the beginning of each test
		 */
		DriverManager.getDriver().get("http://automationpractice.com/index.php");
	}

	@After
	public void afterMethod() {
		/**
		 * delete all cookies in the session to be able to start the new test with no old data
		 */
		DriverManager.getDriver().manage().deleteAllCookies();
	}

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		WebDriver driver = new ChromeDriver();

		// set the driver instance globally so every class can reference it
		DriverManager.setWebDriver(driver);
	}

	@AfterClass
	public static void tearDown() {
		/**
		 * clear after the test is done executing by terminating the running browser instance to
		 * prevent memory leaks.
		 */
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
		}
	}
}