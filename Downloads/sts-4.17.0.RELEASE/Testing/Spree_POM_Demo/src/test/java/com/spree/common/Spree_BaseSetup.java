package com.spree.common;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

@SuppressWarnings("deprecation")
public class Spree_BaseSetup {
	private WebDriver driver;
	static ExtentSparkReporter htmlReporter;
	protected static ExtentReports extent;
	protected static ExtentTest test;
	String filePath = "SCREENSHOTS";
	String extendReportPath = "/test-output/ExtentReportResults_Spreecom.html";

	public WebDriver getDriver() {
		return driver;
	}
	
	@BeforeTest
	public void SetUp(){
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + extendReportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("browser", "Chrome");
		
		htmlReporter.config().setDocumentTitle("Extend Report For Spreecom Tests");
		htmlReporter.config().setReportName("Extend Report For Spreecom Tests");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')' ");
	}

	private void setDriver(String browserType, String appURL) throws InterruptedException {
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		case "edge":
			driver = initEdgeDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Chrome as browser of choice..");
			driver = initChromeDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) throws InterruptedException {
		System.out.println("Launching google chrome with new profile..");
		//System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.navigate().to(appURL);
		Thread.sleep(3000);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) throws InterruptedException {
		System.out.println("Launching Firefox browser..");
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		Thread.sleep(3000);
		return driver;
	}
	
	private static WebDriver initEdgeDriver(String appURL) throws InterruptedException {
		System.out.println("Launching Edge browser..");
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		Thread.sleep(3000);
		return driver;
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeClass
	public void initializeTestBaseSetup(String browserType, String appURL) {
		try {
			setDriver(browserType, appURL);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		driver.quit();
		Thread.sleep(1000);
		extent.flush();
	}

	private File captureScreenshot(ITestResult result) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = null;
		if (ensureScreenshotDirectoryExists(System.getProperty("user.dir") + filePath)) {
			destination = new File(System.getProperty("user.dir") + filePath + "/" + result.getName() + "_"
					+ System.currentTimeMillis() + ".png");
			FileUtils.copyFile(source, destination);
			System.out.println("Screenshot taken @ " + destination);
		} else {
			System.out.println(filePath + "is not found");
		}
		return destination;

	}

	// It will execute after every test execution
	@AfterMethod
	public void tearDown(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED", ExtentColor.RED));
			test.fail(result.getThrowable().getMessage());
			try {
				File dest = captureScreenshot(result);
				test.info("Failed Method: " + result.getMethod().getMethodName());
				test.info("Failed Method description: " + result.getMethod().getDescription());
				test.info("Details:", MediaEntityBuilder.createScreenCaptureFromPath("SCREENSHOTS" + "/" + dest.getName()).build());

			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED", ExtentColor.GREEN));

		} else {
			test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED", ExtentColor.YELLOW));
			test.skip(result.getThrowable());
		}
	}

	private boolean ensureScreenshotDirectoryExists(String checkPath) {
		File parentDir = new File(getScreenshotDirParent(checkPath));
		return ((parentDir.canWrite()) || (parentDir.mkdirs()));
	}

	protected String getScreenshotDirParent(String checkPath) {
		return checkPath;
	}
}

