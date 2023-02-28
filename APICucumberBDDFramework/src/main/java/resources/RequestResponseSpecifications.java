package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestResponseSpecifications 
{
	RequestSpecification initialReqSpec;
	
	public RequestSpecification requestSpecification()
	{
		initialReqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		return initialReqSpec;
	}
	
	
	public RequestSpecification libraryAPIRequestSpecification()
	{
		initialReqSpec = new RequestSpecBuilder().setBaseUri("http://216.10.245.166").build();
		return initialReqSpec;
	}

}
