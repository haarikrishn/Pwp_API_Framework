Feature: Verify the functionality of CreateDelivery Endpoint
  Background: access token id by
    Given Give Username and Password for Access Token
      | username  | integrator       |
      | password  | 3poXy$6o29       |
      | requestId | 0000001376364111 |

  @CreateOneDeliveryWithListOfItemsUsingDT
  Scenario: Verify the functionality of CreateOneDeliveryWithListOfItemsUsingDT
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


#  =========================================================================================
  @CreateOneDeliveryWithListOfItemsUsingSo
#    creating one delivery with list of delivery items
  Scenario Outline: Verify the functionality of Createdelivery
    When  User calls the Create Delivery endPoint "<srcSiteId>" "<dstSiteId>" "<whNumber>" "<createdBy>" "<totalGdsMvtStat>" "<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<itemSeq1>"	"<stoNum1>" "<stoLineNum1>" "<ean1>" "<mrp1>" "<matNum1>"  "<itemDesc1>" "<matGrp1>"	"<catInd1>"	"<stLoc1>" "<batch1>"	"<proposedDlvQty1>"	"<caselot1>"	"<uom1>"	"<itemWt1>"	"<uomWt1>"	"<itemVol1>"	"<uomVol1>"	"<pickStatus1>" "<itemSeq2>"	"<stoNum2>" "<stoLineNum2>" "<ean2>" "<mrp2>" "<matNum2>"  "<itemDesc2>" "<matGrp2>"	"<catInd2>"	"<stLoc2>" "<batch2>"	"<proposedDlvQty2>"	"<caselot2>"	"<uom2>"	"<itemWt2>"	"<uomWt2>"	"<itemVol2>"	"<uomVol2>"	"<pickStatus2>"
    Then verify that status code be equal to 201 for createDelivery
    Examples:
      | srcSiteId | dstSiteId | whNumber | createdBy | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp   | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | itemSeq1 | stoNum1    | stoLineNum1 | ean1          | mrp1   | matNum1   | itemDesc1                          | matGrp1   | catInd1 | stLoc1 | batch1     | proposedDlvQty1 | caselot1 | uom1 | itemWt1 | uomWt1 | itemVol1 | uomVol1 | pickStatus1 | itemSeq2 | stoNum2    | stoLineNum2 | ean2          | mrp2   | matNum2   | itemDesc2                           | matGrp2   | catInd2 | stLoc2 | batch2     | proposedDlvQty2 | caselot2 | uom2 | itemWt2 | uomWt2 | itemVol2 | uomVol2 | pickStatus2 |
      | 1014      | 4019      | 114      | krishna   | A               | 000010  | 5101334418 | 000010     | 4902430905183 | 50.00 | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | 000020   | 5101334418 | 000020      | 8901314524010 | 170.00 | 500017575 | COLGATE MAXFRESH BLUE GEL TP(300G) | 5OCTTPGEL | F       | 3000   | 0103210170 | 96.000          | 48.00    | EA   | 0.360   | KG     | 0.027    | FT3     | A           | 000030   | 5101334418 | 000030      | 8903363007247 | 150.00 | 500017602 | GRACE CITRUS PASSI SHOWER GEL 250ML | 5PWLSPBWS | F       | 3000   | 0103210150 | 72.000          | 36.00    | EA   | 0.369   | KG     | 0.022    | FT3     | A           |

#===========================================================================================================================
#creating multiple deleveries
  @CreateMultipleDeliviriesAndVerify
  Scenario Outline: Verify the functionality of Createdelivery
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Then verify that status code be equal to 201 for createDelivery
    Given Give Username and Password for Access Token
      | username | 8317454007 |
      | password | Sweety123@ |
    When   send GetDelivery End point to get the data
    Then verify delivernumber is present or not
    And verify that status code be equal to 200 for GetListofDeliveries
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp    | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs          |
      |           | 1014      | 4019      | 114      | MHK       |              | A               | 000010  | 5101334412 | 000010     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | create multiple deliveries |
      |           | 1014      | 5019      | 114      | hari      |              | B               | 000020  | 5101334413 | 000020     | 8901314524010 | 170.00 | 500017575 | COLGATE MAXFRESH BLUE GEL TP(300G) | 5OCTTPGEL | G      | 3000  | 0103210170 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.027   | FT3    | A          | create multiple deliveries |
      |           | 1014      | 4019      | 114      | ravi      |              | C               | 000030  | 5101334414 | 000030     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | create multiple deliveries |
      |           | 1014      | 5019      | 114      | ramu      |              | D               | 000040  | 5101334415 | 000040     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | create multiple deliveries |
      |           | 1014      | 4019      | 114      | krishna   |              | E               | 000050  | 5101334416 | 000050     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | create multiple deliveries |
      |           | 1014      | 5019      | 114      | raju      |              | A               | 000060  | 5101334417 | 000060     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | create multiple deliveries |

#=================================================================================================================================
  @CreateDeliveyToPassingEmptyDataToSourceIdAndDestIDOne
  Scenario Outline: Verify the functionality of Createdelivery
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Then verify that status code be equal to 400 for createDelivery
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp   | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs                                               |
      |           |           |           | 114      | ABC       |              | A               | 000020  | 5101334418 | 000010     | 4902430905183 | 50.00 | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | passing empty data to sourceid and desionation id configuration |
##========================================================================
  @CreateDeliveyValidSourceIdAndInvalidDestinationIdConfigurationTwo
  Scenario Outline: Verify the functionality of Createdelivery
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Then verify that status code be equal to 400 for createDelivery
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp   | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs                                                                    |
      |           | 1014      | 4044      | 114      | ABC       |              | A               | 000030  | 5101334418 | 000010     | 4902430905183 | 50.00 | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | valid source sit id and invalid destination id configuration (invalid configuration) |

#  =======================================================================
  @CreateDeliveryInValidSourceIdAndvalidDestinationIdConfigurationThree
  Scenario Outline: Verify the functionality of Createdelivery
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp   | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs                                                                     |
      |           | 1010      | 5019      | 114      | ABC       |              | A               | 000040  | 5101334418 | 000010     | 4902430905183 | 50.00 | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | invalid source sit id and valid destination id configuration  (invalid configuration) |
#  =============================================================================================================
  @CreateDeliveryWithPwpConfigEnable
  Scenario Outline: Verify the functionality of Createdelivery for NegativeScenarios
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp   | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs                 |
      |           | 1014      | 5019      | 114      | hari      |              | A               | 000010  | 5101334418 | 000010     | 4902430905183 | 50.00 | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | with   4019 configuration disable |
#    ======================================================================================================================

  @CreateDeliveryWithPwpConfigDisable
  Scenario Outline: Verify the functionality of Createdelivery for NegativeScenarios
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp   | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs                 |
      |           | 1013      | 4044      | 114      | yash      |              | A               | 000010  | 5101334418 | 000010     | 4902430905183 | 50.00 | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | with   4019 configuration disable |
#    ======================================================================================================================
  @CreateDeliveryWithBeforeFifteenDaysOfDateAtTimeOfCreation
  Scenario Outline: Verify the functionality of Createdelivery for NegativeScenarios
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp   | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs                                |
      |           | 1014      | 4019      | 114      | ABC       | 20231210     | A               | 000010  | 5101334418 | 000010     | 4902430905183 | 50.00 | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | with before 15 days of date ,at time of creation |

#  =========================================================================================================
  @CreateDeliveryWithInvalidDeliveryNumber
  Scenario Outline: Verify the functionality of Createdelivery for NegativeScenarios
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp   | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs                             |
      | abcdefg   | 1014      | 4019      | 114      | ABC       | 20231210     | A               | 000010  | 5101334418 | 000010     | 4902430905183 | 50.00 | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | creating elivery with invalid delivery number |
# ====================================================================================================================
  @CreateDeliveryWithInvalidEAnNumber
  Scenario Outline: Verify the functionality of Createdelivery for NegativeScenarios
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean    | mrp   | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs                             |
      |           | 1014      | 4019      | 114      | ABC       | 20231210     | A               | 000010  | 5101334418 | 000010     | abcdef | 50.00 | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | creating elivery with invalid delivery number |
#====================================================================================================

  @CreateDeliveryWithoutDeliveryNumberUsingJsonFileArray
  Scenario Outline: verify create Delivery with invalid delivery number
    When user calls createDelivery end point to create delivery in NeattivePointOfview "<jsonFileName>"
    Examples:
      | jsonFileName                             |
      | CreateDeliveryWithOutDeliverynumber.json |
#      | CreateDelivery.json |

#  =====================================================================================================
  @CreateDeliveryOneSourceSitedIdDifferentDestinationId
  Scenario Outline: Verify the functionality of Createdelivery for NegativeScenarios
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Then verify that status code be equal to 400 for createDelivery
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp    | matNum    | itemDesc                            | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs              |
      |           | 1014      | 4019      | 114      | raju      |              | E               | 000050  | 5101334418 | 000050     | 8903          | 149.00 | 500017671 | MEADOWS AIR FRESHENER LAV BLS-240ML | 5AFHFRSPR | G      | 7000  | 0103210149 | 26.000         | 78.00   | EA  | 0.242  | KG    | 0.078   | FT3    | E          | invalid Ean number             |
      |           | 1014      | 5019      | 114      | krishh    |              | F               | 000060  | 5101334418 | 000060     | 8901248154031 | 110.00 | 500018038 | BOROPLUS ALOV HAL CH KES GEL(150ML) | 5SKCSLFWS | A      | 8000  | 0103210110 | 56.000         | 98.00   | EA  | 0.028  | KG    | 0.014   | FT3    | S          | with invalid ware house number |
      |           | 1014      | 4044      | 114      | yash      |              | A               | 000070  | 5101334418 | 000070     | 4987176023476 | 199.00 | 500018258 | PAMPERS ALOE BABY WIPS(72N)         | 5PHICRTSW | FB     | 1000  | 0101210199 | 16.000         | 88.00   | EA  | 0.099  | KG    | 0.061   | FT3    | M          |                                |
      |           | 1014      | 4021      | 114      | ABC       |              | A               | 000070  | 5101334418 | 000070     | 4987176023476 | 199.00 | 500018258 | PAMPERS ALOE BABY WIPS(72N)         | 5PHICRTSW | FB     | 1000  | 0101210199 | 16.000         | 88.00   | EA  | 0.099  | KG    | 0.061   | FT3    | M          |                                |

#========================================================================================
  @CreateMultipleDeliviries
  Scenario Outline: Verify the functionality of Createdelivery
    When User calls the Create Delivery endPoint for Negative point of view "<dlvNumber>" "<srcSiteId>" "<dstSiteId>"	"<whNumber>" "<createdBy>" "<creationDate>" "<totalGdsMvtStat>" 	"<itemSeq>"	"<stoNum>" "<stoLineNum>" "<ean>" "<mrp>" "<matNum>"  "<itemDesc>" "<matGrp>"	"<catInd>"	"<stLoc>" "<batch>"	"<proposedDlvQty>"	"<caselot>"	"<uom>"	"<itemWt>"	"<uomWt>"	"<itemVol>"	"<uomVol>"	"<pickStatus>" "<CurrentScenarioIs>"
    Then verify that status code be equal to 201 for createDelivery
    Examples:
      | dlvNumber | srcSiteId | dstSiteId | whNumber | createdBy | creationDate | totalGdsMvtStat | itemSeq | stoNum     | stoLineNum | ean           | mrp    | matNum    | itemDesc                           | matGrp    | catInd | stLoc | batch      | proposedDlvQty | caselot | uom | itemWt | uomWt | itemVol | uomVol | pickStatus | CurrentScenarioIs          |
      |           | 1014      | 4019      | 114      | MHK       |              | A               | 000010  | 5101334412 | 000010     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | create multiple deliveries |
      |           | 1014      | 5019      | 114      | hari      |              | B               | 000020  | 5101334413 | 000020     | 8901314524010 | 170.00 | 500017575 | COLGATE MAXFRESH BLUE GEL TP(300G) | 5OCTTPGEL | G      | 3000  | 0103210170 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.027   | FT3    | A          | create multiple deliveries |
      |           | 1014      | 4019      | 114      | ravi      |              | C               | 000030  | 5101334414 | 000030     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | create multiple deliveries |
      |           | 1014      | 5019      | 114      | ramu      |              | D               | 000040  | 5101334415 | 000040     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | create multiple deliveries |
      |           | 1014      | 4019      | 114      | MHK       |              | E               | 000050  | 5101334416 | 000050     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | create multiple deliveries |
      |           | 1014      | 5019      | 114      | raju      |              | A               | 000060  | 5101334417 | 000060     | 4902430905183 | 50.00  | 500017406 | ORAL B KIDS 2+ YEAR TOOTHBRUSH(3N) | 5OCTBRBAB | F      | 3000  | 2503210050 | 96.000         | 48.00   | EA  | 0.067  | KG    | 0.021   | FT3    | A          | create multiple deliveries |


















