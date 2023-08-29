package com.spree.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Spree_MyAccount {
	WebDriver driver;
	
	@FindBy(xpath="//h3[text()='My Account']")
	WebElement txtMyAccount;
	
	public Spree_MyAccount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public boolean verifyMAccountURL() {
		String pageURL = driver.getCurrentUrl();
		String expectedPageURL = "https://demo.spreecommerce.org/account";
		return pageURL.contains(expectedPageURL);
	}

	public boolean verifyMyAccountPageText() {
		String pageText = txtMyAccount.getText();
		String expectedPageText = "My Account";
		return pageText.contains(expectedPageText);
	}
}
