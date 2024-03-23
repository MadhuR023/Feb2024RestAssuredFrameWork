package com.Qa.gorest.client;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Properties;

import com.Qa.gorest.frameworkexceptions.APIFrameWorkExceptions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	private static RequestSpecBuilder specBuilder;
	private Properties prop;
	private String baseUri;
	private boolean hasAuthorized=false;
	
	public RestClient(Properties prop, String baseUri){
		specBuilder=new RequestSpecBuilder();
		this.prop=prop;
		this.baseUri=baseUri;
	}
	
//	private static final String BASE_URI="https://gorest.co.in";
//	private static final String BEARER_TOKEN="bde9dcbf1a296400f70f89fd84d74580c4ea2ef2d0c6c49a50aba85d7e89944f";
	
	//To avoid adding key and value with every createRequest, one generic method is created 
	public void addAuthorizationHeader() {
		if (!hasAuthorized) {
			specBuilder.addHeader("Authorization", "Bearer "+prop.getProperty("bearertoken"));
			hasAuthorized=true;
		}
			
	}
	
	//Switch method for different ContentType
	private void setContentType(String contenttype) {
		switch (contenttype.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;
		default:
			System.out.println("Please pass the right Content Type.");
			throw new APIFrameWorkExceptions("INVALIDCONTENTTYPE");
		}
	}
	
	//Create Basic Request
	private RequestSpecification createRequest(Boolean addauth) {
		specBuilder.setBaseUri(baseUri);
		if(addauth) {
		addAuthorizationHeader();
		}
		return specBuilder.build();		
	}
	
	//Create Request with Headers
	private RequestSpecification createRequest(Map<String, String> headerMap, Boolean addauth) {
		specBuilder.setBaseUri(baseUri);
		if (addauth) {
			addAuthorizationHeader();
		}
		if(headerMap!=null) {
			specBuilder.addHeaders(headerMap);
		}
		return specBuilder.build();		
	}
	
	//Create Request with Headers and Query Parameters
	private RequestSpecification createRequest(Map<String, String> headerMap, Map<String, Object> queryParam, Boolean addauth) {
		specBuilder.setBaseUri(baseUri);
		if (addauth) {
			addAuthorizationHeader();
		}
		if(headerMap!=null) {
			specBuilder.addHeaders(headerMap);
		}
		if (queryParam!=null) {
			specBuilder.addQueryParams(queryParam);
		}
		return specBuilder.build();		
	}
	
	//Create Request with Pojo Object and contentType
	private RequestSpecification createRequest(Object object, String contentType) {
		specBuilder.setBaseUri(baseUri);
		addAuthorizationHeader();
		setContentType(contentType);
		if (object!=null) {
			specBuilder.setBody(object);
		}
		return specBuilder.build();		
	}
	
	//Create Request with Object, ContentType and Headers
	private RequestSpecification createRequest(Object object, String contentType, Map<String, String>headerMap) {
		specBuilder.setBaseUri(baseUri);
		addAuthorizationHeader();
		setContentType(contentType);
		if(headerMap!=null) {
			specBuilder.addHeaders(headerMap);
		}
		if (object!=null) {
			specBuilder.setBody(object);
		}
		return specBuilder.build();		
	}
	
	//HTTP Request methods
	//GET METHODS
	
	public Response get(String serviceurl, boolean log, boolean addauth) {
		if (log) {
			return RestAssured.given(createRequest(addauth)).log().all()
							.when()
								.get(serviceurl);
		}
		return RestAssured.given(createRequest(addauth))
							.when()
								.get(serviceurl);		
	}
	
	public Response get(String serviceurl,Map<String, String>headerMap, boolean log, Boolean addauth) {
		if (log) {
			return RestAssured.given(createRequest(headerMap,addauth)).log().all()
							.when()
								.get(serviceurl);
		}
		return RestAssured.given(createRequest(headerMap,addauth))
							.when()
								.get(serviceurl);		
	}
	
	public Response get(String serviceurl,Map<String, String>headerMap,Map<String, Object> queryParam, boolean log, Boolean addauth) {
		if (log) {
			return RestAssured.given(createRequest(headerMap,queryParam, addauth)).log().all()
							.when()
								.get(serviceurl);
		}
		return RestAssured.given(createRequest(headerMap, queryParam, addauth))
							.when()
								.get(serviceurl);		
	}
	
	//POST METHODS
	public Response post(String serviceurl, String contenttype, Object requestBody, boolean log, boolean addauth) {
		if (log) {
			return RestAssured.given(createRequest(requestBody, contenttype)).log().all()
							.when()
								.post(serviceurl);
		}
		return RestAssured.given(createRequest(requestBody, contenttype))
								.when()
									.post(serviceurl);		
	}
	
	public Response post(String serviceurl, String contenttype, Object requestBody,Map<String, String>headerMap, boolean log) {
		if (log) {
			return RestAssured.given(createRequest(requestBody, contenttype, headerMap)).log().all()
							.when()
								.post(serviceurl);
		}
		return RestAssured.given(createRequest(requestBody, contenttype, headerMap))
								.when()
									.post(serviceurl);		
	}
	
	//PUT METHODS
	public Response put(String serviceurl, String contenttype, Object requestBody, boolean log) {
		if (log) {
			return RestAssured.given(createRequest(requestBody, contenttype)).log().all()
							.when()
								.put(serviceurl);
		}
		return RestAssured.given(createRequest(requestBody, contenttype))
								.when()
									.put(serviceurl);		
	}
	
	public Response put(String serviceurl, String contenttype, Object requestBody,Map<String, String>headerMap, boolean log) {
		if (log) {
			return RestAssured.given(createRequest(requestBody, contenttype, headerMap)).log().all()
							.when()
								.put(serviceurl);
		}
		return RestAssured.given(createRequest(requestBody, contenttype, headerMap))
								.when()
									.put(serviceurl);		
	}
	
	//PATCH METHODS
	public Response patch(String serviceurl, String contenttype, Object requestBody, boolean log) {
		if (log) {
			return RestAssured.given(createRequest(requestBody, contenttype)).log().all()
							.when()
								.patch(serviceurl);
		}
		return RestAssured.given(createRequest(requestBody, contenttype))
								.when()
									.patch(serviceurl);		
	}
	
	public Response patch(String serviceurl, String contenttype, Object requestBody,Map<String, String>headerMap, boolean log) {
		if (log) {
			return RestAssured.given(createRequest(requestBody, contenttype, headerMap)).log().all()
							.when()
								.patch(serviceurl);
		}
		return RestAssured.given(createRequest(requestBody, contenttype, headerMap))
								.when()
									.patch(serviceurl);		
	}
	
	//DELETE METHODS
	public Response delete(String serviceurl, boolean log, boolean addauth) {
		if (log) {
			return RestAssured.given(createRequest(addauth)).log().all()
							.when()
								.delete(serviceurl);
		}
		return RestAssured.given(createRequest(addauth))
								.when()
									.delete(serviceurl);		
	}
	
	
	//GET Oauth2.0 AccessTokens
	
	public String getAccessToken(String seviceUrl, String grant_type, String client_id, String client_secret) {
		
		RestAssured.baseURI="https://test.api.amadeus.com";
		
		String accessToken=given()
			.contentType(ContentType.URLENC)	
			.formParam("grant_type", grant_type)
			.formParam("client_id", client_id)
			.formParam("client_secret",client_secret )
				.when()
					.post(seviceUrl)
						.then()
							.assertThat()
								.statusCode(200)
									.extract()
										.path("access_token");
		return accessToken;
		
	}
	
	
}
