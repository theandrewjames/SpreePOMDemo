package com.spree.home;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOut {
	private WebDriver driver;
	
	@FindBy(id = "account-button")
	WebElement accountbtn;
	
	@FindBy(name = "spree_user[email]")
	WebElement email;
	
	@FindBy(id = "spree_user_password")
	WebElement pass;
	
	@FindBy(xpath ="//a[normalize-space()='LOGIN']")
	WebElement logIn;
	
	@FindBy(name = "commit")
	WebElement logInBtn;
	
	@FindBy(xpath ="//a[normalize-space()='Women']")
	WebElement women;
	
	@FindBy(className = "product-component-name")
	WebElement item;
	
	@FindBy(xpath = "//button[@id='add-to-cart-button']")
	WebElement addToCart;
	
	@FindBy(xpath ="//a[normalize-space()='Checkout']")
	WebElement checkOutBtn;
	
	@FindBy(xpath = "//h1[normalize-space()='Checkout']")
	WebElement title;
	
	@FindBy(name = "commit")
	WebElement saveAndContinue;
	
	@FindBy(id = "name_on_card")
	WebElement cardName;
	
	@FindBy(id = "card_number")
	WebElement cardNum;
	
	@FindBy(id = "card_expiry")
	WebElement cardExp;
	
	@FindBy(id = "card_code")
	WebElement cardCode;
	
	@FindBy(name = "commit")
	WebElement placeOrder;
	
	@FindBy(xpath = "//span[normalize-space()='Bogus Gateway: Forced failure']") 
	WebElement completeFlag;
	
	@FindBy(xpath = "//a[@class='ml-1 d-inline-block']//*[name()='svg']//*[name()='path']")
	WebElement editBillingAddy;
	
	@FindBy(name = "commit")
	WebElement updateBtn;
	
	@FindBy(xpath = "//h3[normalize-space()='Edit Address']")
	WebElement titleEdit;
	
	@FindBy(className = "spree-checkbox")
	WebElement editShippingAddy;
	
	@FindBy(xpath = "//span[normalize-space()='UPS Two Day (USD)']")
	WebElement upsTwoDays;
	
	@FindBy(xpath = "//label[normalize-space()='Check']//span[@class='spree-radio-label-custom-input']")
	WebElement checkPayment;
	
	@FindBy(xpath = "//h5[normalize-space()='Order placed successfully']")
	WebElement validOrderTitle;
	
	@FindBy(xpath="//a[@class='text-uppercase truncate checkout-header-link-text']")
	WebElement cart;
	
	@FindBy(id = "checkout-link")
	WebElement cart_checkout;
	
	//label[normalize-space()='Check']//span[@class='spree-radio-label-custom-input']
	
	@FindBy(xpath = "//label[normalize-space()='Check']//span[@class='spree-radio-label-custom-input']")
	WebElement cartCheck;
	
	public CheckOut(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean SetUpAccount(String username, String password) throws InterruptedException, AWTException {
		Robot robot = new Robot();
		for(int i=0; i < 4; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
		}
		accountbtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		logIn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		email.sendKeys(username);
		pass.sendKeys(password);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		logInBtn.click();
		women.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		item.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		addToCart.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		checkOutBtn.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/checkout");
	}
	
	public boolean checkPage() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return title.isDisplayed() && driver.getCurrentUrl().equals("http://demo.spreecommerce.org/checkout");
	}
	
	public boolean editShippingAddress() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		editShippingAddy.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		return !editShippingAddy.isSelected();
	}
	
	public boolean editBillingAddress() throws InterruptedException {
		editBillingAddy.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return titleEdit.isDisplayed();
	}
	
	public boolean checkAddressSubmit() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		updateBtn.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		saveAndContinue.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/checkout/delivery");
	}
	
	public boolean chooseDeliverMethod() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		upsTwoDays.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return upsTwoDays.isEnabled();
	}
	
	public boolean checkDeliverySubmit() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		saveAndContinue.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/checkout/payment");
	}
	
	public boolean checkPaymentSubmit(String name, String num, String exp, String code) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		cardName.clear();
		cardName.sendKeys(name);
		cardNum.clear();
		cardNum.sendKeys(num);
		cardExp.clear();
		cardExp.sendKeys(exp);
		cardCode.clear();
		cardCode.sendKeys(code);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		saveAndContinue.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/checkout/confirm");
	}
	
	public boolean checkConfirmSubmit() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		placeOrder.click();
		return completeFlag.isDisplayed();
	}
	public boolean checkPaymentCheck() throws InterruptedException {
		checkPayment.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return checkPayment.isSelected();
	}
	
	public boolean completeCheckPayment() throws InterruptedException {
		saveAndContinue.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return validOrderTitle.isDisplayed();
	}
	
	public boolean cartClick() throws InterruptedException {
		cart.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		cart_checkout.click();
		return driver.getCurrentUrl().equals("http://demo.spreecommerce.org/checkout");
	}
	
	public boolean cartPayCheck() throws InterruptedException {
		cartCheck.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		saveAndContinue.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return validOrderTitle.isDisplayed();
	}
}
