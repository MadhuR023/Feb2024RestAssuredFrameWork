package com.Qa.gorest.tests;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;

import com.Qa.gorest.base.BaseTest;
import com.Qa.gorest.client.RestClient;
import com.Qa.gorest.pojo.CreateUserPojo;
import com.Qa.gorest.utils.StringUtils;

public class CreateUserTest extends BaseTest {
	
	@BeforeMethod
	public void getUserSetup() {
		restClient=new RestClient(prop, baseUri);
	}
	
	@Test
	public void createUser(String name, String gender, String status) {
				
			
			CreateUserPojo user= new CreateUserPojo(StringUtils.getRandomeEmailId(), name, gender, status);
			
			Integer userId=restClient.post("/public/v2/users/", "json", user, true, true)
						.then()
							.assertThat()
								.statusCode(201)
									.extract()
										.path("id");
			
			System.out.println(userId);
			
			//Verify the created user with get Method
			
			
					   restClient.get("/public/v2/users/"+userId, true, true)
					   					.then().log().all()
					   						.assertThat()
					   							.statusCode(200)
					   								.and()
					   									.body("id", equalTo(userId));
	}
}
