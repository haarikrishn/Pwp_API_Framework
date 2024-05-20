Feature: Verify the Confirm Delivery functionality with invalid data using JSON File

  Background: Generate Bearer Token from Username and Password
    Given Give Username and Password for Access Token
      | username | 101201      |
      | password | Dmart@12345 |

  @JSON1
  Scenario: Confirm Delivery by assigining 2 Docks for same Delivery Request
    And Get Confirm Delivery request payload from JSON File
      | fileName | ConfirmDelivery_InvalidDock_2Docks.json |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

  @JSONDDT
  Scenario Outline: Confirm Delivery Request with invalid dock values
    And Get Confirm Delivery request payload from JSON File "<jsonFileName>"
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
    Examples:
      | jsonFileName                                       |
      | ConfirmDelivery_InvalidDock_AlphaNumericValue.json |
      | ConfirmDelivery_InvalidDock_EmptyString.json       |
      | ConfirmDelivery_InvalidDock_NullValue.json         |

  @JSON
  Scenario: Confirm Delivery by assigining 2 truckType for same Delivery Request [Array]
    And Get Confirm Delivery request payload from JSON File
      | fileName | ConfirmDelivery_InvalidTruckType_2TruckType.json |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

  @JSONDDT
  Scenario Outline: Confirm Delivery with invalid truckType
    And Get Confirm Delivery request payload from JSON File "<jsonFileName>"
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
    Examples:
      | jsonFileName                                           |
      | ConfirmDelivery_InvalidTruckType_EmptyString.json      |
      | ConfirmDelivery_InvalidTruckType_NullValue.json        |
      | ConfirmDelivery_InvalidTruckType_SpecialCharacter.json |

  @ExcelDDT
  Scenario Outline: Confirm delivery request with invalid delivery number
    And Get Confirm Delivery request payload from JSON File "<jsonFileName>"
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
    Examples:
      | jsonFileName                                      |
      | ConfirmDelivery_InvalidDlvNumber_EmptyString.json |
      | ConfirmDelivery_InvalidDlvNumber_NullValue.json   |

  @JSON
  Scenario: Confirm delivery request without delivery number
    And Get Confirm Delivery request payload from JSON File
      | fileName | ConfirmDelivery_Without_dlvNumber.json |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

  @JSON
  Scenario: Confirm delivery request without dock
    And Get Confirm Delivery request payload from JSON File
      | fileName | ConfirmDelivery_Without_dock.json |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

  @JSON
  Scenario: Confirm delivery request without truckType
    And Get Confirm Delivery request payload from JSON File
      | fileName | ConfirmDelivery_Without_truckType.json |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

  @JSON
  Scenario: Confirm delivery request without delivery items
    And Get Confirm Delivery request payload from JSON File
      | fileName | ConfirmDelivery_Without_dlvItems.json |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

  @JSON
  Scenario: Confirm delivery request without deliveryItemNum
    And Get Confirm Delivery request payload from JSON File
      | fileName | ConfirmDelivery_Without_dlvItemNum.json |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

  @JSON
  Scenario: Confirm delivery request without planned delivery box quantity
    And Get Confirm Delivery request payload from JSON File
      | fileName | ConfirmDelivery_Without_plannedDlvBoxQty.json |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

  @JSON
  Scenario: Confirm delivery request without minimumBoxes
    And Get Confirm Delivery request payload from JSON File
      | fileName | ConfirmDelivery_Without_minimumBoxes.json |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"

  @JSONDDT
  Scenario Outline: Confirm Delivery Request with invalid dlvItemNum
    And Get Confirm Delivery request payload from JSON File "<jsonFileName>"
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
    Examples:
      | jsonFileName                                            |
      | ConfirmDelivery_InvalidDlvItemNum_EmptyString.json      |
      | ConfirmDelivery_InvalidDlvItemNum_itemNumDlvNumber.json |
      | ConfirmDelivery_InvalidDlvItemNum_NullValue.json        |

  @JSONDDT
  Scenario Outline: Confirm Delivery Request with invalid plannedDlvBoxQty
    And Get Confirm Delivery request payload from JSON File "<jsonFileName>"
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
    Examples:
      | jsonFileName                                                    |
      | ConfirmDelivery_InvalidPlannedBoxQty_QuantityMoreThanStock.json |
      | ConfirmDelivery_InvalidPlannedBoxQty_NullValue.json             |
      | ConfirmDelivery_InvalidPlannedBoxQty_EmptyString.json           |

  @JSONDDT
  Scenario Outline: Confirm Delivery Request with invalid minimumBoxes
    And Get Confirm Delivery request payload from JSON File "<jsonFileName>"
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
    Examples:
      | jsonFileName                                          |
      | ConfirmDelivery_InvalidMinimumBoxes_EmptyString.json  |
      | ConfirmDelivery_InvalidMinimumBoxes_NullValue.json    |
      | ConfirmDelivery_InvalidMinimumBoxes_OneNullValue.json |

  @JSONDDT
  Scenario Outline: Confirm Delivery Request with invalid dlvItems
    And Get Confirm Delivery request payload from JSON File "<jsonFileName>"
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "400"
    Examples:
      | jsonFileName                                               |
      | ConfirmDelivery_InvalidDlvItems_Null.json                  |
      | ConfirmDelivery_InvalidDlvItems_EmptyArray.json            |
      | ConfirmDelivery_InvalidDlvItems_WithoutArrayTypeValue.json |


