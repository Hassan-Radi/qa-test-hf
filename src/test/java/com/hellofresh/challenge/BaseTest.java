package com.hellofresh.challenge;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Base test class for the common setup and driver creation for all the test cases.
 */
public class BaseTest {

	protected final static Logger LOGGER = Logger.getLogger(BaseTest.class.getName());
	protected static WebDriver driver;
	protected static WebDriverWait wait;

	@Before
	public void beforeMethod() {
		/**
		 * Reusing the same driver instance would save us some time for the browser initialization
		 * part
		 */
		driver.get("http://automationpractice.com/index.php");
	}

	@After
	public void afterMethod() {
		/**
		 * delete all cookies in the session to be able to start the new test with no old data
		 */
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10, 50);
	}

	@AfterClass
	public static void tearDown() {
		/**
		 * clear after the test is done executing by terminating the running browser instance to
		 * prevent memory leaks.
		 */
		if (driver != null) {
			driver.quit();
		}
	}
}