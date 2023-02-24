package resources;

import java.util.ArrayList;
import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Location;
import pojo.addPlace;
import pojo.deletePlace;

public class getRequestBody 
{
	
	
	public static addPlace addPlacePayload(String name, String address, String language)
	{
				//creation of request body
				addPlace ap = new addPlace();
				ap.setAccuracy(50);
				ap.setAddress(address);
				ap.setLanguage(language);
				ap.setName(name);
				ap.setPhone_number("1234567890");
				ap.setWebsite("www.google.com");
				//create list and all it to setType
				List<String> types = new ArrayList<String>();
				types.add("shoe park");
				types.add("shop");
				ap.setTypes(types);
				//create location object and pass it to the ap object method
				Location l1= new Location();
				l1.setLat(-234.56);
				l1.setLng(45.67);
				ap.setLocation(l1);
				return ap;
				
	}
	
	public static addPlace addPlacePayload()
	{
				//creation of request body
				addPlace ap = new addPlace();
				ap.setAccuracy(50);
				ap.setAddress("Pune");
				ap.setLanguage("Marathi");
				ap.setName("Wagholi");
				ap.setPhone_number("1234567890");
				ap.setWebsite("www.google.com");
				//create list and all it to setType
				List<String> types = new ArrayList<String>();
				types.add("shoe park");
				types.add("shop");
				ap.setTypes(types);
				//create location object and pass it to the ap object method
				Location l1= new Location();
				l1.setLat(-234.56);
				l1.setLng(45.67);
				ap.setLocation(l1);
				return ap;
				
	}
	
	public static String getValueFromResponse(Response response, String key)
	{
		JsonPath js = new JsonPath(response.asString());
		String value = js.getString(key).toLowerCase();
		return value;
	}
	
//	public static deletePlace getDeleteAPIPayload(String placeId)
//	{
//		deletePlace dp = new deletePlace();
//		dp.setPlaceId1(placeId);
//		return dp;
//	}
	
	public static String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
	
	
	
}
