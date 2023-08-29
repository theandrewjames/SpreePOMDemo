package com.spree.test;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import com.spree.home.CheckOut;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckOutTest {
	WebDriver driver;
	CheckOut out;
	private String username = "ana@spree.com";
	private String password = "ana123" ;
	@Test
	public void checkOutFullTest() throws InterruptedException, AWTException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions opt = new ChromeOptions();
		driver = new ChromeDriver(opt);
		out = new CheckOut(driver);
		driver.manage().window().maximize();
		driver.get("http://demo.spreecommerce.org/");
		assertTrue(out.SetUpAccount(username, password));
		assertTrue(out.checkPage());
		assertTrue(out.editShippingAddress());
		assertTrue(out.editBillingAddress());
		assertTrue(out.checkAddressSubmit());
		assertTrue(out.chooseDeliverMethod());
		assertTrue(out.checkDeliverySubmit());
		assertTrue(out.checkPaymentSubmit("Ana Diaz", "4758923475902375", "102023", "123"));
		assertTrue(out.checkConfirmSubmit());
		assertTrue(out.cartClick());
		assertTrue(out.cartPayCheck());
		//assertTrue(out.completeCheckPayment());	
	}
}
	
