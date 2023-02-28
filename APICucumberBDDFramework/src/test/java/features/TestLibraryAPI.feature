Feature: Test functionalities of library API

#This test case will create single book against given test case
@LibrayAPI
Scenario: Check if user is able to create a book with details provided by user in a file
Given user provided test case data "Book1" in excel sheet "BookDetails"
And AddBookAPI payload
When user hit "AddBookAPI" with "post" request
Then status code should be "200"
And value of data field "Msg" is "successfully added"
And user is able to see value of "ID"

#This test case will create all the books given in excel file
@LibrayAPIMultipleData
Scenario: Check if user is able to create a book with details provided by user in a file
Given all books created from data sheet "BookDetails"

