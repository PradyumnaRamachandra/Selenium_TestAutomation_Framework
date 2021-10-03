package com.selenium.framework;

public interface FrameworkConstants {
	
	public static final String CONFIG_FILE_PATH = "./Test_Configuration/Config.properties";
	
	public static final String OR_FILE_PATH = "./Test_ObjectRepository/OR.properties";
	
	public static final String DATA_FILE_PATH = "./Test_Data/TestData.xls";
	
	public static final String FIREFOX_DRIVER_PATH = "./Test_Library/geckodriver.exe";
	
	public static final String GECKO_DRIVER_KEY = "webdriver.gecko.driver";
	
	public static final String CHROME_DRIVER_PATH = "./Test_Library/chromedriver.exe";
	
	public static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
	
	public static final String IE_DRIVER_PATH = "./Test_Library/IEDriverServer.exe";
	
	public static final String IE_DRIVER_KEY = "webdriver.ie.driver";
	
	public static final String SCREEN_SHOT_FOLDER_PATH = "./Test_Results/SnapShots/";
	
	public static final String REPORT_PATH = "./Test_Results/Reports/";
	
	public static final int OBJECT_LOAD_TIME_OUT = 60;
	
	public static final int PAGE_LOAD_TIME_OUT = 180;
}
