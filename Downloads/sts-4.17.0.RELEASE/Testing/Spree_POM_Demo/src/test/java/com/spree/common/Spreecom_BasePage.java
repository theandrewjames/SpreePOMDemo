package com.spree.common;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.spree.home.Spree_AddAddress;
import com.spree.home.Spree_EditAddress;
import com.spree.home.Spree_MyAccount;
import com.spree.home.Spreecom_MenPage;
import com.spree.home.Spreecom_SportswearPage;
import com.spree.home.Spreecom_WomenPage;


public class Spreecom_BasePage {
private WebDriver driver;
	

	@FindBy(xpath = "//a[@class='nav-link main-nav-bar-item main-nav-bar-category-button dropdown-toggle'][normalize-space()='Women']")
	WebElement linkWomen;
	
	@FindBy(xpath = "//a[@class='nav-link main-nav-bar-item main-nav-bar-category-button dropdown-toggle'][normalize-space()='Men']")
	WebElement linkMen;
	
	@FindBy(xpath = "//a[@class='nav-link main-nav-bar-item main-nav-bar-category-button dropdown-toggle'][normalize-space()='Sportswear']")
	WebElement linkSportswear;
	
	@FindBy(xpath="//div[contains(@class,'dropdown navbar-right-dropdown')]")
	WebElement profile;
	
	@FindBy(id="spree_user_email")
	WebElement unameField;
	
	@FindBy(id="spree_user_password")
	WebElement passwordField;
	
	@FindBy(name="commit")
	WebElement login;
	
	@FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div[2]/h4/a")
	WebElement addAddress;
	
	private String username = "andrew@spree.com";
	private String password = "password";
	private String getUrlStem() {return driver.getCurrentUrl().split("\\?")[0];}

	public WebDriver getDriver() {
		return driver;
	}

	public void login() {
		profile.click();
		driver.findElement(By.linkText("LOGIN")).click();
		unameField.sendKeys(username);
		passwordField.sendKeys(password);
		login.click();
		//return getUrlStem().equals("http://demo.spreecommerce.org/login");
	}
	public void logout() {
		profile.click();
		driver.findElement(By.linkText("LOGOUT")).click();
	}
	public Spreecom_BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public Spreecom_WomenPage goToWomenPage() {
		linkWomen.click();
		return new Spreecom_WomenPage(driver);
	}
	
	public Spreecom_MenPage goToMenPage() {
		linkMen.click();
		return new Spreecom_MenPage(driver);
	}
	
	public Spreecom_SportswearPage goToSportswearPage() {
		linkSportswear.click();
		return new Spreecom_SportswearPage(driver);
	}
	public Spree_MyAccount goToMyAccount() throws InterruptedException {
		Thread.sleep(2000);
		return new Spree_MyAccount(driver);
	}
	public Spree_AddAddress goToAddAddress() {
		return new Spree_AddAddress(driver);
	}
	public Spree_EditAddress goToEditAddress() {
		return new Spree_EditAddress(driver);
	}
}