package com.spree.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Spreecom_WomenPage {
private WebDriver driver;
	
	@FindBy(xpath = "//div[@class='py-md-3 text-uppercase taxon-title'][normalize-space()='Women']")
	WebElement txtWomen;
	
	@FindBy(xpath = "//a[@class='pt-4 pb-2 mb-2 pl-4 pt-4 pb-2'][normalize-space()='Skirts']")
	WebElement txtSkirts;
	
	@FindBy(xpath = "//a[@class='pt-4 pb-2 mb-2 pl-4 pt-4 pb-2'][normalize-space()='Dresses']")
	WebElement txtDresses;
	
	@FindBy(xpath = "//a[normalize-space()='Shirts and Blouses']")
	WebElement txtShirtsAndBlouses;
	
	@FindBy(xpath = "//a[@class='pt-4 pb-2 mb-2 pl-4 pt-4 pb-2'][normalize-space()='Sweaters']")
	WebElement txtSweaters;
	
	@FindBy(xpath ="//a[normalize-space()='Tops and T-shirts']")
	WebElement txtTopAndTshirts;
	
	@FindBy(xpath = "//a[@class='pt-4 pb-2 mb-2 pl-4 pt-4 pb-2'][normalize-space()='Jackets and Coats']")
	WebElement txtJacketAndCoats;
	
	public Spreecom_WomenPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void validateURL() {
		String expURL = "https://demo.spreecommerce.org/t/categories/women";
		String actURL = driver.getCurrentUrl();
		Assert.assertEquals(expURL, actURL);
	}
	
	public void validateTitle() {
		String expTitle = "Women - Spree Demo Site";
		String actTitle = driver.getTitle();
		Assert.assertEquals(expTitle, actTitle);
	}
	
	public void validateAllTexts() {
		txtWomen.isDisplayed();
		txtSkirts.isDisplayed();
		txtDresses.isDisplayed();
		txtShirtsAndBlouses.isDisplayed();
		txtSweaters.isDisplayed();
		txtTopAndTshirts.isDisplayed();
		txtJacketAndCoats.isDisplayed();
	}
}
