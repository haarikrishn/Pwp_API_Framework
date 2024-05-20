package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GetTOstep implements ConStants{
    Response GetTOResponse;
    RequestGenerator requestGenerator=new RequestGenerator();


    @When("send GetTO End point to get the data")
    public void sendGetTOEndPointToGetTheData() {

        HashMap<String, String> queryparamGetTo = new HashMap<>();
        String originaldlvNumber = CommonUtilities.getDeliveryNumber();
        queryparamGetTo.put("dlvNumber", originaldlvNumber);

        GetTOResponse = requestGenerator.getRequestWithQueryParam(CommonUtilities.genericHeader(), queryparamGetTo).log().all()
                .when().get(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "GetToEndPoint"));
        GetTOResponse.then().log().all();

        ExtentReportManager.logJson("Response is " + GetTOResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + GetTOResponse.getStatusCode());
        long responseTime = GetTOResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(GetTOResponse);
    }

    @And("validate Response body of  GetToEndPoint")
    public void validateResponseBodyOfGetToEndPoint() {
        String ExpectedTxfOrdNumber = GetTOResponse.jsonPath().get("txfOrdNumber");
        String ExpectedSrcPlant = GetTOResponse.jsonPath().get("srcPlant");
        String ExpectedDestPlant = GetTOResponse.jsonPath().get("destPlant");
        String ExpectedDispatchType = GetTOResponse.jsonPath().get("dispatchType");
        String ExpectedDlvNumber = GetTOResponse.jsonPath().get("dlvNumber");
        String ExpectedToStatus = GetTOResponse.jsonPath().get("status");
        //   String ExpectedTotalBoxes = GetTOResponse.jsonPath().get("totalBoxes");
        //    float expectedTotalBoxesFloat = Float.parseFloat(ExpectedTotalBoxes);

        String actualtxfOrdNumber = CommonUtilities.gettxfOrdNumber();
        String actualSrcPlant = CreateTOStep.createToMainPojo.getSrcPlant();
        String actualdstPlant = CreateTOStep.createToMainPojo.getDestPlant();
        String actualToStatus = "Unassigned";
        String actualDlvNumber = CommonUtilities.getDeliveryNumber();


        List<Map<String,Integer>> transferOrderItemsBody = GetTOResponse.jsonPath().get("transferOrderItems");
        int ExpectedtransferOrderItems = transferOrderItemsBody.size();
        int ActualtransferOrderItems = CommonUtilities.getTransferOrderItemsCount();
        System.out.println("expected no.of Items are"+"================="+ExpectedtransferOrderItems);
        System.out.println("Actual no.of Items are"+"===================="+ActualtransferOrderItems);
//validating no.of items in the particular delivery number
        if (ExpectedtransferOrderItems==ActualtransferOrderItems) {
            ExtentReportManager.logInfoDetails("verifying No.of items");
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedtransferOrderItems is " + ExpectedtransferOrderItems + " and the ActualtransferOrderItems is " + ActualtransferOrderItems);
            Assert.assertEquals(ExpectedtransferOrderItems, ActualtransferOrderItems);
            System.out.println(ExpectedtransferOrderItems + " ===================>" + "ExpectedtransferOrderItems validation successful");
            System.out.println("TO is created for given no.of items"+"==============="+ExpectedtransferOrderItems);

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedtransferOrderItems is  " + ExpectedtransferOrderItems + " and the ActualtransferOrderItems is " + ActualtransferOrderItems);
        }


// validating TxfOrdNumber
        if (ExpectedTxfOrdNumber.equals(actualtxfOrdNumber)) {
            ExtentReportManager.logInfoDetails("verifying txfOrdNumber");
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedTxfOrdNumber is " + ExpectedTxfOrdNumber + " and the actualtxfOrdNumber is " + actualtxfOrdNumber);
            Assert.assertEquals(ExpectedTxfOrdNumber, actualtxfOrdNumber);
            System.out.println(ExpectedTxfOrdNumber + " ===================>" + "TxfOrdNumber validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedTxfOrdNumber is  " + ExpectedTxfOrdNumber + " and the actualtxfOrdNumber is " + actualtxfOrdNumber);
        }
// validating SrcPlant
        if (ExpectedSrcPlant.equals(actualSrcPlant)) {
            ExtentReportManager.logInfoDetails("verifying SrcPlant");
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedSrcPlant is " + ExpectedSrcPlant + " and the actualSrcPlant is " + actualSrcPlant);
            Assert.assertEquals(ExpectedSrcPlant, actualSrcPlant);
            System.out.println(ExpectedSrcPlant + " ===================>" + "SrcPlant validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedSrcPlant is  " + ExpectedSrcPlant + " and the actualSrcPlant is " + actualSrcPlant);
        }
// validating dstPlant
        if (ExpectedDestPlant.equals(actualdstPlant)) {
            ExtentReportManager.logInfoDetails("verifying DestPlant");
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedDestPlant is " + ExpectedDestPlant + " and the actualdstPlant is " + actualdstPlant);
            Assert.assertEquals(ExpectedDestPlant, actualdstPlant);
            System.out.println(ExpectedDestPlant + " ===================>" + "DestPlant validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedDestPlant is  " + ExpectedDestPlant + " and the actualdstPlant is " + actualdstPlant);
        }
// validating DlvNumber
        if (ExpectedDlvNumber.equals(actualDlvNumber)) {
            ExtentReportManager.logInfoDetails("verifying ExpectedDlvNumber");
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedDlvNumber is " + ExpectedDlvNumber + " and the actualDlvNumber is " + actualDlvNumber);
            Assert.assertEquals(ExpectedDlvNumber, actualDlvNumber);
            System.out.println(ExpectedDlvNumber + " ===================>" + "DlvNumber validation successful");
            System.out.println("=========================>"+"TO craeted Successfully");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedDlvNumber is  " + ExpectedDlvNumber + " and the actualDlvNumber is " + actualDlvNumber);
            System.out.println("delivery number is not present");
            System.out.println("=========================>"+"TO not created,validation fail");
        }
// validating ToStatus
        if (ExpectedToStatus.equals(actualToStatus)) {
            ExtentReportManager.logInfoDetails("verifying ExpectedToStatus");
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpectedToStatus is " + ExpectedToStatus + " and the actualToStatus is " + actualToStatus);
            Assert.assertEquals(ExpectedToStatus, actualToStatus);
            System.out.println(ExpectedToStatus + " ===================>" + "Status validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("ExpectedToStatus is  " + ExpectedToStatus + " and the actualToStatus is " + actualToStatus);
        }
// validating DispatchType.
        if (ExpectedDispatchType.equals("Box")) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpecteddispatchType is " + ExpectedDispatchType + " and the actual dstSiteId is " + ExpectedDestPlant);
            Assert.assertEquals(ExpectedDispatchType, "Box");
            System.out.println(ExpectedDispatchType + " ===================>" + ExpectedDestPlant + "   " + "dispatchType for current dstSiteId validation successful");
            Object ExpectetedTotalBoxes = GetTOResponse.jsonPath().get("totalBoxes");
            int ExpectetedTotalBoxess = (int)ExpectetedTotalBoxes;

            System.out.println("ExpectetedTotalBoxess ===============> "+ExpectetedTotalBoxess);
            int ActualTotalBoxes = CommonUtilities.gettotalboxescount();
            System.out.println("Actual total boxes is "+"===================="+ActualTotalBoxes);

            if(ExpectetedTotalBoxess==ActualTotalBoxes)
            {
                System.out.println("the box quantity  not exceeded case slot quantity");
                System.out.println("total boxes validation succesful");
                ExtentReportManager.logInfoDetails("total boxes validation succesful,count is"+ExpectetedTotalBoxess);

            }
            else
            {
                System.out.println("the box quantity  exceeded case slot quantity");
            }
        } else if (ExpectedDispatchType.equals("Pallet")) {
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("ExpecteddispatchType is " + ExpectedDispatchType + "   " + " and the actual dstSiteId is " + ExpectedDestPlant);
            Assert.assertEquals(ExpectedDispatchType, "Pallet");
            System.out.println(ExpectedDispatchType + " ===================>" + ExpectedDestPlant + "          " + "dispatch Type for current dstSiteId validation successful");

        } else {
            ExtentReportManager.logFailureDetails("Failed");
            ExtentReportManager.logInfoDetails("dispatch type and destination site id not match");
            System.out.println("dispatch type and destination site id not match");
        }
    }
}
