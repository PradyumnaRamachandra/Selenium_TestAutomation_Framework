package com.selenium.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class FrameworkUtility {
	
	public static WebDriver driver;
	protected Select select;
	protected static Actions action;
	protected Alert alert;
	protected static Properties properties;
	protected static String URL;
	protected static ExtentReports e;
	protected static ExtentTest test;
	protected static JavaScriptActions jsActions;
	protected static JavascriptExecutor js;
	protected static WebDriverWait wait;
	protected static CustomWait Wait;
	protected static WindowActions WindowHandler;
	protected static AlertActions AlertHandler;
	protected static KeyboardActions KeyboardHandler;

	public String readConfigurationFile(String key) throws NoSuchFieldException {
		try{
			properties = new Properties();
			properties.load(new FileInputStream(FrameworkConstants.CONFIG_FILE_PATH));
			return properties.getProperty(key).trim();	
		} catch (Exception e){
			throw new NoSuchFieldException("Cannot find key: "+key+" in Config file.");
		}
	}
	
	public void enterText(WebElement webelement, String value) {
		Wait.forElementToBeVisibleAndEnabled(webelement);
		webelement.clear();
		webelement.sendKeys(value);
		test.log(LogStatus.INFO, value +" Entered Value in text field");
	}
	
	public void clickElement(WebElement webelement) {
		Wait.forElementToBeVisibleAndEnabled(webelement);
		webelement.click();
		test.log(LogStatus.INFO, "Clicked on WebElement");
	}
	
	public void selectCheckBox(WebElement webelement, String status) {
		Wait.forElementToBeVisibleAndEnabled(webelement);
		if (status.equalsIgnoreCase("On")) {
			if (!webelement.isSelected()) {
				clickElement(webelement);
				test.log(LogStatus.INFO, "Checkbox Checked");
			} 
		}else if(status.equalsIgnoreCase("Off")) {
			if (webelement.isSelected()) {
				clickElement(webelement);
				test.log(LogStatus.INFO, "Checkbox Un-Checked");
			}
		}
	}
	
	public void selectItemFromListBoxByValue(WebElement webelement, String value) throws InterruptedException  {
		value = value.trim();
		Wait.forElementToBeVisibleAndEnabled(webelement);
		select = new Select(webelement);
		select.selectByValue(value);
		test.log(LogStatus.INFO, value+" Selected from listbox");
	}
	
	public void selectItemFromListBoxByText(WebElement webelement, String Text) throws InterruptedException{
		Text = Text.trim();
		Wait.forElementToBeVisibleAndEnabled(webelement);
		select = new Select(webelement);
		select.selectByVisibleText(Text);
		test.log(LogStatus.INFO, Text+" Selected from listbox");
	}
	
	public void selectItemFromListBoxByIndex(WebElement webelement, int index) throws InterruptedException{
		Wait.forElementToBeVisibleAndEnabled(webelement);
		select = new Select(webelement);
		select.selectByIndex(index);
		test.log(LogStatus.INFO, index+" Selected from listbox");
	}
	
	public void selectMultipleItemsFromListBoxByVisibleText(WebElement webelement, String Text) throws InterruptedException {
		String[] tempelements = Text.split(",");
		Wait.forElementToBeVisibleAndEnabled(webelement);
		select = new Select(webelement);
		select.deselectAll();
		for(int i=0; i < tempelements.length; i++) {
			select.selectByVisibleText(tempelements[i].trim());
		}
	}
	
	public void selectRadioButton(List<WebElement> webelement, String value) {
		value = value.trim();
		boolean flag = false;
			if(webelement.size() > 0){
				for (WebElement element : webelement) {
					if (element.getAttribute("value").equalsIgnoreCase(value)) {
						clickElement(element);
						flag = true;
						test.log(LogStatus.INFO, "Radio Button Selected");
						break;
					}
				}
			}

		if (flag == false) {
			throw new NoSuchElementException("Cannot locate radio button:"+ "'"+value+"'");
		}
	}
	
	public List<WebElement> getWebElements(By by) {
		return driver.findElements(by);
	}
	
	/*Returns the number of elements on the webpage*/
	public int getNumberOfElementsOnPage(By by) {
		return driver.findElements(by).size();
	}
	
	public String getCurrentUrl() {
	    return driver.getCurrentUrl();
	 }
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public String captureScreenShot(String stepName) {
		String destpath = null;
		try{
				File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				destpath = FrameworkConstants.SCREEN_SHOT_FOLDER_PATH+stepName+"_"+getDateTimeStamp()+".png";
				FileUtils.copyFile(srcFile, new File(destpath));
				
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return destpath;
	}
	
	public void doubleClick(WebElement webelement) {
		Wait.forElementToBeVisibleAndEnabled(webelement);
		action.doubleClick(webelement).build().perform();
		test.log(LogStatus.INFO, "Double Clicked on element");
	}
	
	public String getDateTimeStamp() {
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_YYYY_HH_mm_ss");
		String strDate = dateFormat.format(new Date());
		return strDate;
	}
	
	 public String getTimeStamp() {
		    Date date = new Date();
		    long timeCurrent = date.getTime();
		    return String.valueOf(timeCurrent);
	 }
	
	 public String getRandomDigits(int randomdigitlength) {
		 String timeStamp = getTimeStamp();
		 System.out.println(timeStamp);
		 return  timeStamp.substring(timeStamp.length()-randomdigitlength,timeStamp.length());
	}
	 
	 public void killProcessByName(String processName) throws IOException {
			Runtime rt = Runtime.getRuntime();
			rt.exec("taskkill /F /IM "+processName);
	}
		
	public void refreshPage() throws TimeoutException{
		driver.navigate().refresh();	
	}
	
	public String getWebElementRGBColorCode(WebElement webelement) {
		return webelement.getCssValue("background-color");
	}
	
}
