package com.spree.test;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.spree.home.Login;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
	WebDriver driver;
	Login login;
	private String username = "ana@spree.com";
	private String password = "ana123" ;
	private String incorrectPassword = "incorrectPassword" ;
	
	@Test
	public void loginFullTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		driver = new ChromeDriver(opt);
		login = new Login(driver);
		driver.manage().window().maximize();
		driver.get("http://demo.spreecommerce.org/");
		assertTrue(login.clickLogInButton());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		assertTrue(login.logIntoAccount(username, password));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		assertTrue(login.successfulLoginFlag());
		assertTrue(login.successfulLoginTitle());
		assertTrue(login.clickLogOutButton());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		assertTrue(login.clickLogOutFlag());
		//assertTrue(login.rememberMeBtn());
		assertTrue(login.noAccountSignUpClick());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		assertTrue(login.emptyCredentials());
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		assertTrue(login.incorrectCredentials(username, incorrectPassword));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		assertTrue(login.clickForgotPasswordBtn());
		assertTrue(login.forgotPasswordTitle());
		assertTrue(login.emptyCredentialsForgotPass());
		//assertTrue(login.successfulForgotPass());
	}
}
