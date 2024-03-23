 package com.Qa.circuitapi.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Qa.gorest.base.BaseTest;
import com.Qa.gorest.client.RestClient;

public class GetCircuitUserTest extends BaseTest {
	
	@BeforeMethod
	public void getUserSetup() {
		restClient=new RestClient(prop, baseUri);
	}

	@Test
	public void getAllUser() {
		restClient.get("/api/f1/2017/circuits.json", true, false)
					.then().log().all()
						.statusCode(200);
	}
}
