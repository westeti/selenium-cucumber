package com.e2eTest.automation.utils;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Setup {

	private static WebDriver driver;
	private static final Logger LOGGER = (Logger) LogManager.getLogger(Setup.class.getName());
	
/**
 * This method is used to open browser.
 *  This method is called before the invocation of each test method	in the given class.
 *   In this method we need to pass browser name which will invoke the the respective driver 
 *   
 *   @throws MalformedURL.Exception the malformed exception
 *   @Before method annotated with @Before will execute before every scenario.
 */
@Before
	public void setWebDriver(Scenario scenario) {
		
		LOGGER.info("Scenario: " + scenario.getName()+ "- started");
		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}
		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
			break;

		case "firefox":
			FirefoxProfile profile = new FirefoxProfile();
			FirefoxOptions firefoxoption = new FirefoxOptions();
			firefoxoption.setCapability("platform", Platform.WIN11);
			firefoxoption.setProfile(profile);
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
			break;

		case "edge":
			driver = new EdgeDriver();
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		break;
		
		default:
			throw new IllegalArgumentException("Browser\""+ browser + "\" is not supported.");
			

		}
	}
	
	/*GETTER*/
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Logger getLogger() {
		return LOGGER ;
	}

}
