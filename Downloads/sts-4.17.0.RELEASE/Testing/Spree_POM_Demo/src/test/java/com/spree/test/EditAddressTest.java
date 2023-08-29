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
import com.spree.home.Spree_EditAddress;

public class EditAddressTest extends Spree_BaseSetup{
	private WebDriver driver;
	Spreecom_BasePage basePage;
	Spree_EditAddress editAddress;
	
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
	@Test(priority=1)
	public void validateEditAddressURL() throws Exception {
		test = extent.createTest("Test case 1", "verify edit page url");
		editAddress = basePage.goToEditAddress();
		Thread.sleep(2000);
		editAddress.verifyEditAddressURL();
	}
	@Test(priority=2)
	public void validateEditAddressText() throws Exception {
		test = extent.createTest("Test case 2", "verify edit page text");
		editAddress = basePage.goToEditAddress();
		Thread.sleep(2000);
		editAddress.verifyEditAddressText();
	}
	@Test(priority=3)
	public void editAddress() throws Exception {
		test = extent.createTest("Test case 3", "edit Address");
		editAddress = basePage.goToEditAddress();
		Thread.sleep(2000);
		editAddress.editAddress();
	}
}
