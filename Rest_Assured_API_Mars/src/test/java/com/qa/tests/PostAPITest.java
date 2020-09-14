package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.Rest_Client;
import com.qa.data.Users;

public class PostAPITest extends TestBase {
	
	TestBase testBase;
	String endPointURL;
	String apiURL;
	String url;	
	Rest_Client restClient;
	CloseableHttpResponse httpResponse;
	
	
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		endPointURL = prop.getProperty("URL");
		apiURL = prop.getProperty("serviceURL");
		url = endPointURL+apiURL;
					
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new Rest_Client();
		
		//Header
		HashMap<String , String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//Entity/Request Payload
		
		//Jackson API
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader");
		
		//Object to JSON file
		mapper.writeValue(new File("C:\\Users\\jothi\\Desktop\\MarsWorkspace_\\Rest_Assured_API_Mars\\src\\main\\java\\com\\qa\\data\\User.json"), users);
		
		//Object to JSON in String
		String requestPayload = mapper.writeValueAsString(users);
		System.out.println(requestPayload);
		
		//POST CALL
		
		httpResponse = restClient.post(url, requestPayload, headerMap);
		
		//1.Status code:
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);
		
		//2.JsonString:
		
		String response = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		JSONObject responseJson = new JSONObject(response);
		System.out.println("Response Json from API : "+responseJson);
		
		//3.Validate the values
		Users userResObj = mapper.readValue(response, Users.class);
		System.out.println(userResObj);
		
		Assert.assertTrue(users.getName().equals(userResObj.getName()), "Name Assertion Passed");
		Assert.assertTrue(users.getJob().equals(userResObj.getJob()), "Name Assertion Passed");
		
		
		
		//RESPONSE Headers
		
		Header[] headersArray = httpResponse.getAllHeaders();
		HashMap<String, String> responseHeaders = new HashMap<String, String>(); 
		
		for (Header h : headersArray) {
			responseHeaders.put(h.getName(), h.getValue());
		}
		
		System.out.println("Headers Array : "+ responseHeaders);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
