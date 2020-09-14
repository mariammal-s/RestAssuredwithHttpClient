package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Util.Test_Util;
import com.qa.base.TestBase;
import com.qa.client.Rest_Client;

public class GetAPITest extends TestBase {
	
	TestBase testBase;
	Rest_Client restClient;
	String endPointURL;
	String apiURL;
	String url;
	CloseableHttpResponse httpResponse;
	
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		endPointURL = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		url = endPointURL+apiURL;
					
	}
	
	@Test(priority = 0)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restClient = new Rest_Client();
		httpResponse = restClient.get(url);
		
		//STATUS CODE
		
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code : "+statusCode);
				Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200 , "StatusCode is not 200");
				
				//Json String
				
				String response = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
				JSONObject responseJson = new JSONObject(response);
				System.out.println("Response Json from API : "+responseJson);
				
				//SINGLE VALUE ASSERTION
				
				//per_page
				String per_page_value = Test_Util.getValueByJPath(responseJson , "/per_page");
				System.out.println("Per_page value : "+per_page_value);
				
				//total value
				String total_value = Test_Util.getValueByJPath(responseJson , "/total");
				System.out.println("Total value : "+total_value);
				
				//get the value from JSONArray
				String lastName = Test_Util.getValueByJPath(responseJson , "/data[0]/last_name");
				String id = Test_Util.getValueByJPath(responseJson , "/data[0]/id");
				String firstName = Test_Util.getValueByJPath(responseJson , "/data[0]/first_name");
				String avatar = Test_Util.getValueByJPath(responseJson , "/data[0]/avatar");
				System.out.printf("First Name %s\nLastName %s\nid %s\navatar %s\n",firstName,lastName,id,avatar);
				
				
				
				//RESPONSE Headers
				
				Header[] headersArray = httpResponse.getAllHeaders();
				HashMap<String, String> responseHeaders = new HashMap<String, String>(); 
				
				for (Header h : headersArray) {
					responseHeaders.put(h.getName(), h.getValue());
				}
				
				System.out.println("Headers Array : "+ responseHeaders);
				
		
	}
	
	@Test(priority = 1)	
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		
		restClient = new Rest_Client();
		
		HashMap<String , String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		//headerMap.put("username", "test@test.com");
		//headerMap.put("password", "test123!");
				
		httpResponse = restClient.get(url,headerMap);
		
		//STATUS CODE
		
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code : "+statusCode);
				Assert.assertEquals(statusCode, testBase.RESPONSE_STATUS_CODE_200 , "StatusCode is not 200");
				
				//Json String
				
				String response = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
				JSONObject responseJson = new JSONObject(response);
				System.out.println("Response Json from API : "+responseJson);
				
				//SINGLE VALUE ASSERTION
				
				//per_page
				String per_page_value = Test_Util.getValueByJPath(responseJson , "/per_page");
				System.out.println("Per_page value : "+per_page_value);
				
				//total value
				String total_value = Test_Util.getValueByJPath(responseJson , "/total");
				System.out.println("Total value : "+total_value);
				
				//get the value from JSONArray
				String lastName = Test_Util.getValueByJPath(responseJson , "/data[0]/last_name");
				String id = Test_Util.getValueByJPath(responseJson , "/data[0]/id");
				String firstName = Test_Util.getValueByJPath(responseJson , "/data[0]/first_name");
				String avatar = Test_Util.getValueByJPath(responseJson , "/data[0]/avatar");
				System.out.printf("First Name %s\nLastName %s\nid %s\navatar %s\n",firstName,lastName,id,avatar);
				
				
				
				//RESPONSE Headers
				
				Header[] headersArray = httpResponse.getAllHeaders();
				HashMap<String, String> responseHeaders = new HashMap<String, String>(); 
				
				for (Header h : headersArray) {
					responseHeaders.put(h.getName(), h.getValue());
				}
				
				System.out.println("Headers Array : "+ responseHeaders);
		
	}

}
