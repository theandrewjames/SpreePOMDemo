package com.spree.test;

import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.spree.home.CreateAccount;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAccountTest {
	WebDriver driver;
	CreateAccount account;
	private String passWord;
	private String incorrectUserName = "temp@spree.com";
	
	public Integer currentUser() {
		Random random = new Random();
		Integer x = random.nextInt(900)+100;
		return x;
	}
	
	@Test
	public void accountFullTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		driver = new ChromeDriver(opt);
		account = new CreateAccount(driver);
		driver.manage().window().maximize();
		driver.get("http://demo.spreecommerce.org/");
		Integer iter = currentUser();
		passWord = "ana"+iter.toString();
		assertTrue(account.verifyUrl());
		assertTrue(account.clickAccountButton());
		assertTrue(account.createAcctTitleCheck());
		//assertTrue(account.clickSignUpBtn());
		//assertTrue(account.clickLogOut());
		assertTrue(account.clickAlreadyHaveAccountBtn());
		assertTrue(account.allEmptyCredentials());
		assertTrue(account.emptyEmail(passWord));
		assertTrue(account.emptyPassword(incorrectUserName));
		assertTrue(account.confirmPassIncorrect(incorrectUserName, passWord));
	}
}
