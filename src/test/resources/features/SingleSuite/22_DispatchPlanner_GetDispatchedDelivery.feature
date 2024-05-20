Feature: Verify the functionality of Get Dispatch Delivey

  Background: Generate Bearer Token from Username and Password
    Given Give Username and Password for Access Token
      | username | 101201      |
      | password | Dmart@12345 |

  @DispatcDeliveriesDetails
  Scenario: Verify user gets the list of dispatch delivery
    When User calls the dispatch delivery endpoint to get the list of dispatch delivery
    Then verify that status code be equal to "200"
    And Verify that vehicle is assigned for a delivery

#  @GetDispatcDeliveries
  Scenario: Get the list of dispatch delivery
    When User calls the dispatch delivery endpoint to get the list of dispatch delivery
    Then verify that status code be equal to "200"
    And Verify that vehicle is not assigned for a delivery