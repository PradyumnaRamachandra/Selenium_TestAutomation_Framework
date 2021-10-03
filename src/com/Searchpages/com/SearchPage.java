package com.Searchpages.com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.selenium.framework.FrameworkUtility;

public class SearchPage extends FrameworkUtility {
	
	
	
	public SearchPage(WebDriver driver){
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(xpath="(//input[@name='btnI'])[2]")
	WebElement searchbutton;
	
	/*@FindBy(name="password")
	WebElement userpassword;
	
	@FindBy(name="login")
	WebElement login;*/
	
	public void GoogleSearch()
	{
		clickElement(searchbutton);
	
	}

}
