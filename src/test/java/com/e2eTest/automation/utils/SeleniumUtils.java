package com.e2eTest.automation.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



	/**
	 * This class contains all the methods required by selenium to perform actions
	 * on webelement. It is a repository so that same code need not to be written
	 * again.
	 */
	public class SeleniumUtils extends BasePage {

		/** properties. */
		protected Properties properties;

		/** config fis. */
		protected FileInputStream configFis;

		/** driver. */
		private static WebDriver driver;

		private static final Logger LOGGER = (Logger) LogManager.getLogger(SeleniumUtils.class.getName());

		/**
		 * Instanciation de common utils.
		 */
		public SeleniumUtils() {
			super(driver);
			SeleniumUtils.driver = Setup.getDriver();
		}

		/**
		 * methode Locator value : to locate web element.
		 * 
		 * @param locatorTpye String locators
		 * @param value       the locator value
		 * @return byLocator
		 */
		public By locatorValue(String locatorTpye, String value) {
			By byLocator;
			final By obj = null;
			switch (locatorTpye) {
			case "id":
				byLocator = By.id(value);
				break;
			case "name":
				byLocator = By.name(value);
				break;
			case "xpath":
				byLocator = By.xpath(value);
				break;
			case "css":
				byLocator = By.cssSelector(value);
				break;
			case "linkText":
				byLocator = By.linkText(value);
				break;
			case "partialLinkText":
				byLocator = By.partialLinkText(value);
				break;
			default:
				byLocator = obj;
				break;
			}
			return byLocator;
		}

		/**
		 * method to open specified url.
		 *
		 * @param url to open
		 */
		public void get(String url) {
			driver.get(url);
		}

		/**
		 * method to navigate to specified page.
		 *
		 * @param url navigation url
		 */
		public void navigate(String url) {
			driver.navigate().to(url);
		}

		/**
		 * method to click on an element with action class.
		 *
		 * @param element to be clicked
		 */
		public void clickOnElementUsingActions(By element) {
			Actions actions = new Actions(driver);
			actions.moveToElement(Setup.getDriver().findElement(element));
			actions.click().perform();
		}

		/**
		 * method to click on an element using javascript.
		 *
		 * @param element to be clicked
		 */
		public void clickOnElementUsingJs(WebElement element) {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement webElement = driver.findElement((By) element);
			jsExecutor.executeScript("arguments[0].click();", webElement);
		}

		/**
		 * Accesseur de l attribut title.
		 *
		 * @return title
		 */
		public String getTitle() {
			return driver.getTitle();
		}

		/**
		 * Accesseur de l attribut page source.
		 *
		 * @return page source
		 */
		public String getPageSource() {
			return driver.getPageSource();
		}

		/**
		 * method to find an element.
		 *
		 * @param locator element to be found
		 * @return WebElement if found else throws NoSuchElementException
		 */
		public WebElement findElement(By locator) {
			try {
				return Setup.getDriver().findElement(locator);
			} catch (NoSuchElementException e) {
				LOGGER.info(this.getClass().getName(), "findElement", "Element not found " + locator);
				String message = e.getMessage();
				LOGGER.info(message);
				throw new NoSuchElementException(message);
			}
		}

		/**
		 * method to find all the elements of specific locator.
		 *
		 * @param locator element to be found
		 * @return return the list of elements if found else throws
		 *         NoSuchElementException
		 */
		public List<WebElement> findElements(By locator) {
			try {
				return Setup.getDriver().findElements(locator);
			} catch (NoSuchElementException e) {
				LOGGER.info(this.getClass().getName(), "findElements", "element not found" + locator);
				throw new NoSuchElementException(e.getMessage());
			}
		}

		/**
		 * method to accept alert, exception is thrown if no alert is present.
		 */
		public void acceptAlert() {
			try {
				Alert alert = driver.switchTo().alert();
				alert.accept();

			} catch (NoAlertPresentException e) {
				throw new NoAlertPresentException();
			}
		}

		/**
		 * Accesseur de l attribut alert text.
		 *
		 * @return alert text
		 */
		public String getAlertText() {
			try {
				Alert alert = driver.switchTo().alert();
				return alert.getText();
			} catch (NoAlertPresentException e) {
				throw new NoAlertPresentException();
			}
		}

		/**
		 * methode Wait for element to be clickable.
		 *
		 * @param locator
		 */
		public void waitForElementToBeClickable(By locator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		/**
		 * method to clear field.
		 *
		 * @param element
		 */
		public void clearField(WebElement element) {
			try {
				element.clear();
			} catch (Exception e) {
				LOGGER.info(String.format("The following element could not be cleared: [%s]", element.getText()));
			}
		}

		/**
		 * Methode click on element.
		 *
		 * @param elementAttr
		 */
		public void click(WebElement elementAttr) {
			if (elementAttr.getClass().getName().contains("By")) {
				waitForElementToBeClickable(elementAttr);
				driver.findElement((By) elementAttr).click();
			} else {
				elementAttr.click();
			}
		}

		/**
		 * This method is used to click on button.
		 * 
		 * @param locatorType type of locators
		 * @param value       the locator value
		 */
		public void click(String locatorType, String value) {
			try {
				By locator;
				locator = locatorValue(locatorType, value);
				WebElement element = driver.findElement(locator);
				waitForElementToBeClickable(locator);
				element.click();
			} catch (NoSuchElementException e) {
				LOGGER.info("No Element Found to enter text", e);
			}
		}

		/**
		 * This method is used to enter the text by using sendkeys method.
		 * 
		 * @param locatorType that can be id / name / any other locator
		 * @param value       the locator value
		 * @param text        the data that you want to pass into the text field
		 */
		public void writeText(String locatorType, String value, String text) {
			try {
				By locator;
				locator = locatorValue(locatorType, value);
				WebElement element = driver.findElement(locator);
				clearField(element);
				element.sendKeys(text);
			} catch (NoSuchElementException e) {
				LOGGER.info("No Element Found to enter text", e);
			}
		}

		/**
		 * methode write text.
		 *
		 * @param elementAttr
		 * @param text
		 */
		public void writeText(WebElement elementAttr, String text) {
			if (elementAttr.getClass().getName().contains("By")) {
				clearField(elementAttr);
				driver.findElement((By) elementAttr).sendKeys(text);
			} else {
				clearField(elementAttr);
				elementAttr.sendKeys(text);
			}
		}

		/**
		 * methode read text.
		 *
		 * @param elementAttr
		 * @return string
		 */
		public String readText(WebElement elementAttr) {
			if (elementAttr.getClass().getName().contains("By")) {
				return driver.findElement((By) elementAttr).getText();
			} else {
				return elementAttr.getText();
			}
		}

		/**
		 * methode Auto suggest : Below is the code to select the Option based on the
		 * string passed in the Test. We are List as option can be more than one. By
		 * iterating the list we will select the required option.
		 *
		 * @param elementWeb
		 * @param writeText
		 * @param textToSelect
		 * @throws InterruptedException the interrupted exception
		 */
		public void autoSuggest(WebElement elementWeb, String writeText, String textToSelect) throws InterruptedException {
			// start input in input field
			elementWeb.sendKeys(writeText);
			Thread.sleep(3000);
			// get the list of suggestet inputs
			List<WebElement> options = driver.findElements(By.tagName("li"));
			// loop through list of inputs an click specific Text + break out the loop
			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase(textToSelect)) {
					option.click();
					break;
				}
			}
		}

		/**
		 * methode Switch to new window.
		 *
		 * @param driver
		 * @param iframeId
		 * @return web element
		 */
		public WebElement switchToNewIframe(WebDriver driver, String iframeId) {
			driver.switchTo().frame(iframeId);
			return driver.switchTo().activeElement();
		}

		/**
		 * methode Check radio.
		 *
		 * @param driver
		 * @param by
		 */
		public void checkRadio(WebDriver driver, By by) {
			WebElement inputElement = driver.findElement(by);
			inputElement.click();
		}

		/**
		 * methode Mouse hover.
		 *
		 * @param elementAttr
		 */
		public void mouseHover(WebElement elementAttr) {
			Actions actions = new Actions(driver);

			WebElement mouseHover = elementAttr;

			actions.moveToElement(mouseHover).perform();
		}

		/**
		 * this method is used to close browser.
		 */
		public void closeBrowser() {
			driver.quit();
		}

		/**
		 * methode Wait for element to be clickable
		 *
		 * @param locator
		 */
		public void waitForElementToBeClickable(WebElement locator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		/**
		 * methode Select option with text.
		 *
		 * @param textToSelect
		 * @param elementWeb
		 * @param locator
		 */
		public void selectOptionWithText(String textToSelect, WebElement elementWeb, By locator) {
			try {
				WebElement autoOptions = elementWeb;

				List<WebElement> optionsToSelect = autoOptions.findElements(locator);
				for (WebElement option : optionsToSelect) {
					if (option.getText().equals(textToSelect)) {
						LOGGER.info("Trying to select: " + textToSelect);
						option.click();
						break;
					}
				}

			} catch (NoSuchElementException e) {
				LOGGER.info(e.getStackTrace());
			} catch (Exception e) {
				LOGGER.info(e.getStackTrace());
			}
		}

		/**
		 * methode Encoder string : Une méthode pour chiffrer les données.
		 *
		 * @param realStr
		 * @return string
		 */
		public static String encoderString(String realStr) {
			byte[] encodedStr = Base64.getEncoder().encode(realStr.getBytes());
			return (new String(encodedStr));
		}

		/**
		 * methode Decoder string :Une méthode pour déchiffrer les données.
		 *
		 * @param encodedStr
		 * @return string
		 */
		public static String decoderString(String encodedStr) {
			byte[] decoderString = Base64.getDecoder().decode(encodedStr.getBytes());
			return (new String(decoderString));
		}

		/**
		 * method to resize browser
		 */
		public void resizeBrowser(int x, int y) {
			Dimension dimension = new Dimension(x, y);
			driver.manage().window().setSize(dimension);
		}

		/**
		 * verify color
		 * @return 
		 */
		public String verifyColor(WebElement elementWeb) {
			WebElement elt = elementWeb;
			// obtain color in rgba
			String colorPropertyValue  = elt.getCssValue("color");
			
			// convert rgba to hex
			String hexColor = Color.fromString(colorPropertyValue ).asHex();
			System.out.println("Color is :" + colorPropertyValue );
			System.out.println("Hex code for color:" + hexColor);
			return hexColor;
		}
		/**
		 * verify color saif
		 */
		
		  public void verifyElementColor(WebElement element, String expectedColor) {
		      

		        WebElement elt = element;

		        Color actualColor = getRgbColor(elt.getCssValue("color"));
		        Color expectedRgbColor = getRgbColor(expectedColor);

		        Assert.assertEquals(expectedRgbColor, actualColor);
		    }

		    private Color getRgbColor(String colorPropertyValue) {
		        int red = Integer.parseInt(colorPropertyValue.substring(4, 7));
		        int green = Integer.parseInt(colorPropertyValue.substring(9, 12));
		        int blue = Integer.parseInt(colorPropertyValue.substring(14, 17));
		        return new Color(red, green, blue, blue);
		    }
		
		/**
		 * perform a double click
		 */
		public void doubleClick(WebElement elt) {
			Actions act = new Actions(Setup.getDriver());
			WebElement ele = elt;
			act.doubleClick(ele).perform();
		}

		/**
		 * perform a drag and drop
		 */
		public void dragAndDrop(WebElement from, WebElement to) {
			Actions act = new Actions(Setup.getDriver());
			act.dragAndDrop(from, to).perform();
		}
		
	    /**
	     * methode Upload file with robot.
	     *
	     * @param imagePath path of the file
	     */
	    public void uploadFile( String imagePath)
	    {
	    	
	        StringSelection stringSelection = new StringSelection(imagePath);
	        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        clipBoard.setContents(stringSelection, null);

	        Robot robot = null;
	        try
	        {
	            robot = new Robot();
	        }
	        catch (AWTException e)
	        {
	        	LOGGER.info(e);
	        }

	        robot.delay(250);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.delay(150);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	    }
	    /**
		 * This method is used to select value from dropdown list by visible text. Here
		 * we need to have two parameters locator type and string text.
		 * 
		 * @param element element to be selected
		 * @param text    The exactly displayed text of a particular option
		 */
		public void selectDropDownListByVisibleText(WebElement element, String text) {
			Select dropDownList = new Select(element);
			dropDownList.selectByVisibleText(text);
		}

		/**
		 * This method is used to select value from dropdown list by index. Here we need
		 * to have two parameters locator type and option index.
		 * 
		 * @param element element to be selected
		 * @param index   the index of the option to be selected
		 */
		public void selectDropDownListByIndex(WebElement element, int index) {
			Select dropDownList = new Select(element);
			dropDownList.selectByIndex(index);
		}

		/**
		 * This method is used to select value from dropdown list by value. Here we need
		 * to have two parameters locator type and string text.
		 * 
		 * @param element element to be selected
		 * @param value   value of the “value” attribute
		 */
		public void selectDropDownListByValue(WebElement element, String value) {
			Select dropDownList = new Select(element);
			dropDownList.selectByValue(value);
		}
	
}
