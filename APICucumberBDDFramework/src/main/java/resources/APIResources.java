package resources;

public enum APIResources 
{
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json"),
	AddBookAPI("/Library/Addbook.php");
	String resource;

	APIResources(String resource) 
	{
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}

}
