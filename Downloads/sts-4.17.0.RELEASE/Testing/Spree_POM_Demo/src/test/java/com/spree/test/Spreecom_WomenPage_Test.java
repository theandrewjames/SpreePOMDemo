package com.spree.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spree.common.Spree_BaseSetup;
import com.spree.common.Spreecom_BasePage;
import com.spree.home.Spreecom_WomenPage;

public class Spreecom_WomenPage_Test extends Spree_BaseSetup{
	Spreecom_WomenPage womenPage;
	Spreecom_BasePage basePage;
	Spreecom_BasePage baseSetup;
	private WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = getDriver();
		basePage = new Spreecom_BasePage(driver);
	}
	
	@Test(priority = 1)
	public void validateWomenPage() throws Exception {
		test = extent.createTest("Test case 1", "verify woman page");
		womenPage = basePage.goToWomenPage();
		Thread.sleep(2000);
		womenPage.validateURL();
	}
	@Test(priority = 2)
	public void validateTitle() {
		womenPage.validateTitle();
	}
	@Test(priority = 3)
	public void validateText() {
		womenPage.validateAllTexts();
	}
}
