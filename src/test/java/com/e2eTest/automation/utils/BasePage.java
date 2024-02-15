package com.e2eTest.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * This class contains the declaration of driver. and contains the 
 * declaration of logger
 */
public class BasePage {
	
	protected WebDriver driver ;
	
	/**
	 * Instantiation of base page.
	 * @param driver
	 */
	
	public BasePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}

}
