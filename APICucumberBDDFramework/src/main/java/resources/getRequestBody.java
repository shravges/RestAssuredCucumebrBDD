package resources;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.Location;
import pojo.addPlace;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class getRequestBody 
{
	HashMap<String,String> testCaseData = new HashMap<String, String>();
	HashMap<String,HashMap<String,String>> allData = new HashMap<String,HashMap<String,String>>();
	HashMap<String,String> innerData = new HashMap<String, String>();
	
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
	
	
	public HashMap<String,String> getDataFromExcel(String testCaseName, String sheetName)
	{
		try
		{
		FileInputStream fis=new FileInputStream("C://InputData//AddBookAPI_InputData.xls");
		HSSFWorkbook workbook=new HSSFWorkbook(fis);
		HSSFSheet sheet =  workbook.getSheet(sheetName);
		
		System.out.println("Total rows in sheet" + sheetName + "are" + sheet.getLastRowNum());
		
		//assuming 0th row is headers row
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			Row headerRow = sheet.getRow(0);
			Row currentRow =  sheet.getRow(i);
			
			//check if 1st column == test case name provide by user
			if(currentRow.getCell(0).getStringCellValue().equalsIgnoreCase(testCaseName))
					{
						System.out.println("Test data found for mentioned test case");
						int totalCellCount = currentRow.getLastCellNum();
						System.out.println("Total columns =" +totalCellCount);
						for(int j=1;j<totalCellCount; j++)
						{
							String key = headerRow.getCell(j).getStringCellValue();
							String value = currentRow.getCell(j).getStringCellValue();
							testCaseData.put(key, value);
						}
					}
		}
		
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return testCaseData;
	}
	
	
	public HashMap<String, HashMap<String, String>> getAllDataFromExcel(String sheetName)
	{
		try
		{
		FileInputStream fis=new FileInputStream("C://InputData//AddBookAPI_InputData.xls");
		HSSFWorkbook workbook=new HSSFWorkbook(fis);
		HSSFSheet sheet =  workbook.getSheet(sheetName);
		
		System.out.println("Total rows in sheet" + sheetName + "are" + sheet.getLastRowNum());
		
		//assuming 0th row is headers row
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{
			HashMap<String,String> innerData = new HashMap<String, String>();
			Row headerRow = sheet.getRow(0);
			Row currentRow =  sheet.getRow(i);
			System.out.println("Considering data for test case" +currentRow.getCell(0).getStringCellValue());
			int totalCellCount = currentRow.getLastCellNum();
			//System.out.println("Total columns =" +totalCellCount);
			for(int j=1;j<totalCellCount; j++)
			{
				String key = headerRow.getCell(j).getStringCellValue();
				String value = currentRow.getCell(j).getStringCellValue();
				innerData.put(key, value);
			}
			
			allData.put(currentRow.getCell(0).getStringCellValue(), innerData);		
			
		}
		
		//System.out.println("All data received from excel =" +allData);
		
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return allData;
	}
	
	
	
	
}
	

