package stepDefination;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import resources.APIResources;
import resources.RequestResponseSpecifications;
import resources.getRequestBody;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class LibraryAPIDetails extends RequestResponseSpecifications
{
	HashMap<String,String> inputData = new HashMap<String,String>();
	getRequestBody grb = new getRequestBody();
	Response response;
	RequestSpecification reqSpec;
	APIResources apiResources;
	HashMap<String,HashMap<String,String>> allData = new HashMap<String,HashMap<String,String>>();
		
	
	
	@Given("all books created from data sheet {string}")
	public void all_books_created_from_data_sheet(String sheetName)
	{
		allData = grb.getAllDataFromExcel(sheetName);
		for (Map.Entry<String,HashMap<String,String>> entry : allData.entrySet())   
		{  
			System.out.println("All test data received =" +allData);
			//System.out.println("test case: " + entry.getKey() + ", value: " + entry.getValue());
			HashMap<String,String> innerData = entry.getValue();
			//System.out.println(innerData);
			
			Response response = given().baseUri("http://216.10.245.166").body(innerData).when().post("/Library/Addbook.php")
					.then().extract().response();
			
			System.out.println("ID created for Test case:" + entry.getKey() + " = " + getRequestBody.getValueFromResponse(response, "ID"));
			System.out.println("Message received for Test case:" + entry.getKey() + " = " +getRequestBody.getValueFromResponse(response, "Msg"));
			
		}  
	}

	
	@Then("user able to create book")
	public void user_able_to_create_book() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Given("user provided test case data {string} in excel sheet {string}")
	public void user_provided_test_case_data_in_excel_sheet(String testCase, String sheet) 
	{
		inputData = grb.getDataFromExcel(testCase, sheet);
		System.out.println(inputData);
	}
	
	@Given("AddBookAPI payload")
	public void addbookapi_payload()
	{
		reqSpec = given().spec(libraryAPIRequestSpecification()).body(inputData).log().body();
	    
	}
	
	@When("user hit {string} with {string} request")
	public void user_hit_with_request(String apiResource, String requestType)
	{
		apiResources = APIResources.valueOf(apiResource);
		
		if(requestType.equalsIgnoreCase("post"))
		{
			System.out.println("API resource received = " +apiResources.getResource());
		response = reqSpec.when().post(apiResources.getResource()).
					then().extract().response();
		}
	   
	}

	@Then("status code should be {string}")
	public void status_code_should_be(String expectedResponseCode) 
	{
	   assertEquals(expectedResponseCode, Integer.toString(response.getStatusCode()));
	   System.out.println("Response code received =" +Integer.toString(response.getStatusCode()));
	}

	@Then("user is able to see value of {string}")
	public void user_is_able_to_see_value_of(String keyToBeSearch) 
	{
		String actualValue = getRequestBody.getValueFromResponse(response, keyToBeSearch);
		System.out.println("Value of " +keyToBeSearch + " is" + " " +actualValue);
	}

	
	

}
