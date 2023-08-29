package com.spree.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Spreecom_SportswearPage {
	private WebDriver driver;
	
	@FindBy(xpath = "//div[@class='py-md-3 text-uppercase taxon-title'][normalize-space()='Sportswear']")
	WebElement txtSportswear;
	
	@FindBy(xpath = "//a[@class='pt-4 pb-2 mb-2 pl-4 pt-4 pb-2'][normalize-space()='Tops']")
	WebElement txtTops;
	
	@FindBy(xpath = "//a[@class='pt-4 pb-2 mb-2 pl-4 pt-4 pb-2'][normalize-space()='Sweatshirts']")
	WebElement txtSweatshirts;
	
	@FindBy(xpath = "//a[@class='pt-4 pb-2 mb-2 pl-4 pt-4 pb-2'][normalize-space()='Pants']")
	WebElement txtPants;
	
	public Spreecom_SportswearPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void validateURL() {
		String expURL = "https://demo.spreecommerce.org/t/categories/sportswear";
		String actURL = driver.getCurrentUrl();
		Assert.assertEquals(expURL, actURL);
	}
	
	public void validateTitle() {
		String expTitle = "Sportswear - Spree Demo Site";
		String actTitle = driver.getTitle();
		Assert.assertEquals(expTitle, actTitle);
	}
	
	public void validateAllTexts() {
		txtSportswear.isDisplayed();
		txtTops.isDisplayed();
		txtSweatshirts.isDisplayed();
		txtPants.isDisplayed();
	}
}
