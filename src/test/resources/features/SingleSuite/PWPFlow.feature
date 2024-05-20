Feature: PWP Flow

  Background: Generate Bearer Token from Username and Password
    Given Give Username and Password for Access Token
      | username | 101201      |
      | password | Dmart@12345 |

  @CreateOneDeliveryWithListOfItemsUsingDT
  Scenario: Verify the functionality of CreateOneDeliveryWithListOfItemsUsingDT
    Given Give Username and Password for Access Token
      | username  | integrator       |
      | password  | 3poXy$6o29       |
      | requestId | 0000001376364111 |
    And Assigning Main Mandatory fields to CreateOneDeliveryWithListOfItemsUsingDT
      | srcSiteId       | 1014 |
      | dstSiteId       | 5019 |
      | whNumber        | 114  |
      | createdBy       | MHK  |
      | totalGdsMvtStat | A    |
    And Provide dlvItemNum to CreateOneDeliveryWithListOfItemsUsingDT
      | itemSeq | stoNum     | stoLineNum | ean           | mrp    | matNum    | itemDesc                            | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus |
      | 000010  | 5101334418 | 000010     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N)  | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          |
      | 000020  | 5101334418 | 000020     | 8901314524010 | 170.00 | 500017575 | COLGATE MAXFRESH BLUE GEL TP(300G)  | 5OCTTPGEL | F      | 3000  | 0103210170 | 96.000         | 48.00   | EA  | 0.360  | KG    | 0.027   | FT3    | A          |
      | 000030  | 5101334418 | 000030     | 8903363007247 | 150.00 | 500017602 | GRACE CITRUS PASSI SHOWER GEL 250ML | 5PWLSPBWS | F      | 3000  | 0103210150 | 72.000         | 36.00   | EA  | 0.369  | KG    | 0.22    | FT3    | A          |
      | 000040  | 5101334418 | 000040     | 8903363007254 | 150.00 | 500017603 | GRACE DEEP IMPACT SHOWER GEL 250M   | 5PWLSPBWS | F      | 3000  | 0103210150 | 72.000         | 36.00   | EA  | 0.303  | KG    | 0.022   | FT3    | A          |
      | 000050  | 5101334418 | 000050     | 8903363006868 | 149.00 | 500017671 | MEADOWS AIR FRESHENER LAV BLS-240ML | 5AFHFRSPR | G      | 3000  | 0103210149 | 96.000         | 48.00   | EA  | 0.303  | KG    | 0.022   | FT3    | A          |
      | 000060  | 5101334418 | 000060     | 8901248154031 | 110.00 | 500018038 | BOROPLUS ALOV HAL CH KES GEL(150ML) | 5SKCSLFWS | Q      | 3000  | 0103210110 | 96.000         | 48.00   | EA  | 0.215  | KG    | 0.028   | FT3    | A          |
      | 000070  | 5101334418 | 000070     | 4987176023476 | 199.00 | 500018258 | PAMPERS ALOE BABY WIPS(72N)         | 5PHICRTSW | Q      | 3000  | 0101210199 | 96.000         | 48.00   | EA  | 0.250  | KG    | 0.099   | FT3    | A          |
    When User calls the to createDelivery End point to   CreateOneDeliveryWithListOfItemsUsingDT
    Then verify that status code be equal to "201"

  @GetListofDeliveries
  Scenario: verify the functionality of GetListofDeliveriesEndpoint
    When   send GetDelivery End point to get the data
    Then verify that status code be equal to 200 for GetListofDeliveries
    And validate Response body of getListOfDeleveries
    And validating JsonSchema Of object "GetListOfDeliverySchema.json"

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
      | 000060  | 2                | 2            |
      | 000070  | 2                | 2            |
    When User calls the Confirm Delivery's endpoint to confirm the Delivery Request
    Then verify that status code be equal to "200"

  @GetDelCD
  Scenario: Verify the Functionality of Get Delivery by Delivery Number and Truck Type
    And Provide dlvNumber and truckType to get confirmed delivery request
    When User calls the get delivery by dlvNumber and truckType to get the confirmed delivery
    Then verify that status code be equal to "200"
    And Verify that new delivery is confirmed successfully

  @CreateTOForOriginalDelivery
  Scenario: Verify the functionality of CreateTOWithListOftransferOrderItems
    Given Give Username and Password for Access Token
      | username  | integrator       |
      | password  | 3poXy$6o29       |
      | requestId | 0000001376364111 |
    And Assigning Main Mandatory fields to CreateTOWithListOftransferOrderItems
      | whNumber     | 114        |
      | srcPlant     | 1014       |
      | destPlant    | 5019       |
      | grpDlv       | 0004362630 |
      | movementType | 850        |
    And Provide transferOrderItems to CreateTOWithListOftransferOrderItems
      | txfOrdItemSeq | matNumber | matDesc                             | ean           | batchNum   | srcStorageType | srcStorageBin | srcQty | caselot | uom | mrp    | itemWeight | itemVolume | itemType | binSeq | bbDate | storageLoc | destStorageType | palletQty |
      | 0001          | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N)  | 4902430905183 | 2503210050 | FRS            | A030-14-1B    | 96.000 | 48.00   | EA  | 50.00  | 0.067      | 0.021      | ZFNF     | 3982   |        | 3000       | 200             | 1728.000  |
      | 0002          | 500017575 | COLGATE MAXFRESH BLUE GEL TP(300G)  | 8901314524010 | 0103210170 | FRS            | A030-14-1B    | 96.000 | 48.00   | EA  | 170.00 | 0.360      | 0.027      | ZFNF     | 3904   |        | 3000       | 200             | 1440.000  |
      | 0003          | 500017602 | GRACE CITRUS PASSI SHOWER GEL 250ML | 8903363007247 | 0103210150 | FRS            | A033-07-1A    | 72.000 | 36.00   | EA  | 150.00 | 0.369      | 0.022      | ZFNF     | 4143   |        | 3000       | 200             | 1440.000  |
      | 0004          | 500017603 | GRACE DEEP IMPACT SHOWER GEL 250M   | 8903363007254 | 0103210150 | FRS            | A033-29-1A    | 72.000 | 36.00   | EA  | 150.00 | 0.303      | 0.022      | ZFNF     | 4319   |        | 3000       | 200             | 1440.000  |
      | 0005          | 500017671 | MEADOWS AIR FRESHENER LAV BLS-240ML | 8903363006868 | 0103210149 | FRS            | A032-09-1B    | 96.000 | 48.00   | EA  | 149.00 | 0.242      | 0.032      | ZFNF     | 4161   |        | 3000       | 200             | 1152.000  |
      | 0006          | 500018038 | BOROPLUS ALOV HAL CH KES GEL(150ML) | 8901248154031 | 0103210110 | FRS            | A029-03-1A    | 96.000 | 48.00   | EA  | 110.00 | 0.215      | 0.028      | ZFNF     | 3583   |        | 3000       | 200             | 1056.000  |
      | 0007          | 500018258 | PAMPERS ALOE BABY WIPS(72N)         | 4987176023476 | 0101210199 | FRS            | A019-06-1B    | 96.000 | 48.00   | EA  | 199.00 | 0.250      | 0.099      | ZFNF     | 2464   |        | 3000       | 200             | 648.000   |
    When User calls the to create TO End point to CreateTOWithListOftransferOrderItems
    Then verify that status code be equal to "201"

  @GetTO
  Scenario: verify the functionality of GetTOEndPoint
    When send GetTO End point to get the data
    Then verify that status code be equal to "200"
    And validate Response body of  GetToEndPoint

  @PickerCheckIn
  Scenario: Check-In Picker with valid data
    And Get Picker's details to Check-In Picker into DC
    When User calls the Picker Check-In's endpoint to check-in the Picker
    Then verify that status code be equal to "200"

#  @PickerCheckIn
  Scenario: Check-In Picker with valid data using DataTable
    And Provide Picker's details using DataTable to Check-In Picker into DC
      | attendeeId         | 659fd2585c2fd619b4032812 |
      | checkinTime        | 25-01-2024 11:24         |
      | deviceSerialNumber | 1234567890               |
      | siteId             | 1014                     |
    When User calls the Picker Check-In's endpoint to check-in the Picker
    Then verify that status code be equal to "200"

  @PickerPresence
  Scenario: Verify the functionality of Picker Presence
    When User calls the Picker Presence endpoint to get the checked-in Pickers
    Then verify that status code be equal to "200"
    And Verify the Checked-In Picker details

  @AssignPicker
  Scenario: Assign new Delivery to Picker with valid data
    And Get delivery details and assigneeId to assign delivery to picker
    And Provide huMaterial and destSiteId for a delivery to be assigned
      | huMaterial | 201 |
    When User calls the Assign Picker endpoint to assign delivery to Picker
    Then verify that status code be equal to "200"

  @TOBYDELIVERYNUM
  Scenario: Get TO's Details with valid data
    And Get delivery number to get the details of TO
    When User calls the Get TO details by delivery numbers endpoint to get TO's details
    Then verify that status code be equal to "200"
    And Verify that items are assigned to Picker

  @GETPICKER
  Scenario: Verify the functionality of Get Picker
    And Provide dcId to get the checked-in Picker's details
      | siteId | 1014 |
    When User calls the Get Picker endpoint to get checked-in Pickers
    Then verify that status code be equal to "200"
    And Verify that delivery is assigned to picker

  @VehicleAssign
  Scenario: Verify vehicle is assigned for a Delivery with valid data
    And Provide dock and get delivery number to which vehicle is to be assigned
      | dock | A1 |
    And Provide vehicle type and vehicle number for a delivery to be assigned vehicle
      | vehicleType | vehicleRegNum |
      | ACE         | MH02MB9876    |
    When User calls the assign vehicle's endpoint to assign vehicle for a delivery
    Then verify that status code be equal to "200"

  @DispatcDeliveriesDetails
  Scenario: Verify user gets the list of dispatch delivery
    When User calls the dispatch delivery endpoint to get the list of dispatch delivery
    Then verify that status code be equal to "200"
    And Verify that vehicle is assigned for a delivery