Feature: GetTo using GetToEndPoint
  Background: access token id by
    Given Give Username and Password for Access Token
      | username | 101201      |
      | password | Dmart@12345 |
  @GetTO
  Scenario: verify the functionality of GetTOEndPoint
    When   send GetTO End point to get the data
    Then verify that status code be equal to "200"
    And validate Response body of  GetToEndPoint
