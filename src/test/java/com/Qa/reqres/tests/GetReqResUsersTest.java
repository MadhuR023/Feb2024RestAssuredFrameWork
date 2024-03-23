package com.Qa.reqres.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Qa.gorest.base.BaseTest;
import com.Qa.gorest.client.RestClient;

public class GetReqResUsersTest extends BaseTest {
	@BeforeMethod
	public void getUserSetup() {
		restClient=new RestClient(prop, baseUri);
	}
	
	@Test
	public void getAllUser() {
		restClient.get("/api/users/2", true, false)
					.then().log().all()
						.statusCode(200);
	}
}
