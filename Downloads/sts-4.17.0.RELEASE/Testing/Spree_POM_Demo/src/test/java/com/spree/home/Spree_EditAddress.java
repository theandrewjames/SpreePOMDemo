package com.spree.home;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Spree_EditAddress {
	WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"content\"]/div/div[1]/div[2]/div/div[2]/div/div[2]/a[1]")
	WebElement itemToEdit;
	
	@FindBy(xpath="//h3[text()='Edit Address']")
	WebElement editPageText;
	
	@FindBy(id="address_firstname")
	WebElement firstNameField;
	
	@FindBy(name="commit")
	WebElement updatebutton;
	
	@FindBy(xpath="//span[text()='Updated successfully']")
	WebElement updatedMessage;
	
	private String newName = "Andy";
	
	public Spree_EditAddress(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyEditAddressURL() throws InterruptedException {
		itemToEdit.click();
		Thread.sleep(2000);
		String pageURL = driver.getCurrentUrl();
		String expectedPageURL = "https://demo.spreecommerce.org/addresses/104272/edit";
		return pageURL.contains(expectedPageURL);
	}

	public boolean verifyEditAddressText() {
		String pageText = editPageText.getText();
		String expectedPageText = "Edit Address";
		return pageText.contains(expectedPageText);
	}
	public boolean editAddress() throws InterruptedException {
		firstNameField.sendKeys(newName);
		updatebutton.click();
		Thread.sleep(2000);
		String pageURL = driver.getCurrentUrl();
		String expectedPageURL = "https://demo.spreecommerce.org/account";
		Assert.assertEquals(pageURL.contains(expectedPageURL), true);
		return updatedMessage.isDisplayed();
	}
}
