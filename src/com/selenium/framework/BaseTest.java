package com.selenium.framework;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public abstract class BaseTest extends FrameworkUtility {
	/*
	 * Creates an instance of ExtentReports Object and Creates report.html file
	 * in reports folder under the current project. report.html is appended by
	 * current date and time in dd_MM_YY_HH_MM_ss format. Every test run will
	 * create a new report and old reports are not being overwritten.
	 */
	
	@BeforeSuite
	public void initlizeExtentReportsObject() {
		System.out.println("Initializing extent reports..");
		e = new ExtentReports(FrameworkConstants.REPORT_PATH + getDateTimeStamp()+"_"+"report.html");
	}

	/*****************************************************************************************************************/
	@AfterSuite
	public void publishExtentReport() {
		test.log(LogStatus.INFO, "Publishing reports..");
		e.flush();
	}
	/************************************************************************************************************************/
	/*Starts the test.
	 * method.getName() returns the name of the current test method during run time.
	 * */
	@BeforeMethod
	public void startTest(Method method) {
		driver.get(URL);
		test = e.startTest(method.getName());
		test.log(LogStatus.INFO, "Starting test Script execution "+method.getName());
		test.log(LogStatus.INFO, "Launching URL '"+URL+"'");
	}
	/**
	 * @throws IOException ************************************************************************************************************/
	@AfterMethod
	public void logResults(ITestResult result, Method method) throws IOException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, "Test Script Passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			//Call a method to capture the screen shot
			String path = "./../../"+captureScreenShot(method.getName());
			test.log(LogStatus.FAIL, result.getThrowable());
			test.log(LogStatus.FAIL, "Captured ScreenShot: "+test.addScreenCapture(path));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "details");
		}
		System.out.println("Ending test..");
		test.log(LogStatus.INFO, "Ending test Script execution "+method.getName());
		e.endTest(test);
	}
	/**
	 * @throws NoSuchFieldException **************************************************************************************************************/
	
	@BeforeClass
	public void initilizeDriver() throws NoSuchFieldException {
		URL = readConfigurationFile("URL");
		if(URL.trim().length() > 0){
			String browserName = readConfigurationFile("BrowserName");
				if (browserName.trim().equalsIgnoreCase("firefox")) {
					System.setProperty(FrameworkConstants.GECKO_DRIVER_KEY,FrameworkConstants.FIREFOX_DRIVER_PATH);
					driver = new FirefoxDriver();
					driver.manage().window().maximize();
					initObjects();
				}else if (browserName.trim().equalsIgnoreCase("chrome")) {
					System.setProperty(FrameworkConstants.CHROME_DRIVER_KEY,FrameworkConstants.CHROME_DRIVER_PATH);
					driver = new ChromeDriver();
					driver.manage().window().maximize();
					initObjects();
				}else if (browserName.trim().equalsIgnoreCase("ie")) {
					System.setProperty(FrameworkConstants.IE_DRIVER_KEY,FrameworkConstants.IE_DRIVER_PATH);
					driver = new InternetExplorerDriver();
					driver.manage().window().maximize();
					initObjects();
				}else {
					System.out.println("Invalid BrowserName");
					throw new IllegalStateException("Failed to invoke WebBrowser.Invalid BrowserName..");
				}
	}
}
	
/****************************************************************************************************************/	
	@AfterClass
	public void closeApplication(){
		driver.quit();
		test.log(LogStatus.INFO, "Closing Browser");
	}
/****************************************************************************************************************/	
	/*Initialize objects of JS, jsActions, Actions and WindowActions classes*/
	public void initObjects() {
		js = (JavascriptExecutor) driver;
		jsActions = new JavaScriptActions();
		action = new Actions(driver);
		Wait = new CustomWait();
		WindowHandler = new WindowActions();
		AlertHandler = new AlertActions();
		KeyboardHandler = new KeyboardActions();
	}
}
/*****************************************************************************************************************/