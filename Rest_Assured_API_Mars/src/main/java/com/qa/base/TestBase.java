package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_401 = 401;
	
	public TestBase() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\jothi\\Desktop\\MarsWorkspace_\\Rest_Assured_API_Mars\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	

}
