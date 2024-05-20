Feature: Verify the functionality of Create Transfer Order Endpoint
  Background: access token id by
    Given Give Username and Password for Access Token
      | username  | integrator       |
      | password  | 3poXy$6o29       |
      | requestId | 0000001376364111 |
  @CreateTOForOriginalDelivery
  Scenario: Verify the functionality of CreateTOWithListOftransferOrderItems
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
    When User calls the to create TO End point to CreateTOWithListOftransferOrderItems
    Then verify that status code be equal to "201"
#==========================================================================================

#  @CreateTOWithoutConfirmDelivery
  Scenario: Verify the functionality of CreateTOWithListOftransferOrderItems
    And Assigning Main Mandatory fields to CreateTOWithListOftransferOrderItems
      | whNumber     | 139        |
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
    When User calls the to create TO End point to CreateTOWithListOftransferOrderItems
    Then verify that status code be equal to "201"
# ====================================================================================================
#  @CreateTOwithValidSourceIdAndInvalidDestinationID
  Scenario: Verify the functionality of CreateTOWithListOftransferOrderItems
    And Assigning Main Mandatory fields to CreateTOWithListOftransferOrderItems
      | whNumber     | 139        |
      | srcPlant     | 1014       |
      | destPlant    | 4044       |
      | grpDlv       | 0004362630 |
      | movementType | 850        |
    And Provide transferOrderItems to CreateTOWithListOftransferOrderItems
      | txfOrdItemSeq | matNumber | matDesc                             | ean           | batchNum   | srcStorageType | srcStorageBin | srcQty | caselot | uom | mrp    | itemWeight | itemVolume | itemType | binSeq | bbDate | storageLoc | destStorageType | palletQty |
      | 0001          | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N)  | 4902430905183 | 2503210050 | FRS            | A030-14-1B    | 96.000 | 48.00   | EA  | 50.00  | 0.067      | 0.021      | ZFNF     | 3982   |        | 3000       | 200             | 1728.000  |
      | 0002          | 500017575 | COLGATE MAXFRESH BLUE GEL TP(300G)  | 8901314524010 | 0103210170 | FRS            | A030-14-1B    | 96.000 | 48.00   | EA  | 170.00 | 0.360      | 0.027      | ZFNF     | 3904   |        | 3000       | 200             | 1440.000  |
      | 0003          | 500017602 | GRACE CITRUS PASSI SHOWER GEL 250ML | 8903363007247 | 0103210150 | FRS            | A033-07-1A    | 72.000 | 36.00   | EA  | 150.00 | 0.369      | 0.022      | ZFNF     | 4143   |        | 3000       | 200             | 1440.000  |
      | 0004          | 500017603 | GRACE DEEP IMPACT SHOWER GEL 250M   | 8903363007254 | 0103210150 | FRS            | A033-29-1A    | 72.000 | 36.00   | EA  | 150.00 | 0.303      | 0.022      | ZFNF     | 4319   |        | 3000       | 200             | 1440.000  |
      | 0005          | 500017671 | MEADOWS AIR FRESHENER LAV BLS-240ML | 8903363006868 | 0103210149 | FRS            | A032-09-1B    | 96.000 | 48.00   | EA  | 149.00 | 0.242      | 0.032      | ZFNF     | 4161   |        | 3000       | 200             | 1152.000  |
    When User calls the to create TO End point to CreateTOWithListOftransferOrderItems
    Then verify that status code be equal to "400"
#  ======================================================================================
#  @CreateTOwithlessThanNoOfItemsCompareToCreateDelivey
  Scenario: Verify the functionality of CreateTOWithListOftransferOrderItems
    And Assigning Main Mandatory fields to CreateTOWithListOftransferOrderItems
      | whNumber     | 139        |
      | srcPlant     | 1014       |
      | destPlant    | 4044       |
      | grpDlv       | 0004362630 |
      | movementType | 850        |
    And Provide transferOrderItems to CreateTOWithListOftransferOrderItems
      | txfOrdItemSeq | matNumber | matDesc                            | ean           | batchNum   | srcStorageType | srcStorageBin | srcQty | caselot | uom | mrp    | itemWeight | itemVolume | itemType | binSeq | bbDate | storageLoc | destStorageType | palletQty |
      | 0001          | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 4902430905183 | 2503210050 | FRS            | A030-14-1B    | 96.000 | 48.00   | EA  | 50.00  | 0.067      | 0.021      | ZFNF     | 3982   |        | 3000       | 200             | 1728.000  |
      | 0002          | 500017575 | COLGATE MAXFRESH BLUE GEL TP(300G) | 8901314524010 | 0103210170 | FRS            | A030-14-1B    | 96.000 | 48.00   | EA  | 170.00 | 0.360      | 0.027      | ZFNF     | 3904   |        | 3000       | 200             | 1440.000  |
    When User calls the to create TO End point to CreateTOWithListOftransferOrderItems
    Then verify that status code be equal to "400"
#    ==============================================================================================
#  @CreateTOwithoutDeliveryNumberUsingJSonArrayFile
  Scenario Outline: verify createTO without delivery number jsonfile
    When user calls createTO end point to createTo without deliverynumber "<jsonFileName>"
    Examples:
      | jsonFileName                       |
      | CreateToWithoutDeliveryNumber.json |
#    ===========================================================================================
#  @CreateTOwithoutDlvItemsUsingJSonArrayFile
  Scenario Outline: verify createTO without delivery number jsonfile
    When user calls createTO end point to createTo without deliverynumber "<jsonFileName>"
    Examples:
      | jsonFileName                 |
      | CreateTOwithoutDLVItems.json |