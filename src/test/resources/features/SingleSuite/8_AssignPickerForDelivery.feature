Feature: Verify the functionality of Assign Picker

  Background: Generate Bearer Token from Username and Password
    Given Give Username and Password for Access Token
      | username | 101202      |
      | password | Dmart@12345 |

#  @AssignPicker
  Scenario: Assign new Delivery to Picker with valid data
    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
      | siteId     | 4019                     |
      | toNumber   | 0097056610               |
      | dlvNumber  | 009584061                |
      | assigneeId | 6577f98130e33d33903e7876 |
      | huMaterial | 201                      |
    And Provide tolineItemNumbers to assign Items to Picker
      | 00970566101390001 |
      | 00970566101390002 |
      | 00970566101390003 |
      | 00970566101390004 |
      | 00970566101390005 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "200"

  @AssignPicker
  Scenario: Assign new Delivery to Picker with valid data
    And Get delivery details and assigneeId to assign delivery to picker
    And Provide huMaterial and destSiteId for a delivery to be assigned
      | huMaterial | 201 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "200"

#  @AssignPicker
  Scenario: Assign some of the Items in Delivery to one Picker and Remaining Items to Other Picker
    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
      | siteId     | 4019                     |
      | toNumber   | 0095013049               |
      | dlvNumber  | 0098541950               |
      | assigneeId | 659fe5135c2fd619b403285b |
      | huMaterial | 201                      |
    And Provide tolineItemNumbers to assign Items to Picker
      | 00950130491390001 |
      | 00950130491390002 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "200"
    And Get delivery number to get the details of TO
    When User calls the Get TO details by delivery numbers endpoint to get TO's details
    Then verify that status code be equal to "200"
    And Verify that items are assigned to Picker
    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
      | siteId     | 4019                     |
      | toNumber   | 0095013049               |
      | dlvNumber  | 0098541950               |
      | assigneeId | 659f934f5c2fd619b4032780 |
      | huMaterial | 201                      |
    And Provide tolineItemNumbers to assign Items to Picker
      | 00950130491390003 |
      | 00950130491390004 |
      | 00950130491390005 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "200"
    And Get delivery number to get the details of TO
    When User calls the Get TO details by delivery numbers endpoint to get TO's details
    Then verify that status code be equal to "200"
    And Verify that items are assigned to Picker

#  @AssignPicker
  Scenario: Re-Assign new Picker for already assigned delivery
    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
      | siteId     | 4019                     |
      | toNumber   | 0097056610               |
      | dlvNumber  | 009584061               |
      | assigneeId | 659f934f5c2fd619b4032780 |
      | huMaterial | 201                      |
    And Provide tolineItemNumbers to assign Items to Picker
      | 00970566101390001 |
      | 00970566101390002 |
      | 00970566101390003 |
      | 00970566101390004 |
      | 00970566101390005 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "200"

#  @AssignPicker
  Scenario Outline: Assign new Delivery to Picker with invalid dlvNumber(null, emptyString, Special Character)
    And Provide delivery details to assign delivery to picker "<siteId>" "<toNumber>" "<dlvNumber>" "<assigneeId>" "<tolineItemNumber1>" "<tolineItemNumber2>" "<tolineItemNumber3>" "<tolineItemNumber4>" "<tolineItemNumber5>" "<huMaterial>"
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "500"
    Examples:
      | siteId | toNumber   | dlvNumber     | assigneeId               | tolineItemNumber1 | tolineItemNumber2 | tolineItemNumber3 | tolineItemNumber4 | tolineItemNumber5 | huMaterial |
      | 4019   | 0095513672 | null          | 659fe5135c2fd619b403285b | 00955136721390001 | 00955136721390002 | 00955136721390003 | 00955136721390004 | 00955136721390005 | 201        |
      | 4019   | 0095513672 |               | 659fe5135c2fd619b403285b | 00955136721390001 | 00955136721390002 | 00955136721390003 | 00955136721390004 | 00955136721390005 | 201        |
      | 4019   | 0095513672 | 0095513672@#$ | 659fe5135c2fd619b403285b | 00955136721390001 | 00955136721390002 | 00955136721390003 | 00955136721390004 | 00955136721390005 | 201        |

#  @AssignPicker
  Scenario Outline: Assign new Delivery to Picker with invalid tolineItemNumbers(null, emptyString, Special Character)
    And Provide delivery details to assign delivery to picker "<siteId>" "<toNumber>" "<dlvNumber>" "<assigneeId>" "<tolineItemNumber1>" "<tolineItemNumber2>" "<tolineItemNumber3>" "<tolineItemNumber4>" "<tolineItemNumber5>" "<huMaterial>"
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "500"
    Examples:
      | siteId | toNumber   | dlvNumber  | assigneeId              | tolineItemNumber1    | tolineItemNumber2    | tolineItemNumber3    | tolineItemNumber4    | tolineItemNumber5    | huMaterial |
      | 4019   | 0095513672 | 0093173262 | 659fe5135c2fd619b403285 | 00955136721390001@#$ | 00955136721390002@#$ | 00955136721390003@#$ | 00955136721390004@#$ | 00955136721390005@#$ | 201        |
      | 4019   | 0095513672 | 0093173262 | 659fe5135c2fd619b403285 | null                 | null                 | null                 | null                 | null                 | 201        |
      | 4019   | 0095513672 | 0093173262 | 659fe5135c2fd619b403285 |                      |                      |                      | 00955136721390004    |                      | 201        |
      | 4019   | 0095513672 | 0093173262 | 659fe5135c2fd619b403285 | 00955136721390001    | null                 | 00955136721390003    | 00955136721390004    | 00955136721390005    | 201        |

#  @AssignPicker
  Scenario Outline: Assign new Delivery to Picker with invalid assigneeId
    And Provide delivery details to assign delivery to picker "<siteId>" "<toNumber>" "<dlvNumber>" "<assigneeId>" "<tolineItemNumber1>" "<tolineItemNumber2>" "<tolineItemNumber3>" "<tolineItemNumber4>" "<tolineItemNumber5>" "<huMaterial>"
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "500"
    Examples:
      | siteId | toNumber   | dlvNumber  | assigneeId                  | tolineItemNumber1 | tolineItemNumber2 | tolineItemNumber3 | tolineItemNumber4 | tolineItemNumber5 | huMaterial |
      | 4019   | 0095513672 | 0093173262 | null                        | 00955136721390001 | 00955136721390002 | 00955136721390003 | 00955136721390004 | 00955136721390005 | 201        |
      | 4019   | 0095513672 | 0093173262 |                             | 00955136721390001 | 00955136721390002 | 00955136721390003 | 00955136721390004 | 00955136721390005 | 201        |
      | 4019   | 0095513672 | 0093173262 | 659fe5135c2fd619b403285b@#$ | 00955136721390001 | 00955136721390002 | 00955136721390003 | 00955136721390004 | 00955136721390005 | 201        |

#  @AssignPicker Critical Bug Delivery disappeared in GetToByDeliveryNumber delivery is showing in getTo
  Scenario: Assign Delivery to Picker by giving toNumber of different delivery
    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
      | siteId     | 4019                    |
      | toNumber   | 0096514168              |
      | dlvNumber  | 0092801764              |
      | assigneeId | 659fe5135c2fd619b403285 |
      | huMaterial | 201                     |
    And Provide tolineItemNumbers to assign Items to Picker
      | 0099360891390001 |
      | 0099360891390002 |
      | 0099360891390003 |
      | 0099360891390004 |
      | 0099360891390005 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "200"

#  @AssignPicker
#  Scenario: Assign Delivery to Picker with tolineItemNumber of different Transfer Order
#    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
#      | siteId     | 4019                     |
#      | toNumber   | 0096514168               |
#      | dlvNumber  | 0095918824               |
#      | assigneeId | 6577f98130e33d33903e7876 |
#      | huMaterial | 201                      |
#    And Provide tolineItemNumbers to assign Items to Picker
#      | 00965141681390001 |
#      | 00965141681390002 |
#      | 00965141681390003 |
#      | 00965141681390004 |
#    When User calls the Assign Picker endpoint to assign delivery to Picker
#    Then verify that status code be equal to "400"

  #  @AssignPicker
#  Scenario: Assign No items to Picker for New Delivery(empty tolineItemNumbers array)
#    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
#      | siteId     | 4019                     |
#      | toNumber   | 0096514168               |
#      | dlvNumber  | 0095918824               |
#      | assigneeId | 6577f98130e33d33903e7876 |
#      | huMaterial | 201                      |
#    And Provide tolineItemNumbers to assign Items to Picker
#      |  |
#      |  |
#      |  |
#      |  |
#    When User calls the Assign Picker endpoint to assign delivery to Picker
#    Then verify that status code be equal to "400"

#  @AssignPicker
#  Scenario: Assign Delivery to Picker without creating TO
#    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
#      | siteId     | 4019                     |
#      | dlvNumber  | 0095918824               |
#      | assigneeId | 6577f98130e33d33903e7876 |
#      | huMaterial | 201                      |
#    And Provide tolineItemNumbers to assign Items to Picker
#      | 00965141681390001 |
#      | 00965141681390002 |
#      | 00965141681390003 |
#      | 00965141681390004 |
#    When User calls the Assign Picker endpoint to assign delivery to Picker
#    Then verify that status code be equal to "400"

#  @AssignPicker assigning different siteId delivery to Picker
  Scenario: Assign different siteId delivery to Picker(delivery of siteId 1039 Picker - 1014)
    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
      | siteId     | 4019                     |
      | toNumber   | 0000546241               |
      | dlvNumber  | 0085117636               |
      | assigneeId | 659fe5135c2fd619b403285b |
      | huMaterial | 201                      |
    And Provide tolineItemNumbers to assign Items to Picker
      | 00005462410001 |
      | 00005462410002 |
      | 00005462410003 |
      | 00005462410004 |
      | 00005462410005 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "400"

#  @AssignPicker Delivery assigned to Checked-Out Picker
  Scenario: Assign Delivery to Picker without Check-In
    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
      | siteId     | 4019                     |
      | toNumber   | 009505040                |
      | dlvNumber  | 0092423520               |
      | assigneeId | 659fe5135c2fd619b403285b |
      | huMaterial | 201                      |
    And Provide tolineItemNumbers to assign Items to Picker
      | 0095050401390001 |
      | 0095050401390002 |
      | 0095050401390004 |
      | 0095050401390005 |
      | 0095050401390006 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "200"

#  @AssignPicker
  Scenario: Assign new Delivery to Picker without huMaterial
    And Provide delivery details and assigneeId using DataTable to assign delivery to picker
      | siteId     | 4019                     |
      | toNumber   | 0094359279               |
      | dlvNumber  | 0096034009               |
      | assigneeId | 659fe5135c2fd619b403285b |
    And Provide tolineItemNumbers to assign Items to Picker
      | 00943592791390001 |
      | 00943592791390002 |
      | 00943592791390003 |
      | 00943592791390004 |
      | 00943592791390005 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "400"

#  @AssignPicker
  Scenario Outline: Assign new Delivery to Picker with invalid assigneeId
    And Provide delivery details to assign delivery to picker "<siteId>" "<toNumber>" "<dlvNumber>" "<assigneeId>" "<tolineItemNumber1>" "<tolineItemNumber2>" "<tolineItemNumber3>" "<tolineItemNumber4>" "<tolineItemNumber5>" "<huMaterial>"
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "400"
    Examples:
      | siteId | toNumber   | dlvNumber  | assigneeId               | tolineItemNumber1 | tolineItemNumber2 | tolineItemNumber3 | tolineItemNumber4 | tolineItemNumber5 | huMaterial |
      | 4019   | 0095513672 | 0093173262 | 659fe5135c2fd619b403285b | 00955136721390001 | 00955136721390002 | 00955136721390003 | 00955136721390004 | 00955136721390005 | null       |
      | 4019   | 0095513672 | 0093173262 | 659fe5135c2fd619b403285b | 00955136721390001 | 00955136721390002 | 00955136721390003 | 00955136721390004 | 00955136721390005 |            |
      | 4019   | 0095513672 | 0093173262 | 659fe5135c2fd619b403285b | 00955136721390001 | 00955136721390002 | 00955136721390003 | 00955136721390004 | 00955136721390005 | 201@#$%    |

