package com.e2eTest.automation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class TearDown {

	/**
	 * This method is used to close browser. This method is called after the
	 * invocation of each test method in given class.
	 * 
	 * @After Method annotated with @After execute after every scenario.
	 */

	@After
	public void quitDriver(Scenario scenario) {

		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) Setup.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Screenshot: " + scenario.getName());

		}
		Setup.getDriver().quit();
		Setup.getLogger().info("scenario: " + scenario.getName() + "-finished.Status: " + scenario.getStatus());

	}

}
