package com.Qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.Qa.gorest.base.BaseTest;
import com.Qa.gorest.client.RestClient;
import com.Qa.gorest.pojo.CreateUserPojo;
import com.Qa.gorest.utils.StringUtils;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetGoRestUserTest extends BaseTest{	
	
	@BeforeMethod
	public void getUserSetup() {
		restClient=new RestClient(prop, baseUri);
	}
	
	@Test
	public void getAllUser() {
		restClient.get("/public/v2/users/", true, true)
					.then().log().all()
						.statusCode(200);
	}
	
	@Test
	public void getSingleUser() {
		restClient.get("/public/v2/users/6814204", true, true)
					.then().log().all()
						.statusCode(200)
							.and()	
								.body("id", equalTo(6814204));
	}
	
	@Test
	public void getUsersWithQueryParam() {
			Map<String, Object> queryParam=new HashMap<String, Object>();
			queryParam.put("gender", "female");
			queryParam.put("status", "active");
			restClient.get("/public/v2/users", null, queryParam, false, true)
						.then().log().all()
							.assertThat()
								.statusCode(200);
		
	}
	

	

}
