package com.spree.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Spreecom_MenPage {
private WebDriver driver;
	
	@FindBy(xpath = "//div[@class='py-md-3 text-uppercase taxon-title'][normalize-space()='Men']")
	WebElement txtMen;
	
	@FindBy(xpath = "//a[@class='pt-4 pb-2 mb-2 pl-4 pt-4 pb-2'][normalize-space()='Shirts']")
	WebElement txtShirts;
	
	@FindBy(xpath = "//a[normalize-space()='T-shirts']")
	WebElement txtTshirts;
	
	@FindBy(xpath = "//a[@class='pt-4 pb-2 mb-2 pl-4 pt-4 pb-2'][normalize-space()='Sweaters']")
	WebElement txtSweaters;
	
	@FindBy(xpath = "//a[@class='pt-4 pb-2 mb-2 pl-4 pt-4 pb-2'][normalize-space()='Jackets and Coats']")
	WebElement txtJacketAndCoats;
	
	public Spreecom_MenPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void validateURL() {
		String expURL = "https://demo.spreecommerce.org/t/categories/men";
		String actURL = driver.getCurrentUrl();
		Assert.assertEquals(expURL, actURL);
	}
	
	public void validateTitle() {
		String expTitle = "Men - Spree Demo Site";
		String actTitle = driver.getTitle();
		Assert.assertEquals(expTitle, actTitle);
	}
	
	public void validateAllTexts() {
		txtMen.isDisplayed();
		txtShirts.isDisplayed();
		txtTshirts.isDisplayed();
		txtSweaters.isDisplayed();
		txtJacketAndCoats.isDisplayed();
		
	}
}
