package resources;

import java.io.IOException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class RequestResponseSpecifications 
{
	RequestSpecification initialReqSpec;
	getRequestBody grb = new getRequestBody();
	
	public RequestSpecification requestSpecification() throws IOException
	{
		initialReqSpec = new RequestSpecBuilder().setBaseUri(grb.getProperties("PLACE_BASE_URI"))
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		return initialReqSpec;
	}
	
	public RequestSpecification libraryAPIRequestSpecification()
	{
		initialReqSpec = new RequestSpecBuilder().setBaseUri("http://216.10.245.166").build();
		return initialReqSpec;
	}

}
