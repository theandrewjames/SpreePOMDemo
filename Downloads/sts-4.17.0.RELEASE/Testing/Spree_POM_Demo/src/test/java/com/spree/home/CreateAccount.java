package com.spree.home;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateAccount {
	private WebDriver driver;
	
	public CreateAccount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id = "account-button")
	WebElement accountbtn;
	
	@FindBy(xpath ="//a[normalize-space()='SIGN UP']")
	WebElement signUp;
	
	@FindBy(name = "spree_user[email]")
	WebElement email;
	
	@FindBy(id = "spree_user_password")
	WebElement pass;
	
	@FindBy(id = "spree_user_password_confirmation")
	WebElement confirmPass;
	
	@FindBy(name = "commit")
	WebElement signUpBtn;
	
	@FindBy(name = "commit")
	WebElement logInBtn;
	
	@FindBy(xpath ="//a[normalize-space()='LOGOUT']")
	WebElement logOut;
	
	@FindBy(xpath ="//a[normalize-space()='Log in']")
	WebElement regLogIn;
	
	@FindBy(xpath = "//h3[normalize-space()='Create new account']")
	WebElement titleCreateAcct;
	
	@FindBy(xpath = "//main[@id='content']//li[1]")
	WebElement emptyCredentials;
	
	public boolean verifyUrl() {
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/");
	}
		
	public boolean clickAccountButton () {
		accountbtn.click();
		signUp.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/signup");
	}
	
	public boolean createAcctTitleCheck() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return titleCreateAcct.isDisplayed();
	}
	
	public boolean clickSignUpBtn(String userName, String passWord) throws InterruptedException {
		System.out.println(userName);
		System.out.println(passWord);
		email.sendKeys(userName);
		pass.sendKeys(passWord);
		confirmPass.sendKeys(passWord);
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("users.txt", true)));
		    out.println(userName+"/"+passWord);
		    out.close();
		} catch (IOException e) {
			 System.out.println("An error occurred.");
		     e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		signUpBtn.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/account");
	}
	
	public boolean clickLogOut() throws InterruptedException {
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		logOut.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/login");
	}
	
	public boolean clickAlreadyHaveAccountBtn() throws InterruptedException {
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		signUp.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,400)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		regLogIn.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/login");
	}
	
	public boolean allEmptyCredentials() throws InterruptedException {
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		signUp.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		signUpBtn.click();
		return emptyCredentials.isDisplayed();
	}
	
	public boolean emptyEmail(String passWord) throws InterruptedException {
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		signUp.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		pass.sendKeys(passWord);
		confirmPass.sendKeys(passWord);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		signUpBtn.click();
		return emptyCredentials.isDisplayed();
	}
	
	public boolean emptyPassword(String userName) throws InterruptedException {
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		signUp.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		email.sendKeys(userName);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		signUpBtn.click();
		return emptyCredentials.isDisplayed();
	}
	
	public boolean confirmPassIncorrect(String userName, String passWord) throws InterruptedException {
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		signUp.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		email.sendKeys(userName);
		pass.sendKeys(passWord);
		confirmPass.sendKeys(passWord+"123");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		signUpBtn.click();
		return emptyCredentials.isDisplayed();
	}
	
	
}
