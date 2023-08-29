package com.spree.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spree.common.Spree_BaseSetup;
import com.spree.common.Spreecom_BasePage;
import com.spree.home.Spree_MyAccount;

public class Spreecom_MyAccount_Test  extends Spree_BaseSetup {
	Spree_MyAccount myaccount;
	Spreecom_BasePage basePage;
	Spree_BaseSetup baseSetup;
	private WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = getDriver();
		basePage = new Spreecom_BasePage(driver);
		basePage.login();
	}
	@AfterClass
	public void logout() {
		basePage.logout();
	}
	@Test
	public void verifyMyAccountUrl() throws InterruptedException {
		test = extent.createTest("Test case 1", "verify my account page");
		myaccount = basePage.goToMyAccount();
		Thread.sleep(2000);
		myaccount.verifyMAccountURL();
	}
	@Test
	public void verifyMyAccountText() throws InterruptedException {
		test = extent.createTest("Test case 2", "verify my account text");
		myaccount = basePage.goToMyAccount();
		Thread.sleep(2000);
		myaccount.verifyMyAccountPageText();
	}
}
