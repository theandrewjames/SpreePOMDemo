package com.spree.home;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Spree_AddAddress {
	WebDriver driver;
	
	@FindBy(xpath="//h3[text()='New Address']")
	WebElement txtNewAddress;
	
	@FindBy(id="address_label")
	WebElement addressLabel;
	
	@FindBy(id="address_firstname")
	WebElement addressFirstName;
	
	@FindBy(id="address_lastname")
	WebElement addressLastName;
	
	@FindBy(id="address_address1")
	WebElement addressField;
	
	@FindBy(id="address_city")
	WebElement cityField;
	
	@FindBy(id="address_zipcode")
	WebElement zipField;
	
	@FindBy(id="address_phone")
	WebElement phoneField;
	
	@FindBy(name="commit")
	WebElement submit;
	
	@FindBy(xpath="//span[text()='New address has been successfully created']")
	WebElement addressSuccess;
	Random r = new Random();
	int randomInt = r.nextInt(100) + 1;
	public String addressName = "address" + randomInt;
	private String firstName = "Andrew";
	private String lastName = "V";
	private String address = "123 Main st";
	private String city = "Montgmery";
	private String state = "Alabama";
	private String zip = "20010";
	private String phone = "123456789";
	
	
	public String getAddressName() {
		return addressName;
	}

	public Spree_AddAddress(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyAddAddressURL() {
		String pageURL = driver.getCurrentUrl();
		String expectedPageURL = "https://demo.spreecommerce.org/addresses/new";
		return pageURL.contains(expectedPageURL);
	}

	public boolean verifyAddAddressText() {
		String pageText = txtNewAddress.getText();
		String expectedPageText = "New Address";
		return pageText.contains(expectedPageText);
	}
	public boolean addAddress() throws InterruptedException {
		addressLabel.sendKeys(addressName);
		addressFirstName.sendKeys(firstName);
		addressLastName.sendKeys(lastName);
		addressField.sendKeys(address);
		cityField.sendKeys(city);
		zipField.sendKeys(zip);
		phoneField.sendKeys(phone);
		submit.click();
		Thread.sleep(2000);
		String pageURL = driver.getCurrentUrl();
		String expectedPageURL = "https://demo.spreecommerce.org/account";
		Assert.assertEquals(pageURL.contains(expectedPageURL), true);
		return addressSuccess.isDisplayed();
	}
}
