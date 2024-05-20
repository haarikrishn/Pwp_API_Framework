Feature: Verify the functionality of Get TO's Details

  Background: Generate Bearer Token from Username and Password
    Given Give Username and Password for Access Token
      | username | 101201      |
      | password | Dmart@12345 |

#  @TOBYDELIVERYNUM
  Scenario: Get TO's Details with valid data
    And Provide delivery number using DataTable to get the details of TO
      | dlvNumber | 0097311239 |
    When User calls the Get TO details by delivery numbers endpoint to get TO's details
    Then verify that status code be equal to "200"

#  @TOBYDELIVERYNUM
  Scenario: Get TO's Details with valid data
    And Provide delivery number using DataTable to get the details of TO
      | dlvNumber | 0097311239 |
    When User calls the Get TO details by delivery numbers endpoint to get TO's details
    Then verify that status code be equal to "200"
    And Verify that items are assigned to Picker

  @TOBYDELIVERYNUM
  Scenario: Get TO's Details with valid data
    And Get delivery number to get the details of TO
    When User calls the Get TO details by delivery numbers endpoint to get TO's details
    Then verify that status code be equal to "200"
    And Verify that items are assigned to Picker

#  @TOBYDELIVERYNUM
  Scenario: Get TO's Details with valid data
    And Get delivery number to get the details of TO
    When User calls the Get TO details by delivery numbers endpoint to get TO's details
    Then verify that status code be equal to "200"
    And Verify that items are not assigned to Picker

#  @TOBYDELIVERYNUM
  Scenario: Get Delivery Request with dlvNumber not present
    And Provide delivery number using DataTable to get the details of TO
      | dlvNumber | 0034567843 |
    When User calls the Get TO details by delivery numbers endpoint to get TO's details
    Then verify that status code be equal to "404"

#  @TOBYDELIVERYNUM
  Scenario Outline: Get Delivery Request with invalid dlvNumber(null, empty string)
    And Provide delivery number using DataTable to get the details of TO "<dlvNumber>"
    When User calls the Get TO details by delivery numbers endpoint to get TO's details
    Then verify that status code be equal to "404"
    Examples:
      | dlvNumber      |
      | null           |
      |                |
      | 0034567843@#$% |
