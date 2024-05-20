Feature: Verify the functionality of Picker Check-Out

  @Check-Out
  Scenario: Check-Out Picker without bearer token
    And Provide attendeeId to check-out the Picker
      | attendeeId | 659f934f5c2fd619b4032780 |
    When User calls the Picker Check-Out endpoint to check-out the Picker
    Then verify that status code be equal to "401"

#  @Check-Out
  Scenario: Check-Out Picker with valid attendeeId
    And Get attendeeId to check-out the Picker
    When User calls the Picker Check-Out endpoint to check-out the Picker
    Then verify that status code be equal to "200"

