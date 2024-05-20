Feature: Verify the functionality of Get Picker

  Background: Generate Bearer Token from Username and Password
    Given Give Username and Password for Access Token
      | username | 101201      |
      | password | Dmart@12345 |

  @GETPICKER
  Scenario: Verify the functionality of Get Picker
    And Provide dcId to get the checked-in Picker's details
      | siteId | 1014 |
    When User calls the Get Picker endpoint to get checked-in Pickers
    Then verify that status code be equal to "200"
    And Verify that delivery is assigned to picker

  @GETPICKER
  Scenario: Verify the functionality of Get Picker
    And Get dcId to get the checked-in Picker's details
    When User calls the Get Picker endpoint to get checked-in Pickers
    Then verify that status code be equal to "200"