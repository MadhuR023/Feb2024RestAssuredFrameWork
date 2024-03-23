package com.Qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.Qa.gorest.client.RestClient;
import com.Qa.gorest.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	protected ConfigurationManager config;
	protected Properties prop;
	protected RestClient restClient;
	protected String baseUri;

	@Parameters({"baseUri"})
	@BeforeTest
	public void setUp(String baseUri) {
		RestAssured.filters(new AllureRestAssured());
		
		config=new ConfigurationManager();
		prop=config.initProp();
		this.baseUri=baseUri;
		//String baseUri = prop.getProperty("baseUri");
		//restClient=new RestClient(prop, baseUri);
	}

}
