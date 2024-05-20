package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import com.dmartLabs.stepdefinitions.CommonUtilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import rst.pdfbox.layout.text.Constants;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.JSON_SCHEMA_VALIDATION_PATH;

public class GetListOfDeleviriesStep extends Constants implements  ConStants {
    RequestGenerator requestGenerator = new RequestGenerator();
    Response GetListOfDeliveriesResponse;

    @When("send GetDelivery End point to get the data")
    public void sendGetDeliveryEndPointToGetTheData() {
        GetListOfDeliveriesResponse = requestGenerator.getRequestGet(CommonUtilities.genericHeader()).log().all()
                .when().get(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "GetListOfDeliveriesEndPoint"));
        GetListOfDeliveriesResponse.then().log().all();

        ExtentReportManager.logJson("Response is " + GetListOfDeliveriesResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + GetListOfDeliveriesResponse.getStatusCode());
        long responseTime = GetListOfDeliveriesResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(GetListOfDeliveriesResponse);
    }

    @Then("verify that status code be equal to {int} for GetListofDeliveries")
    public void verifyThatStatusCodeBeEqualToForGetListofDeliveries(int expectedStatusCode) {
        ExtentReportManager.logInfoDetails("Validate that status code is 200");
        if (GetListOfDeliveriesResponse.getStatusCode() == expectedStatusCode) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("Expected status code is " + expectedStatusCode + " and the actual status code is " + GetListOfDeliveriesResponse.getStatusCode());
            System.out.println("generate token status code is validated");
        } else {
            ExtentReportManager.logPassDetails("failed");
            ExtentReportManager.logInfoDetails("Expected status code is " + expectedStatusCode + " and the actual status code  is " + GetListOfDeliveriesResponse.getStatusCode());
        }
        Assert.assertEquals(GetListOfDeliveriesResponse.statusCode(), expectedStatusCode);
    }

    //======================================================================================================================
    @And("validate Response body of getListOfDeleveries")
    public void validateResponseBodyOfGetListOfDeleveries() {
        List<Map<String, Object>> GetdeliverySchemabody = GetListOfDeliveriesResponse.jsonPath().get();
        boolean flag = true;
        for (int i = 0; i < GetdeliverySchemabody.size(); i++) {
            if (CreateDeliveryStep.createdeliverypojo.getDlvNumber().equals(GetdeliverySchemabody.get(i).get("dlvNumber"))) {
                    System.out.println("searching for delivery number");
                    Map<String, Object> FinalGetDeliveryBody = GetdeliverySchemabody.get(i);
                    System.out.println("=================================================================");
                    System.out.println("FinalResponseBody" + "    " + FinalGetDeliveryBody);
                    System.out.println("====================================================================");
                    String ExpectedDeliveryNumber = (String)FinalGetDeliveryBody.get("dlvNumber");
                    String ExpectedDeliveryStatus = (String)FinalGetDeliveryBody.get("deliveryStatus");
                    String ExpectedSourceSiteId = (String)FinalGetDeliveryBody.get("srcSiteId");
                    String ExpectedDestinationId = (String)FinalGetDeliveryBody.get("dstSiteId");
                    String ExpecteddispatchType = (String)FinalGetDeliveryBody.get("dispatchType");
                    String ActualDeliveryNumber = CreateDeliveryStep.createdeliverypojo.dlvNumber;
                    String ActualSourceSiteId = CreateDeliveryStep.createdeliverypojo.srcSiteId;
                    String ActualDestinationSiteId = CreateDeliveryStep.createdeliverypojo.dstSiteId;
                    String ActualDeliveryStatus = "CREATED";


                    System.out.println(ExpectedDeliveryNumber + " " + "is expected delivery number");
                    System.out.println(ActualDeliveryNumber + " " + "is actual delivery number ");
                    //validating delivery number
                    if (ExpectedDeliveryNumber.equals(ActualDeliveryNumber)) {
                        ExtentReportManager.logPassDetails("Passed");
                        ExtentReportManager.logInfoDetails("Expected delivery number is " + ExpectedDeliveryNumber + " and the actual deliveryNumber is " + ActualDeliveryNumber);
                        Assert.assertEquals(ExpectedDeliveryNumber, ActualDeliveryNumber);
                        System.out.println(ExpectedDeliveryNumber + " ===================>" + "deliveryNumber validation successful");

                    } else {
                        ExtentReportManager.logFailureDetails("Failed");
                        ExtentReportManager.logInfoDetails("Expected delivery number is " + ExpectedDeliveryNumber + " and the actual deliveryNumber is " + ActualDeliveryNumber);
                    }
                    //validating delivery status
                    if (ExpectedDeliveryStatus.equals(ActualDeliveryStatus)) {
                        ExtentReportManager.logPassDetails("Passed");
                        ExtentReportManager.logInfoDetails("Expected deliveryStatus is " + ExpectedDeliveryStatus + " and the actual deliveryStatus is " + ActualDeliveryStatus);
                        Assert.assertEquals(ExpectedDeliveryStatus, ActualDeliveryStatus);
                        System.out.println(ExpectedDeliveryStatus + " ===================>" + "deliveryStatus validation successful");

                    } else {
                        ExtentReportManager.logFailureDetails("Failed");
                        ExtentReportManager.logInfoDetails("deliveryStatus is" + ExpectedDeliveryStatus + " and the actual deliveryStatus is " + ActualDeliveryStatus);

                    }
                    //validating source sit id
                    if (ExpectedSourceSiteId.equals(ActualSourceSiteId)) {
                        ExtentReportManager.logPassDetails("Passed");
                        ExtentReportManager.logInfoDetails("Expected srcSiteId number is " + ExpectedSourceSiteId + " and the actual srcSiteId is " + ActualSourceSiteId);
                        Assert.assertEquals(ExpectedSourceSiteId, ActualSourceSiteId);
                        System.out.println(ExpectedSourceSiteId + " ===================>" + "srcSiteId validation successful");

                    } else {
                        ExtentReportManager.logFailureDetails("Failed");
                        ExtentReportManager.logInfoDetails("Expected srcSiteId number is " + ExpectedSourceSiteId + " and the actual srcSiteId is " + ActualSourceSiteId);

                    }
                    //validating destination site id
                    if (ExpectedDestinationId.equals(ActualDestinationSiteId)) {
                        ExtentReportManager.logPassDetails("Passed");
                        ExtentReportManager.logInfoDetails("Expected dstSiteId number is " + ExpectedDestinationId + " and the actual dstSiteId is " + ActualDestinationSiteId);
                        Assert.assertEquals(ExpectedDestinationId, ActualDestinationSiteId);
                        System.out.println(ExpectedDestinationId + " ===================>" + "dstSiteId validation successful");

                    } else {
                        ExtentReportManager.logFailureDetails("Failed");
                        ExtentReportManager.logInfoDetails("Expected dstSiteId number is " + ExpectedDestinationId + " and the actual dstSiteId is " + ActualDestinationSiteId);

                    }
                    //validating dispatch type
                    if (ExpecteddispatchType.equals("Box")) {
                        ExtentReportManager.logPassDetails("Passed");
                        ExtentReportManager.logInfoDetails("ExpecteddispatchType is " + ExpecteddispatchType + " and the actual dstSiteId is " + ExpectedDestinationId);
                        Assert.assertEquals(ExpecteddispatchType, "Box");
                        System.out.println(ExpecteddispatchType + " ===================>" + ExpectedDestinationId + "   " + "dispatchType for current dstSiteId validation successful");
//                        dlvmainpojo.setProposedDlvQty(proposedDlvQty);
//                        dlvmainpojo.setCaselot(caselot);
                        Object actualTotalBoxes = FinalGetDeliveryBody.get("totalBoxes");
                        int actualTotalBoxess = (int)actualTotalBoxes;

                        System.out.println("actualTotalBoxes ===============> "+actualTotalBoxess);
//                        int ActualproposedDlvQty = Integer.parseInt(CreateDeliveryStep.dlvmainpojo.proposedDlvQty);
//                        int ActualCaseslot = Integer.parseInt(CreateDeliveryStep.dlvmainpojo.caselot);
//                        int ActualCount = ActualproposedDlvQty/ActualCaseslot;
////                        System.out.println(ActualCount);
////                        System.out.println(ExpectedCount);
                        if(actualTotalBoxess<=CommonUtilities.expectedTotalBoxes)
                        {
                            System.out.println("the box quantity  not exceeded case slot quantity");
                        }
                        else
                        {
                            System.out.println("the box quantity  exceeded case slot quantity");
                        }

                    } else if (ExpecteddispatchType.equals("Pallet")) {
                        ExtentReportManager.logPassDetails("Passed");
                        ExtentReportManager.logInfoDetails("ExpecteddispatchType is " + ExpecteddispatchType + "   " + " and the actual dstSiteId is " + ExpectedDestinationId);
                        Assert.assertEquals(ExpecteddispatchType, "Pallet");
                        System.out.println(ExpecteddispatchType + " ===================>" + ExpectedDestinationId + "          " + "dispatch Type for current dstSiteId validation successful");

                    } else {
                        ExtentReportManager.logFailureDetails("Failed");
                        ExtentReportManager.logInfoDetails("dispatch type and destination site id not match");
                        System.out.println("dispatch type and destination site id not match");
                        System.out.println("delivery number not match");
                    }
//                flag=true;
                  break;
            }
            else{
                flag = false;
            }
            if (i==GetdeliverySchemabody.size()-1){
                if (!flag){
                    System.out.println("==============================================================");
                    System.out.println("delivery number is not present ");
                }
            }
            }

        }
    //validating schema
    @And("validating JsonSchema Of object {string}")
    public void validatingJsonSchemaOfObject(String schemaFile) {
        ExtentReportManager.logInfoDetails("verify that schema should be equal {string}");
        CommonUtilities.getResponseInstance().then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(PropertyReader.fileReaders(JSON_SCHEMA_VALIDATION_PATH, schemaFile)));
        JsonSchemaValidator schema = JsonSchemaValidator.matchesJsonSchema(PropertyReader.fileReaders(JSON_SCHEMA_VALIDATION_PATH, schemaFile));
        ExtentReportManager.logInfoDetails(" Schema is pass " + schema);
        System.out.println("===================================================================================");
        System.out.println("schema validation succesful" + "=============================>");

    }

    //===============================================================================================
    //verify delivery number is present or not
    @Then("verify delivernumber is present or not")
    public void verifyDelivernumberIsPresentOrNot() {
        System.out.println(CreateDeliveryStep.createdeliverypojo.getDlvNumber() + "====================> pojo delivery number(actual delivery number)");
        List<Map<String, String>> GetdeliverySchemabody = GetListOfDeliveriesResponse.jsonPath().get();
        boolean flag = true;
        for (int i = 0; i < GetdeliverySchemabody.size(); i++) {
            //     System.out.println(GetdeliverySchemabody.get(i).get("dlvNumber"));
            if (CreateDeliveryStep.createdeliverypojo.getDlvNumber().equals(GetdeliverySchemabody.get(i).get("dlvNumber"))) {
                    System.out.println("searching for delivery number");
                    Map<String, String> FinalGetDeliveryBody = GetdeliverySchemabody.get(i);
                    System.out.println("=================================================================");
                    System.out.println("FinalResponseBody" + "    " + FinalGetDeliveryBody);
                    System.out.println("====================================================================");
                    String ExpectedDeliveryNumber = FinalGetDeliveryBody.get("dlvNumber");
                    String ActualDeliveryNumber = CreateDeliveryStep.createdeliverypojo.dlvNumber;
                    System.out.println(ActualDeliveryNumber + "is  actual delivery number ");
                    System.out.println(ExpectedDeliveryNumber + "is  Expected delivery number");
                    //validating delivery number
                    if (ExpectedDeliveryNumber.equals(ActualDeliveryNumber)) {
                        ExtentReportManager.logPassDetails("Passed");
                        ExtentReportManager.logInfoDetails("Expected delivery number is " + ExpectedDeliveryNumber + " and the actual deliveryNumber is " + ActualDeliveryNumber);
                        Assert.assertEquals(ExpectedDeliveryNumber, ActualDeliveryNumber);
                        System.out.println(ExpectedDeliveryNumber + " ===================>" + "deliveryNumber is present and validation is successful");

                    } else {
                        ExtentReportManager.logFailureDetails("Failed");
                        ExtentReportManager.logInfoDetails("Expected delivery number is " + ExpectedDeliveryNumber + " and the actual deliveryNumber is " + ActualDeliveryNumber);
                        System.out.println(ExpectedDeliveryNumber + " ===================>" + "deliveryNumber is not present and validation is fail");
                    }
//                flag=true;
                break;
                }
            else{
                flag = false;
            }
            if (i==GetdeliverySchemabody.size()-1){
                if (!flag){
                    System.out.println("=======================================================================");
                    System.out.println("@delivery number is not present@.................... ");

                }
            }
            }
        }
    }



