Feature: Verify the functionality of Picker Check-In

#  @PickerCheckIn
  Scenario: Check-In Picker without Bearer Token
    And Get Picker's details to Check-In Picker into DC
    When User calls the Picker Check-In's endpoint to check-in the Picker
    Then verify that status code be equal to "401"

#  @PickerCheckIn
  Scenario: Check-In Picker without Bearer Token
    And Provide Picker's details using DataTable to Check-In Picker into DC
      | attendeeId         | 659fd2585c2fd619b4032812 |
      | checkinTime        | 10-01-2024 17:26         |
      | deviceSerialNumber | 1234567890               |
      | siteId             | 1014                     |
    When User calls the Picker Check-In's endpoint to check-in the Picker
    Then verify that status code be equal to "401"

