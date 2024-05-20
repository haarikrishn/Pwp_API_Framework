Feature: Verify the Confirm Delivery functionality with different types of Bearer Token

#  @CDDT
  Scenario: Confirm Delivery Request with Bearer Token of Different dcID
  Given Give Username and Password for Access Token
    | username | 123457      |
    | password | Welcome@123 |
    And Assign truckType and dock for new Delivery Request
    | truckType | ACE       |
    | dock      | A4        |
    | dlvNumber | 008222718 |
  And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
    | itemSeq | plannedDlvBoxQty | minimumBoxes |
    | 000010  | 2                | 2            |
    | 000020  | 2                | 2            |
    | 000030  | 2                | 2            |
    | 000040  | 2                | 2            |
    | 000050  | 2                | 2            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
  Then verify that status code be equal to "401"
#    Failed Delivery Confirmed CRITICAL

  @CDDT
  Scenario: Confirm Delivery Request by Employee not having Delivery Planner Role in DC
    Given Give Username and Password for Access Token
      | username | 104105    |
      | password | Dmart@123 |
    And Assign truckType and dock for new Delivery Request
      | truckType | ACE       |
      | dock      | A4        |
      | dlvNumber | 008222718 |
    And Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request
      | itemSeq | plannedDlvBoxQty | minimumBoxes |
      | 000010  | 2                | 2            |
      | 000020  | 2                | 2            |
      | 000030  | 2                | 2            |
      | 000040  | 2                | 2            |
      | 000050  | 2                | 2            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "401"
#    PASSED