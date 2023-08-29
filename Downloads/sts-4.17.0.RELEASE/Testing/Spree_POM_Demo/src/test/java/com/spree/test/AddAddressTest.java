package com.spree.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.spree.common.Spree_BaseSetup;
import com.spree.common.Spreecom_BasePage;
import com.spree.home.ShoppingCart;
import com.spree.home.Spree_AddAddress;

public class AddAddressTest extends Spree_BaseSetup{
	private WebDriver driver;
	ShoppingCart cart;
	Spreecom_BasePage basePage;
	Spree_AddAddress addAddress;
	
	
	@BeforeClass
	public void setUp() {
		driver = getDriver();
		basePage = new Spreecom_BasePage(driver);
		basePage.login();
		driver.findElement(By.linkText("Add new address")).click();
	}
	@AfterClass
	public void logout() {
		basePage.logout();
	}
	@Test(priority=1)
	public void validateMyAccountURL() throws Exception {
		test = extent.createTest("Test case 1", "verify my account page");
		addAddress = basePage.goToAddAddress();
		Thread.sleep(2000);
		addAddress.verifyAddAddressURL();
	}
	@Test(priority=2)
	public void validateMyAccountText() throws Exception {
		test = extent.createTest("Test case 2", "verify my account text");
		addAddress = basePage.goToAddAddress();
		Thread.sleep(2000);
		addAddress.verifyAddAddressText();
	}
	@Test(priority=3)
	public void addAddress() throws Exception {
		test = extent.createTest("Test case 3", "Add Address");
		addAddress = basePage.goToAddAddress();
		Thread.sleep(2000);
		addAddress.addAddress();
	}
}
