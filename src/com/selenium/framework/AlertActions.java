package com.selenium.framework;

import com.relevantcodes.extentreports.LogStatus;

public class AlertActions extends BaseTest{
	
	public void isAlertPresent(){
		Wait.forAlertToBePresent();
		test.log(LogStatus.INFO, "Alert message exist");
	}
	
	public void isAlertPresent(int customTimeout){
		Wait.forAlertToBePresent(customTimeout);
	}
	
	public void acceptAlert(){
		isAlertPresent();
		alert = driver.switchTo().alert();
		alert.accept();
		test.log(LogStatus.INFO, "Alert message accepted");
	}
	
	public void dismissAlert(){
		isAlertPresent();
		alert = driver.switchTo().alert();
		alert.dismiss();
		test.log(LogStatus.INFO, "Alert message dismissed");
	}
	
	public String getAlertText(){
		isAlertPresent();
		alert = driver.switchTo().alert();
		String tempText = alert.getText().trim();
		alert.accept();
			if(tempText.length() > 0){
				return tempText;
			}
		return null;
	}
}
