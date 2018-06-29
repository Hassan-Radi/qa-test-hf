package com.hellofresh.challenge;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.hellofresh.constants.TestData;
import com.hellofresh.util.DriverManager;

/**
 * Base test class for the common setup and driver creation for all the test cases.
 */
public class BaseTest {

	protected final static Logger LOGGER = Logger.getLogger(BaseTest.class.getName());

	@Before
	public void beforeMethod() {
		/**
		 * - Reusing the same driver instance would save us some time for the browser initialization
		 * part. That's why we need to navigate to the homepage at the beginning of each test case
		 * 
		 * - The browser URL is to be provided as a config for each run, this allows the flexibility
		 * to run on different environments without changing the test cases that much.
		 */
		String url = System.getProperty(TestData.URL_PROPERTY);
		if (url == null || url.isEmpty()) {
			throw new RuntimeException("Failed to find a value for the config \"" + TestData.URL_PROPERTY + "\".");
		} else {
			DriverManager.getDriver().get(url);
			LOGGER.info("Starting a new test. Got a config value: " + TestData.URL_PROPERTY + " = " + url);
		}
	}

	@After
	public void afterMethod() {
		/**
		 * delete all cookies in the session to be able to start the new test with no old data
		 */
		DriverManager.getDriver().manage().deleteAllCookies();
		LOGGER.info("Cleaned all the session cookies to start running a new test.\n\n");
	}

	@BeforeClass
	public static void setUp() {
		WebDriver driver = null;

		/**
		 * Browser selection is done using the command line configs.
		 */
		String browser = System.getProperty(TestData.BROWSER_PROPERTY);
		if (browser == null || browser.isEmpty()) {
			throw new RuntimeException("Failed to find a value for the config \"" + TestData.BROWSER_PROPERTY + "\".");
		} else {
			if (browser.equalsIgnoreCase(TestData.FIREFOX_BROWSER)) {
				System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
				driver = new FirefoxDriver();
				LOGGER.info("Initializing Firefox browser...");
			} else if (browser.equalsIgnoreCase(TestData.CHROME_BROWSER)) {
				System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
				driver = new ChromeDriver();
				LOGGER.info("Initializing Chrome browser...");
			}
		}

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
			LOGGER.info("Terminating the driver session and killing the browser...");
		}
	}
}