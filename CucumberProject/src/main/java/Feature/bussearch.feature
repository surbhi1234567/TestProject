Feature: RedBus.in Automation 

Background: Open the chrome and launch the application
Given Open the chrome 
Given Launch the application

Scenario Outline: Search Bus 
	When Search for Bus Tickets from Mumbai to Nasik 
	And Select the journey date as date "<date>" , month "<month>" and year "<year>"
	And Click on Search Buses button
	And Select Departure time as After 6 PM 
	And Select Bus Type as Non AC 
	And Select the 2 Available Seats 
	And Select the Boarding and Dropping Point 
	And Click on Proceed to Book 
	And On Passenger Details, Select I don't want insurance 
	Then Verify whether the Total Amount Displayed on Passenger Details is the same as displayed on Select the Boarding and Dropping Point

Examples:
|date|month|year|
|5|6|2019|
	
Scenario Outline: Search Hotel
	When Click on Hotels button
	And Search for Hotels in Mumbai
	And Select the journey date as date "<date>" , month "<month>" and year "<year>"
	And Click on Search button
	And Select location as "<location>"
	And Verify whether the results are getting displayed for Andheri, Mumbai Location

Examples:
|date|month|year|location|
|6|7|2019|Andheri|