package com.e2eTest.automation.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.e2eTest.automation.utils.BasePage;
import com.e2eTest.automation.utils.Setup;

public class CatatlogPage extends BasePage {
	
	/* TC_01 product */
	
	@FindBy(how = How.XPATH, using = "//p[normalize-space()='Catalog']")
	private static WebElement btnCatalog;

	@FindBy(how = How.XPATH, using = "//p[normalize-space()='Products']")
	private static WebElement btnProduct;

	@FindBy(how = How.XPATH, using = "//input[@id='SearchProductName']")
	private static WebElement Productname;

	@FindBy(how = How.XPATH, using = "//button[@id='search-products']")
	private static WebElement btnSearch;
	
	/* TC_02 Categories */

	@FindBy(how = How.XPATH, using = "//p[normalize-space()='Categories']")
	private static WebElement btnCategories;

	@FindBy(how = How.XPATH, using = "//a[normalize-space()='Add new']")
	private static WebElement btnAdd;

	@FindBy(how = How.XPATH, using = "//input[@id='Name']")
	private static WebElement name;

	@FindBy(how = How.XPATH, using = "//select[@id='ParentCategoryId']")
	private static WebElement parentCategoryId;

	@FindBy(how = How.XPATH, using = "//input[@title='file input']")
	private static WebElement btnuploadfile;

	@FindBy(how = How.XPATH, using = "//button[@name='save']")
	private static WebElement save;

	
	/* Constructor*/

	public CatatlogPage() {
		super(Setup.getDriver());
	}
	
	public static WebElement getBtnCatalog() {
		return btnCatalog;
	}

	public static WebElement getBtnProduct() {
		return btnProduct;
	}

	public static WebElement getproductName() {
		return Productname;
	}

	public static WebElement getBtnSearch() {
		return btnSearch;
	}
	
	public static WebElement getBtnCategories() {
		return btnCategories;
	}

	public static WebElement getBtnAdd() {
		return btnAdd;
	}

	public static WebElement getName() {
		return name;
	}

	public static WebElement getParentCategoryId() {
		return parentCategoryId;
	}

	public static WebElement getBtnuploadfile() {
		return btnuploadfile;
	}

	public static WebElement getBtnsave() {
		return save;
	}

}
