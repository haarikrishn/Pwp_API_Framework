Feature: Verify the functionality of Assign Picker


#  @AssignPicker
  Scenario: Assign new Delivery to Picker without Bearer Token
    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
      | siteId     | 4019                     |
      | toNumber   | 0096514168               |
      | dlvNumber  | 0095918824               |
      | assigneeId | 6577f98130e33d33903e7876 |
      | huMaterial | 201                      |
    And Provide tolineItemNumbers to assign Items to Picker
      | 00965141681390001 |
      | 00965141681390002 |
      | 00965141681390003 |
      | 00965141681390004 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "200"

#  @AssignPicker
  Scenario: Assign new Delivery to Picker without Bearer Token
    And Get delivery details and assigneeId to assign delivery to picker
    And Provide huMaterial and destSiteId for a delivery to be assigned
      | huMaterial | 201  |
      | siteId     | 4019 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "200"

