package com.spree.home;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {
	private WebDriver driver;
	
	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "account-button")
	WebElement accountbtn;
	
	@FindBy(name = "spree_user[email]")
	WebElement email;
	
	@FindBy(id = "spree_user_password")
	WebElement pass;
	
	@FindBy(xpath ="//a[normalize-space()='LOGIN']")
	WebElement logIn;
	
	@FindBy(xpath ="//a[normalize-space()='LOGOUT']")
	WebElement logOut;
	
	@FindBy(xpath = "//span[normalize-space()='Signed out successfully.']")
	WebElement logOutFlag;
	
	@FindBy(name = "commit")
	WebElement logInBtn;
	
	@FindBy(xpath = "//span[normalize-space()='Logged in successfully']")
	WebElement validFlag;
	
	@FindBy(xpath = "//h3[normalize-space()='My Account']")
	WebElement title;
	
	@FindBy(xpath = "//span[normalize-space()='Invalid email or password.']") 
	WebElement invalidFlag;
	
	@FindBy(xpath = "//a[normalize-space()='Forgot password?']")
	WebElement forgotPassBtn;
	
	@FindBy(xpath = "//h3[normalize-space()='Forgot password?']")
	WebElement forgotPassTitle;
	
	@FindBy(name = "commit")
	WebElement resetBtn;
	
	@FindBy(xpath = "//main[@id='content']//li[1]")
	WebElement emptyFlag;
	
	@FindBy(className = "spree-checkbox")
	WebElement rememberBox;
	
	@FindBy(xpath = "//a[text()='Sign Up']")
	WebElement noAccountSignUp;
	
		
	public boolean clickLogInButton() throws InterruptedException {
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logIn.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/login");
	}

	public boolean logIntoAccount(String username, String password) throws InterruptedException {
		email.sendKeys(username);
		pass.sendKeys(password);
		rememberBox.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logInBtn.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/account");
	}
	
	public boolean successfulLoginFlag() {
		return validFlag.isDisplayed();
	}
	
	public boolean successfulLoginTitle() {
		return title.isDisplayed();
	}
	
	public boolean clickLogOutButton() throws InterruptedException {
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logOut.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/login");
	}
	
	public boolean clickLogOutFlag() throws InterruptedException {
		return logOutFlag.isDisplayed();
	}
	
	public boolean noAccountSignUpClick() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		noAccountSignUp.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/signup");
	}
	
	public boolean emptyCredentials() throws InterruptedException {
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logIn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		logInBtn.click();
		return invalidFlag.isDisplayed();
	}
	
	public boolean incorrectCredentials(String username, String password) throws InterruptedException {
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logIn.click();
		email.sendKeys(username);
		pass.sendKeys(password);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		logInBtn.click();
		return invalidFlag.isDisplayed();
	}
	
	public boolean clickForgotPasswordBtn() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		forgotPassBtn.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/password/recover");
	}
	
	public boolean forgotPasswordTitle() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return forgotPassTitle.isDisplayed();
	}
	
	public boolean emptyCredentialsForgotPass() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		resetBtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return emptyFlag.isDisplayed();
	}
	
	
	/*public boolean successfulForgotPass() throws InterruptedException {
		Thread.sleep(2000);
		email.sendKeys(username);
		resetBtn.click();
		Thread.sleep(2000);
		return emptyFlag.isDisplayed();
	}*/
}
	