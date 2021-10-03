package com.Search.tests;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Searchpages.com.SearchPage;
import com.relevantcodes.extentreports.LogStatus;
import com.selenium.framework.BaseTest;
import com.selenium.framework.ExcelLib;

import jxl.read.biff.BiffException;

public class GoogleSearch extends BaseTest{
	
	@DataProvider()
	public Object[][] data() throws BiffException, IOException{
		ExcelLib lib=new ExcelLib("Reservation", this.getClass().getSimpleName());
		return lib.getTestdata();
		
	}
	
		@Test(dataProvider="data")
		public void TC01(String Username,String Password) throws InterruptedException{
			
		SearchPage page=new SearchPage(driver);
		page.GoogleSearch();
		test.log(LogStatus.PASS, test.addScreenCapture(page.captureScreenShot("Click Am lucky")));
		//page.captureScreenShot("Click Search Button");
			
		}
}
