Feature: Verify the functionality of Confirm Delivery

  Background: Generate Bearer Token from Username and Password
    Given Give Username and Password for Access Token
      | username | 101201      |
      | password | Dmart@12345 |

  @ConfirmDeliveryDT
  Scenario: Verify the functionality of confirm delivery with valid data
    And Assign truckType and dock for new Delivery Request
      | truckType | ACE |
      | dock      | A4  |
    And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
      | itemSeq | plannedDlvBoxQty | minimumBoxes |
      | 000010  | 2                | 2            |
      | 000020  | 2                | 2            |
      | 000030  | 2                | 2            |
      | 000040  | 2                | 2            |
      | 000050  | 2                | 2            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "200"

#  @ConfirmDeliverySO
  Scenario Outline: Verify the functionality of Confirm Delivery
    And Send the request payload for new Delivery Request "<dlvNumber>" "<truckType>" "<dock>" "<dlvItemNum1>" "<plannedDlvBoxQty1>" "<minimumBoxes1>" "<dlvItemNum2>" "<plannedDlvBoxQty2>" "<minimumBoxes2>" "<dlvItemNum3>" "<plannedDlvBoxQty3>" "<minimumBoxes3>" "<dlvItemNum4>" "<plannedDlvBoxQty4>" "<minimumBoxes4>" "<dlvItemNum5>" "<plannedDlvBoxQty5>" "<minimumBoxes5>" "<dlvItemNum6>" "<plannedDlvBoxQty6>" "<minimumBoxes6>" "<dlvItemNum7>" "<plannedDlvBoxQty7>" "<minimumBoxes7>"
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "200"
    And Provide delivery number "<dlvNumber>" and truck type "<truckType>" to get confirm delivery request
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "200"
    And Verify that new delivery is confirmed successfully
    Examples:
      | dlvNumber | truckType | dock | dlvItemNum1     | plannedDlvBoxQty1 | minimumBoxes1 | dlvItemNum2     | plannedDlvBoxQty2 | minimumBoxes2 | dlvItemNum3     | plannedDlvBoxQty3 | minimumBoxes3 | dlvItemNum4     | plannedDlvBoxQty4 | minimumBoxes4 | dlvItemNum5     | plannedDlvBoxQty5 | minimumBoxes5 |
      | 008222721 | ACE       | A3   | 008222721000010 | 2                 | 2             | 008222721000020 | 2                 | 2             | 008222721000030 | 2                 | 2             | 008222721000040 | 2                 | 2             | 008222721000050 | 2                 | 2             |
      | 008222722 | ha        | A3   | 008222722000010 | 2                 | 2             | 008222722000020 | 2                 | 2             | 008222722000030 | 2                 | 2             | 008222722000040 | 2                 | 2             | 008222722000050 | 2                 | 2             |


#  @ConfirmDeliveryExcel
  Scenario: Verify the functionality of Confirm Delivery API
    And Get new delivery request items from Excel File
      | fileName  | PWP_TestData.xlsx             |
      | sheetName | ConfirmDeliveryRequestPayload |
      | rowNum    | 4                             |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "200"

#  @ConfirmDeliveryExcelDDT1
  Scenario: Verify the functionality of Confirm Delivery API
    And Get all Delivery Request items from Excel File
      | fileName           | PWP_TestData.xlsx             |
      | sheetName          | ConfirmDeliveryRequestPayload |
      | expectedStatusCode | 200                           |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "200"
    And Provide dlvNumber and truckType to get confirmed delivery request
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "200"
    And Verify that new delivery request is confirmed successfully

#  @ConfirmDeliveryDT-ve
  Scenario: Confirm Delivery with inward docks(B series)
    And Assign truckType and dock for new Delivery Request
      | truckType | ACE |
      | dock      | B1  |
    And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
      | itemSeq | plannedDlvBoxQty | minimumBoxes |
      | 000010  | 2                | 2            |
      | 000020  | 2                | 2            |
      | 000030  | 2                | 2            |
      | 000040  | 2                | 2            |
      | 000050  | 2                | 2            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
#   failed delivery is created

#  @ConfirmDeliveryDT-ve
  Scenario: Confirm Delivery with dock not present in master
    And Assign truckType and dock for new Delivery Request
      | truckType | ACE |
      | dock      | C4  |
    And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
      | itemSeq | plannedDlvBoxQty | minimumBoxes |
      | 000010  | 2                | 2            |
      | 000020  | 2                | 2            |
      | 000030  | 2                | 2            |
      | 000040  | 2                | 2            |
      | 000050  | 2                | 2            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
#    failed delivery is created with dock not present

#  @ConfirmDeliveryExcel-ve
  Scenario: Confirm Delivery by assigining 2 Docks for same Delivery Request
    And Get new delivery request items from Excel File
      | fileName  | PWP_TestData.xlsx      |
      | sheetName | ConfirmDeliveryInvalid |
      | rowNum    | 1                      |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then Verify that response has status as 400
#    Passed

# @ConfirmDeliveryExcelDDT-ve
  Scenario: Confirm Delivery Request with invalid dock values
    And Get all Delivery Request items from Excel File
      | fileName           | PWP_TestData.xlsx |
      | sheetName          | InvalidDock       |
      | expectedStatusCode | 400               |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
#    Failed with special character

#  @ConfirmDeliveryDT-ve
  Scenario: Confirm Delivery with truckType not present in master
    And Assign truckType and dock for new Delivery Request
      | truckType | XYZ |
      | dock      | A2  |
    And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
      | itemSeq | plannedDlvBoxQty | minimumBoxes |
      | 000010  | 2                | 2            |
      | 000020  | 2                | 2            |
      | 000030  | 2                | 2            |
      | 000040  | 2                | 2            |
      | 000050  | 2                | 2            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then Verify that response has status as 400
#    Failed truckType xyz is assigned

#  @ConfirmDeliveryExcel-ve
  Scenario: Confirm Delivery by assigining 2 truckType for same Delivery Request
    And Get new delivery request items from Excel File
      | fileName  | PWP_TestData.xlsx      |
      | sheetName | ConfirmDeliveryInvalid |
      | rowNum    | 2                      |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then Verify that response has status as 400
#    Passed

#  @ConfirmDeliveryExcelDDT-ve not executed
  Scenario: Confirm Delivery with invalid truckType
    And Get all Delivery Request items from Excel File
      | fileName           | PWP_TestData.xlsx |
      | sheetName          | InvalidTruckType  |
      | expectedStatusCode | 400               |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

#  @ConfirmDeliveryDT-ve
  Scenario: Confirm delivery request with delivery number not created
    And Assign truckType and dock for new Delivery Request
      | truckType | ACE        |
      | dock      | A4         |
      | dlvNumber | 0025489344 |
    And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
      | itemSeq | plannedDlvBoxQty | minimumBoxes |
      | 000010  | 2                | 2            |
      | 000020  | 2                | 2            |
      | 000030  | 2                | 2            |
      | 000040  | 2                | 2            |
      | 000050  | 2                | 2            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then Verify that response has status as "404 Not Found"
#   Passed

#  @ConfirmDeliveryExcelDDT-ve
  Scenario: Confirm delivery request with invalid delivery number
    And Get all Delivery Request items from Excel File
      | fileName           | PWP_TestData.xlsx |
      | sheetName          | InvalidDlvNumber  |
      | expectedStatusCode | 400               |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
#    Not Executed

#  @ConfirmDeliveryExcel-ve
  Scenario: Confirm delivery request without delivery number
    And Get new delivery request items from Excel File
      | fileName  | PWP_TestData.xlsx      |
      | sheetName | ConfirmDeliveryInvalid |
      | rowNum    | 3                      |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then Verify that response has status as 400
#    PASSED

#  @ConfirmDeliveryExcel-ve
  Scenario: Confirm delivery request without dock
    And Get new delivery request items from Excel File
      | fileName  | PWP_TestData.xlsx      |
      | sheetName | ConfirmDeliveryInvalid |
      | rowNum    | 4                      |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then Verify that response has status as 400
#    Passed

#  @ConfirmDeliveryExcel-ve
  Scenario: Confirm delivery request without truckType
    And Get new delivery request items from Excel File
      | fileName  | PWP_TestData.xlsx      |
      | sheetName | ConfirmDeliveryInvalid |
      | rowNum    | 5                      |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then Verify that response has status as 400
#    PASSED

#  @ConfirmDeliveryExcel-ve
  Scenario: Confirm delivery request without delivery items
    And Get new delivery request items from Excel File
      | fileName  | PWP_TestData.xlsx      |
      | sheetName | ConfirmDeliveryInvalid |
      | rowNum    | 6                      |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then Verify that response has status as 400
#    Passed

#  @ConfirmDeliveryExcel-ve1
  Scenario: Confirm delivery request without deliveryItemNum
    And Get new delivery request items from Excel File
      | fileName  | PWP_TestData.xlsx      |
      | sheetName | ConfirmDeliveryInvalid |
      | rowNum    | 7                      |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

#  @ConfirmDeliveryExcel-ve
  Scenario: Confirm delivery request without planned delivery box quantity
    And Get new delivery request items from Excel File
      | fileName  | PWP_TestData.xlsx      |
      | sheetName | ConfirmDeliveryInvalid |
      | rowNum    | 8                      |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

#  @ConfirmDeliveryExcel-ve
  Scenario: Confirm delivery request without minimumBoxes
    And Get new delivery request items from Excel File
      | fileName  | PWP_TestData.xlsx      |
      | sheetName | ConfirmDeliveryInvalid |
      | rowNum    | 9                      |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

#  @ConfirmDeliveryExcelDDT-ve
  Scenario: Confirm Delivery Request with invalid dlvItems
    And Get all Delivery Request items from Excel File
      | fileName           | PWP_TestData.xlsx |
      | sheetName          | InvalidDlvItems   |
      | expectedStatusCode | 400               |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

#  @ConfirmDeliveryExcelDDT-ve
  Scenario: Confirm Delivery Request with invalid dlvItemNum
    And Get all Delivery Request items from Excel File
      | fileName           | PWP_TestData.xlsx |
      | sheetName          | InvalidDlvItemNum |
      | expectedStatusCode | 400               |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

#  @ConfirmDeliveryExcelDDT-ve
  Scenario: Confirm Delivery Request with invalid plannedDlvBoxQty
    And Get all Delivery Request items from Excel File
      | fileName           | PWP_TestData.xlsx    |
      | sheetName          | InvalidPlannedBoxQty |
      | expectedStatusCode | 400                  |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

#  @ConfirmDeliveryExcelDDT-ve
  Scenario: Confirm Delivery Request with invalid minimumBoxes
    And Get all Delivery Request items from Excel File
      | fileName           | PWP_TestData.xlsx |
      | sheetName          | InvalidMinimumBox |
      | expectedStatusCode | 400               |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

#  @ConfirmDeliveryDT-ve
  Scenario: Confirm delivery request with value less than min value for minimumBoxes
    And Assign truckType and dock for new Delivery Request
      | truckType | ACE        |
      | dock      | A5         |
      | dlvNumber | 0026759884 |
    And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
      | itemSeq | plannedDlvBoxQty | minimumBoxes |
      | 000010  | 2                | 0            |
      | 000020  | 2                | 0            |
      | 000030  | 2                | 0            |
      | 000040  | 2                | 0            |
      | 000050  | 2                | 0            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

#  @ConfirmDeliveryDT-ve
  Scenario: Re-Confirm same delivery request with different dock and truckType and plannedBoxQty and minimumBoxes
    And Assign truckType and dock for new Delivery Request
      | truckType | Toras |
      | dock      | A5    |
    And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
      | itemSeq | plannedDlvBoxQty | minimumBoxes |
      | 000010  | 3                | 2            |
      | 000020  | 3                | 2            |
      | 000030  | 4                | 4            |
      | 000040  | 5                | 5            |
      | 000050  | 6                | 4            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
#    Failed delivery reconfirmed

#  @ConfirmDeliveryDT-ve
  Scenario: Confirm delivery request with plannedDlvBoxQty less than minimumBoxes
    And Assign truckType and dock for new Delivery Request
      | truckType | ACE |
      | dock      | A2  |
    And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
      | itemSeq | plannedDlvBoxQty | minimumBoxes |
      | 000010  | 1                | 2            |
      | 000020  | 1                | 2            |
      | 000030  | 1                | 2            |
      | 000040  | 1                | 2            |
      | 000050  | 1                | 2            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

#  @ConfirmDeliveryDT-ve
  Scenario: Confirm Delivery Request with items less than total items Requested
    And Assign truckType and dock for new Delivery Request
      | truckType | ha |
      | dock      | A3 |
    And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
      | itemSeq | plannedDlvBoxQty | minimumBoxes |
      | 000010  | 2                | 2            |
      | 000020  | 2                | 2            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"











