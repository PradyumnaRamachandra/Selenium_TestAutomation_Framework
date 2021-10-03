package com.Searchpages.com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigDemo {
	static String path="./Test_Configuration/Config.properties";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties p = new Properties();
		p.load(new FileInputStream(path));
		
		//p.getProperty("URL");
		System.out.println(p.getProperty("URL"));
		System.out.println(p.getProperty("Username"));
		System.out.println(p.getProperty("Password"));
	}
	

}
