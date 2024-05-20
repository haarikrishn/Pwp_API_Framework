Feature: Get  delivery using GetListofDeliveriesEndPoint
  Background: access token id by
    Given Give Username and Password for Access Token
      | username | 8317454007 |
      | password | Sweety123@ |

  @GetListofDeliveries
  Scenario: verify the functionality of GetListofDeliveriesEndpoint
    When   send GetDelivery End point to get the data
    Then verify that status code be equal to 200 for GetListofDeliveries
    And validate Response body of getListOfDeleveries
    And validating JsonSchema Of object "GetListOfDeliverySchema.json"

  @verifyDelivernumberPresentOrNot
  Scenario: verify the functionality of GetListofDeliveriesEndpoint
    When   send GetDelivery End point to get the data
    Then verify delivernumber is present or not
    And verify that status code be equal to 200 for GetListofDeliveries

