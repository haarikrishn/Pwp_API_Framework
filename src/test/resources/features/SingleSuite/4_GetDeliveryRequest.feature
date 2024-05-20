Feature: Verify the functionality of Get Delivery

  Background: Generate Bearer Token from Username and Password
    Given Give Username and Password for Access Token
      | username | 101201      |
      | password | Dmart@12345 |

  @GetDelCD
  Scenario: Verify the Functionality of Get Delivery by Delivery Number and Truck Type
    And Provide dlvNumber and truckType to get confirmed delivery request
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "200"
    And Verify that new delivery is confirmed successfully

#  @GetDelCD
  Scenario: Check whether the delivery is confirmed with invalid data
    And Provide dlvNumber and truckType to get confirmed delivery request
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "400"

#  @GetDelExcel
  Scenario: Verify the Functionality of Get Delivery by Delivery Number and Truck Type
    And Provide dlvNumber and truckType to get confirmed delivery request
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "200"
    And Verify that new delivery request is confirmed successfully

#  @GetDelSO
  Scenario Outline: Verify the Functionality of Get Delivery by Delivery Number and Truck Type
    And Provide delivery number "<dlvNumber>" and truck type "<truckType>" to get confirm delivery request
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "200"
    Examples:
      | dlvNumber | truckType |
      | 008222721 | ACE       |
      | 008222722 | ha        |

#  @GetDelExcel
  Scenario: Verify the Functionality of Get Delivery by Delivery Number and Truck Type
    And Provide dlvNumber and truckType to get confirmed delivery request
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "200"
    And Verify that new delivery request is confirmed successfully

#  @GetDelDDT
  Scenario: Verify the Functionality of Get Delivery by Delivery Number and Truck Type
    And Provide fileName and sheetName to all get confirm delivery request
      | fileName           | PWP_TestData.xlsx           |
      | sheetName          | GetDeliveryByDeliveryNumber |
      | expectedStatusCode | 200                         |
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "200"

#  @GetDelDT1
  Scenario: Get Delivery Request with valid dlvNumber and truckType
    And Provide delivery number and truck type to get confirm delivery request
      | dlvNumber | 008222721 |
      | truckType | ACE       |
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "200"
#    Then Verify that new delivery is confirmed successfully

#  @GetDelDT1
  Scenario: Get Delivery Request with dlvNumber only
    And Provide delivery number and truck type to get confirm delivery request
      | dlvNumber | 008222721 |
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then Verify that response has status as 400

#  @GetDelDT1
  Scenario: Get Delivery Request with truckType only
    And Provide delivery number and truck type to get confirm delivery request
      | truckType | ACE |
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "400"

#  @GetDelDT1
  Scenario: Get Delivery Request with dlvNumber not present
    And Provide delivery number and truck type to get confirm delivery request
      | dlvNumber | 0012345678 |
      | truckType | ACE        |
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "500"

#  @GetDelDT1
  Scenario: Get Delivery Request with truckType not present
    And Provide delivery number and truck type to get confirm delivery request
      | dlvNumber | 008222721 |
      | truckType | ABCDE     |
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "500"

#  @GetDelDT1
  Scenario: Get Delivery Request by employee of different siteId
    Given Give Username and Password for Access Token
      | username | 123457      |
      | password | Welcome@123 |
    And Provide delivery number and truck type to get confirm delivery request
      | dlvNumber | 0046350710 |
      | truckType | ACE        |
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "401"