Feature: Verify the functionality of Assign Vehicle

  Background: Generate Bearer Token from Username and Password
    Given Give Username and Password for Access Token
      | username | 101201      |
      | password | Dmart@12345 |

#  @AssignVehicle
  Scenario: Assign Vehicle for a Delivery with valid data
    And Provide delivery number, dock and vehicle details to assign vehicle for a delivery
      | dlvNumber     | 9109792974 |
      | dock          | A3         |
      | vehicleType   | ACE        |
      | vehicleRegNum | MH02MB9876 |
    When User calls the assign vehicle's endpoint to assign vehicle for a delivery
    Then verify that status code be equal to "200"

  @VehicleAssign
  Scenario: Verify vehicle is assigned for a Delivery with valid data
    And Provide dock and get delivery number to which vehicle is to be assigned
      | dock | ACE |
    And Provide vehicle type and vehicle number for a delivery to be assigned vehicle
      | vehicleType | vehicleRegNum |
      | ACE         | MH02MB9876    |
    When User calls the assign vehicle's endpoint to assign vehicle for a delivery
    Then verify that status code be equal to "200"

#  @AssignVehicle
  Scenario: Re-assign new Truck to same delivery
    And Provide dock and get delivery number to which vehicle is to be assigned
      | dock | A3 |
    And Provide vehicle type and vehicle number for a delivery to be assigned vehicle
      | vehicleType | vehicleRegNum |
      | Toras       | MH04FJ6467    |
    When User calls the assign vehicle's endpoint to assign vehicle for a delivery
    Then verify that status code be equal to "200"

#  @AssignVehicle
  Scenario: Assign vehicle by Changing vehicleType for Confirmed Delivery
    And Provide dock and get delivery number to which vehicle is to be assigned
      | dock | A3 |
    And Provide vehicle type and vehicle number for a delivery to be assigned vehicle
      | vehicleType | vehicleRegNum |
      | Toras       | MH04FJ6467    |
    When User calls the assign vehicle's endpoint to assign vehicle for a delivery
    Then verify that status code be equal to "400"

#  @AssignVehicle
  Scenario: Assign vehicle to delivery not confirmed
    And Provide dock and get delivery number to which vehicle is to be assigned
      | dock | A3 |
    And Provide vehicle type and vehicle number for a delivery to be assigned vehicle
      | vehicleType | vehicleRegNum |
      | Toras       | MH04FJ6467    |
    When User calls the assign vehicle's endpoint to assign vehicle for a delivery
    Then verify that status code be equal to "200"

#  @AssignVehicle
  Scenario: Assign delivery without vehicles field
    And Provide dock and get delivery number to which vehicle is to be assigned
      | dock | A3 |
    When User calls the assign vehicle's endpoint to assign vehicle for a delivery
    Then verify that status code be equal to "400"

#  @AssignVehicle
  Scenario: Assign vehicle to delivery without vehicleRegNum feild
    And Provide dock and get delivery number to which vehicle is to be assigned
      | dock | A3 |
    And Provide vehicle type and vehicle number for a delivery to be assigned vehicle
      | vehicleType |
      | Toras       |
    When User calls the assign vehicle's endpoint to assign vehicle for a delivery
    Then verify that status code be equal to "400"