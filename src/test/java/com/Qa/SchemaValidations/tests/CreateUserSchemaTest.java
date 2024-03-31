package com.Qa.SchemaValidations.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Qa.gorest.base.BaseTest;
import com.Qa.gorest.client.RestClient;
import com.Qa.gorest.pojo.CreateUserPojo;
import com.Qa.gorest.utils.StringUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;


public class CreateUserSchemaTest extends  BaseTest {
	
	@BeforeMethod
	public void getUserSetup() {
		restClient=new RestClient(prop, baseUri);
	}
	
	@Test
	public void createUserSchemaTest(String name, String gender, String status) {

		CreateUserPojo user= new CreateUserPojo(StringUtils.getRandomeEmailId(), name, gender, status);
		
		restClient.post("/public/v2/users/", "json", user, true, true)
					.then().log().all()
						.assertThat()
							.statusCode(201)
								.and()
									.body(matchesJsonSchemaInClasspath("createuserschema.json"));
	}
}
