package com.selenium.framework;

import org.openqa.selenium.WebElement;

public class JavaScriptActions extends BaseTest {
	
	public void scrollUp() {
		js.executeScript("window.scrollBy(0,-300)");
	}
	
	public void scrollDown() {
		js.executeScript("window.scrollBy(0,300)");
	}
	
	public void scrollRight() {
		js.executeScript("window.scrollBy(300,0)");
	}
	
	public void scrollLeft() {
		js.executeScript("window.scrollBy(-300,0)");
	}
	
	public void scrollToElement(WebElement webelement) {
		js.executeScript("arguments[0].scrollIntoView()", webelement);
	}
	
	public void pageDown() {
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void pageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}
	
	public void jsclickElement(WebElement webelement) {
		js.executeScript("arguments[0].click()", webelement);
	}
	
	public void jsenterText(WebElement webelement, String value) {
		webelement.clear();
		js.executeScript("arguments[0].value=arguments[1]", webelement,value);
	}
}
