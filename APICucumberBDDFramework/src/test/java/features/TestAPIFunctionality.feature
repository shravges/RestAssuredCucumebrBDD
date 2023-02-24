Feature: Test if the APIs are functioning as expected

@API_Test
Scenario Outline: Test Add Place API with customized data
Given AddPlaceAPI payload with "<name>", "<address>" ,"<language>"
When user hits "AddPlaceAPI" with httprequest "POST" 
Then status code is "200"
And value of "status" is "ok"
And user hits "GetPlaceAPI" with httprequest "GET"
And value of "name" is "<name>"
And user can see response
When user hits "DeletePlaceAPI" with httprequest "DELETE"
And status code is "200"
And value of "status" is "ok"
And user can see response
Examples:
|name|address|language|
|wagholi|pune|marathi|
|london|uk|english|
|indore|mp|hindi|


@1API_Test1
Scenario: Test Add Place API with normal data
Given AddPlaceAPI payload 
When user hits "AddPlaceAPI" with httprequest "POST" 
Then status code is "200"
And value of "status" is "ok" 

@1API_Test1
Scenario: Check if place has been created with earlier name
Given get place api
When user hits "GetPlaceAPI" with httprequest "GET" 
Then status code is "200"
And user can see response

@1API_Test1
Scenario: Check if place has been deleted with earlier name
Given get place api
When user hits "GetPlaceAPI" with httprequest "GET" 
Then status code is "200"
And user can see response






