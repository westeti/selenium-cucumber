package com.e2eTest.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

/**
 * This class is used to perform various kinds of validations in the test cases.
 */
public class Validations extends BasePage {

	private static final Logger LOGGER = (Logger) LogManager.getLogger(SeleniumUtils.class.getName());

	/** testCaseStatus the status of the test case. */
	boolean testCaseStatus = true;

	/** test status. */
	boolean testStatus;

	/** test screenshot dir. */
	private String testScreenshotDir;

	/**
	 * Instanciation de assertions.
	 */
	public Validations() {
		super(Setup.getDriver());
	}

	/**
	 * method verify whether element is present on screen.
	 *
	 * @param targetElement element to be present
	 * @return true if element is present else throws exception
	 */
	public Boolean isElementPresent(By targetElement) {
		return Setup.getDriver().findElements(targetElement).size() > 0;
	}

	/**
	 * methode Checks if is element displayed.
	 *
	 * @param element element web
	 * @return boolean
	 */
	public Boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	/**
	 * methode Checks if is element selected
	 *
	 * @param element
	 * @return boolean
	 */
	public Boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}

	/**
	 * methode Checks if is element enabled.
	 *
	 * @param element
	 * @return boolean
	 */
	public Boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	/**
	 * method verify whether element is not present on screen.
	 *
	 * @param targetElement element not to be present
	 * @return true if element is not present else throws exception
	 */
	public Boolean isElementNotPresent(By targetElement) {
		return Setup.getDriver().findElements(targetElement).size() == 0;
	}

	/**
	 * method to take screenshot.
	 *
	 * @return path where screenshot has been saved
	 */
	public String screenShot() {
		String screenshotPath = "screenshot"
				+ new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss", Locale.FRANCE).format(new GregorianCalendar().getTime())
				+ ".png";

		LOGGER.info(screenshotPath);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(testScreenshotDir + screenshotPath));
		} catch (IOException e) {
			LOGGER.info("Exception: ", e);
			screenshotPath = "";
		}
		return screenshotPath;
	}

	/**
	 * methode Verif PDF text file.
	 *
	 * @param text
	 * @param pdfPath
	 * @return true, si c'est vrai
	 * @throws IOException Signal qu'une execption de type I/O s'est produite.
	 */
	public boolean verifyTextInFile(String text, String pdfPath) throws IOException {

		boolean exist = false;
		File file = new File(pdfPath);
		FileInputStream fis = new FileInputStream(file);
		PDFParser parser = new PDFParser(fis);

		parser.parse();

		COSDocument cosDoc = parser.getDocument();
		PDDocument pdDoc = new PDDocument(cosDoc);
		PDFTextStripper strip = new PDFTextStripper();
		String data = strip.getText(pdDoc);

		exist = data.contains(text);

		cosDoc.close();
		pdDoc.close();

		LOGGER.info("Text Found on the pdf File...");
		return exist;

	}

	/**
	 * methode Column contains value.
	 *
	 * @param pathToFile
	 * @param columnIndex
	 * @param targetValue
	 * @return true, si c'est vrai
	 * @throws IOException Signal qu'une execption de type I/O s'est produite.
	 */
	public boolean columnContainsValue(String pathToFile, int columnIndex, String targetValue) throws IOException {

		Reader in = new FileReader(pathToFile);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);

		for (CSVRecord record : records) {
			String rowValue = record.get(columnIndex);
			if (targetValue.equals(rowValue))
				return true;
		}
		return false;
	}

    /**
     * Assert command for checking the url in selenium webdriver
     * @param string URL
     */
    public void checkChangedURL(String expectedURL)
    {
    	String URL = driver.getCurrentUrl();
    	Assert.assertEquals(URL, expectedURL);
    }
    
    /**
     * method to verify the actual value with expected value.
     *
     * @param actual              actual text displayed
     * @param expected            expected text to be displayed
     * @param message             message should be displayed on failure of assertion
     * @param screenshotOnFailure
     * @param exitOnFailure
     * @return true, si c'est vrai
     */
    public boolean verifyEquals(Object actual, Object expected, String message, boolean screenshotOnFailure, boolean exitOnFailure)
    {
        testStatus = true;
        Reporter.log("<br>");
        try
        {
            Assert.assertEquals(actual, expected, message);
            Reporter.log("<Font Color=#008000> PASS </Font>" + message);

        }
        catch (AssertionError e)
        {

            testStatus = false;

            if (screenshotOnFailure)
            {

                Reporter.log("<Font Color=red> FAIL </Font> " + message + " Actual: " + actual + " Expected: " + expected
                    + " Please check the screenshot " + "<a href='" + screenShot()
                    + "'> <Font Color=red> here </Font> </a>");

            }

            if (exitOnFailure)
            {
                Reporter.log("<br>");
                Reporter.log("Exiting this function as exitOnFail flag is set to True. Will move to next test.");
                throw e;
            }

            Reporter.log("<Font Color=red> FAIL </Font> " + message + " Actual: " + actual + " Expected: " + expected);

        }
        return testStatus;
    }

    /**
     * method to verify if the condition is true.
     *
     * @param condition           statement to verify
     * @param message             message should be displayed on failure of assertion
     * @param screenshotOnFailure true if screenshot has to be taken in case of failure
     * @param exitOnFailure       true if execution to be stopped in case of failure
     * @return true if assertion passes, false if fails
     */
    public boolean verifyTrue(boolean condition, String message,
        boolean screenshotOnFailure, boolean exitOnFailure)
    {

        Reporter.log("<br>");

        try
        {

            Assert.assertTrue(condition, message);
            Reporter.log("<Font Color=#008000> PASS </Font>" + message);

        }
        catch (AssertionError e)
        {
            LOGGER.info(message);
            this.testCaseStatus = false;

            if (screenshotOnFailure)
            {
                Reporter.log("<Font Color=red> FAIL </Font> " + message + " Actual: FALSE Expected: TRUE."
                    + " Please check the screenshot " + "<a href='" + screenShot()
                    + "'> <Font Color=red> here </Font> </a>");

            }
            else
            {

                Reporter.log("<Font Color=red> FAIL </Font> " + message);

            }

            if (exitOnFailure)
            {
                Reporter.log("<br>");

                Reporter.log("Exiting this function as exitOnFail flag is set to True.");

                throw e;

            }

        }

        return this.testCaseStatus;
    }

    /**
     * methode Check field is empty.
     * @param elementAttr
     */
    public void checkFieldIsEmpty(WebElement elementAttr)
    {
        WebElement inputText = elementAttr;
        String text = inputText.getAttribute("value");

        if (text.isEmpty())
        {
        	LOGGER.info("input box is empty");
        }
    }

    /**
     * methode Text exist find elemnts.
     * @param elementAttr 
     */
    public void textExistFindElemnts(List<WebElement> elementAttr)
    {
        List<WebElement> list = elementAttr;

        if (list.size() > 0)
        {
        	LOGGER.info("Text is present.");
        }
        else
        {
        	LOGGER.info("Text is not present.");
        }
    }

    /**
     * methode Text pg source to check if text exisit in field.
     * @param text 
     */
    public void textPgSource(String text)
    {
        if (driver.getPageSource().contains(text))
        {
            LOGGER.info("Text is present.");
        }
        else
        {
        	LOGGER.info("Text is not present.");
        }
    }



}
