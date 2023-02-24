package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.*;

public class apiTestDetails extends RequestResponseSpecifications
{
	//RequestSpecification initialReqSpec;
	RequestSpecification addPlaceReqSpec;
	ResponseSpecification responseSpec;
	Response response;
	APIResources apiResources;
	static String placeId;
	
	@Given("AddPlaceAPI payload")
	public void add_place_api_payload() 
	{
		addPlaceReqSpec = given().spec(requestSpecification())
				   .body(getRequestBody.addPlacePayload());	
	    						
	}

	@Given("AddPlaceAPI payload with {string}, {string} ,{string}")
	public void addplaceapi_payload_with(String name, String address, String language)
	{
		addPlaceReqSpec = given().spec(requestSpecification())
				   .body(getRequestBody.addPlacePayload(name, address, language));
	}
	
	@Given("get place api")
	public void get_place_api() 
	{
		addPlaceReqSpec = given().spec(requestSpecification());
	}


	@When("user hits AddPlaceAPI")
	public void user_hits_add_place_api() 
	{
		//ResponseSpecification responseSpec  = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	    
		response = addPlaceReqSpec.
				   when().post("/maps/api/place/add/json")
				   .then().extract().response();
		
	}
	
	
	@When("user hits {string} with httprequest {string}")
	public void user_hits_with_httprequest(String api, String requestType)
	{
		apiResources = APIResources.valueOf(api);
		
		if(requestType.equalsIgnoreCase("post"))
		{
			System.out.println("Resource received for post request" +apiResources.getResource());
			response = addPlaceReqSpec
					   .when().post(apiResources.getResource()).then().extract().response();
			placeId = getRequestBody.getValueFromResponse(response, "place_id");
			System.out.println("Place Id created = " +placeId);
		}
		
		else if(requestType.equalsIgnoreCase("get"))
		{
			System.out.println("Resource received for get request" +apiResources.getResource());
			response = addPlaceReqSpec.queryParam("place_id", placeId)
						.when().get(apiResources.getResource());
			
		}
		else if(requestType.equalsIgnoreCase("delete"))
		{
			System.out.println("Resource received for delete request" +apiResources.getResource());
			addPlaceReqSpec = given().spec(requestSpecification())
					   .body(getRequestBody.deletePlacePayload(placeId));	
			
			response = addPlaceReqSpec
						.when().delete(apiResources.getResource()).then().extract().response();
			
		}
		else
		{
			System.out.println("Please provide valid request type");
		}
		
		//execute complete request if request type is other than post -- as post has been executed separately in loop
//		if(!requestType.equalsIgnoreCase("post"))
//		{
//		response = response.then().extract().response();
//		//System.out.println(response.asString());
//		}
		
	}
	
	@Then("status code is {string}")
	public void status_code_is(String expectedStatusCode) 
	{
		String actualStatusCode = Integer.toString(response.statusCode());	
		System.out.println("Status code received from request ="  +actualStatusCode);
		assertEquals(expectedStatusCode,actualStatusCode);
    
	}

	@Then("value of {string} is {string}")
	public void value_of_is(String key, String expectedValue) 
	{
		String actualValue = getRequestBody.getValueFromResponse(response, key);
		assertEquals(expectedValue,actualValue);
	}
	
	@Then("user can see response")
	public void user_can_see_response() 
	{
		System.out.println("Response code received =" +response.getStatusCode());
	    System.out.println("Response received =" +response.asString());
	}
	

}
