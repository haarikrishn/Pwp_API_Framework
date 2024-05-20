Feature: Verify the functionality of Get TOs

  Background: Generate Bearer Token from Username and Password
    Given Give Username and Password for Access Token
      | username | 101201      |
      | password | Dmart@12345 |

  @AllTOS
  Scenario: Verify the functionality of Get TOs
    When User calls the Get TO's endpoint to get list of TOs
    Then verify that status code be equal to "200"