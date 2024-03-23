package com.Qa.AmaedeusApi.tests;


import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Qa.gorest.base.BaseTest;
import com.Qa.gorest.client.RestClient;



public class AmadeusAPITest extends BaseTest {
	
	private String accesstoken;
	
	@Parameters({"baseUri", "grant_type", "client_id", "client_secret"})
	@BeforeMethod
	public void getUserSetup(String baseUri, String grant_type, String client_id, String client_secret) {
	restClient=new RestClient(prop, baseUri);
	accesstoken=restClient.getAccessToken("/v1/security/oauth2/token", grant_type, client_id, client_secret);
	}
	
	@Test
	public void getFlightDetails() {
		RestClient restCtlientFlight=new RestClient(prop, baseUri);
		
		Map<String, String> headers=new HashMap<String, String>();
		headers.put("Authorization", "Bearer "+accesstoken);
		
		
		Map<String, Object>queryparam=new HashMap<String, Object>();
		queryparam.put("origin", "PAR");
		queryparam.put("maxPrice", 200);
		
				
				
		restCtlientFlight.get("/v1/shopping/flight-destinations", headers, queryparam, true, false)
				.then().log().all()
					.assertThat()
						.statusCode(200)
							.and()
								.extract()
									.response();
		
		
//         JsonPath priString = FlightDetails.jsonPath().get("data[0].type");	
//        System.out.println(priString.prettyPrint());
								
		
		
	}
}
