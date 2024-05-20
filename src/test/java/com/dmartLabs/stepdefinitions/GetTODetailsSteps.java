package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.datatable.TypeReference;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class GetTODetailsSteps {

    Map<String, String> queryParam;

    @And("Provide delivery number using DataTable to get the details of TO")
    public void provideDeliveryNumberToGetTheDetailsOfTO(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide delivery number using DataTable to get the details of TO");
        queryParam=dataTable.asMap(String.class, String.class);
    }

    @And("Get delivery number to get the details of TO")
    public void provideDeliveryNumberToGetTheDetailsOfTO() {
        ExtentReportManager.logInfoDetails("Provide delivery number to get the details of TO");
        queryParam = new HashMap<>();
        queryParam.put("dlvNumber", CommonUtilities.getDeliveryNumber());
//        queryParam.put("dlvNumber", "0097311239");
    }

    @And("Provide delivery number using DataTable to get the details of TO {string}")
    public void provideDeliveryNumberUsingDataTableToGetTheDetailsOfTO(String dlvNumber) {
        ExtentReportManager.logInfoDetails("Provide delivery number using DataTable to get the details of TO {string}");
        queryParam = new HashMap<>();
        if (dlvNumber.equals("null")){
            dlvNumber=null;
        }
        queryParam.put("dlvNumber", dlvNumber);
    }

    @When("User calls the Get TO details by delivery numbers endpoint to get TO's details")
    public void userCallsTheGetTODetailsByDeliveryNumbersEndpointToGetTOSDetails() {
        ExtentReportManager.logInfoDetails("User calls the Get TO details by delivery numbers endpoint to get TO's details");
        RequestSpecification requestSpecification = RequestGenerator.getRequestWithQueryParam(CommonUtilities.genericHeader(), queryParam).log().all();
        Response response = requestSpecification.get(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "GET_TO_DETAILS"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Get TO's details Response Status Code is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Get To's details Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Verify that items are assigned to Picker")
    public void verifyThatItemsAreAssignedToPicker() {
        Map<String,Object> assignPickerRequestPayload = CommonUtilities.getMapRequestPayload();
        Response response = CommonUtilities.getResponseInstance();

        if (response.jsonPath().getString("txfOrdNumber").equals((String) assignPickerRequestPayload.get("toNumber"))){
            ExtentReportManager.logPassDetails("txfOrdNumber field is Passed");
            ExtentReportManager.logInfoDetails("Expected txfOrdNumber is "+assignPickerRequestPayload.get("toNumber")+" and the Actual txfOrdNumber is "+response.jsonPath().getString("txfOrdNumber"));
        }
        else{
            ExtentReportManager.logFailureDetails("txfOrdNumber field is Failed");
            ExtentReportManager.logInfoDetails("Expected txfOrdNumber is "+assignPickerRequestPayload.get("toNumber")+" and the Actual txfOrdNumber is "+response.jsonPath().getString("txfOrdNumber"));
        }
        Assert.assertEquals(response.jsonPath().getString("txfOrdNumber"),(String) assignPickerRequestPayload.get("toNumber"));

        if (response.jsonPath().getString("srcPlant").equals(CommonUtilities.getSiteId())){
            ExtentReportManager.logPassDetails("srcPlant field is Passed");
            ExtentReportManager.logInfoDetails("Expected srcPlant is "+CommonUtilities.getSiteId()+" and the Actual srcPlant is "+response.jsonPath().getString("srcPlant"));
        }
        else {
            ExtentReportManager.logFailureDetails("srcPlant field is Failed");
            ExtentReportManager.logInfoDetails("Expected srcPlant is "+CommonUtilities.getSiteId()+" and the Actual srcPlant is "+response.jsonPath().getString("srcPlant"));
        }
        Assert.assertEquals(response.jsonPath().getString("srcPlant"), CommonUtilities.getSiteId());

        if (response.jsonPath().getString("destPlant").equals((String) assignPickerRequestPayload.get("siteId"))){
            ExtentReportManager.logPassDetails("destPlant field is Passed");
            ExtentReportManager.logInfoDetails("Expected destPlant is "+assignPickerRequestPayload.get("siteId")+" and the Actual destPlant is "+response.jsonPath().getString("destPlant"));
        }
        else {
            ExtentReportManager.logFailureDetails("destPlant field is Failed");
            ExtentReportManager.logInfoDetails("Expected destPlant is "+assignPickerRequestPayload.get("siteId")+" and the Actual destPlant is "+response.jsonPath().getString("destPlant"));
        }
        Assert.assertEquals(response.jsonPath().getString("destPlant"), (String) assignPickerRequestPayload.get("siteId"));

        List<Map<String, Object>> transferOrderItems =response.jsonPath().get("transferOrderItems");

        if (response.jsonPath().getString("dlvNumber").equals((String) assignPickerRequestPayload.get("dlvNumber"))){
            ExtentReportManager.logPassDetails("dlvNumber field is Passed");
            ExtentReportManager.logInfoDetails("Expected dlvNumber is "+assignPickerRequestPayload.get("dlvNumber")+" and the Actual dlvNumber is "+response.jsonPath().getString("dlvNumber"));
        }
        else {
            ExtentReportManager.logFailureDetails("dlvNumber field is Failed");
            ExtentReportManager.logInfoDetails("Expected dlvNumber is "+assignPickerRequestPayload.get("dlvNumber")+" and the Actual dlvNumber is "+response.jsonPath().getString("dlvNumber"));
        }
        Assert.assertEquals(response.jsonPath().getString("dlvNumber"), (String) assignPickerRequestPayload.get("dlvNumber"));

        int assignedItems = GetTODetailsSteps.validateTransferOrderStatus(response, transferOrderItems);

        GetTODetailsSteps.validateTransferOrderItems(response ,assignPickerRequestPayload);

        List<String> tolineItemNumbers = (List<String>) assignPickerRequestPayload.get("tolineItemNumbers");
        if (response.jsonPath().getInt("unassignedItems")==(transferOrderItems.size()-assignedItems)){
            ExtentReportManager.logPassDetails("unassignedItems field is Passed");
//            ExtentReportManager.logInfoDetails("Expected unassignedItems is "+(transferOrderItems.size()-tolineItemNumbers.size())+" and the Actual assignedItems is "+response.jsonPath().getInt("unassignedItems"));
            ExtentReportManager.logInfoDetails("Expected unassignedItems is "+(transferOrderItems.size()-assignedItems)+" and the Actual assignedItems is "+response.jsonPath().getInt("unassignedItems"));
        }
        else {
            ExtentReportManager.logFailureDetails("unassignedItems field is Passed");
            ExtentReportManager.logInfoDetails("Expected unassignedItems is "+(transferOrderItems.size()-assignedItems)+" and the Actual assignedItems is "+response.jsonPath().getInt("unassignedItems"));
        }
        Assert.assertEquals(response.jsonPath().getInt("unassignedItems"), (transferOrderItems.size()-assignedItems));

        if (response.jsonPath().getInt("assignedItems")==assignedItems){
            ExtentReportManager.logPassDetails("assignedItems field is Passed");
            ExtentReportManager.logInfoDetails("Expected assignedItems is "+assignedItems+" and the Actual assignedItems is "+response.jsonPath().getInt("assignedItems"));
        }
        else {
            ExtentReportManager.logPassDetails("assignedItems field is Passed");
            ExtentReportManager.logInfoDetails("Expected assignedItems is "+assignedItems+" and the Actual assignedItems is "+response.jsonPath().getInt("assignedItems"));
        }
        Assert.assertEquals(response.jsonPath().getInt("assignedItems"),assignedItems);
    }

    @And("Verify that items are not assigned to Picker")
    public void verifyThatItemsAreNotAssignedToPicker() {
        ExtentReportManager.logInfoDetails("Verify that items are not assigned to Picker");

        Map<String,Object> assignPickerRequestPayload = CommonUtilities.getMapRequestPayload();
        Response response = CommonUtilities.getResponseInstance();

        List<String> tolineItemNumbers = (List<String>) assignPickerRequestPayload.get("tolineItemNumbers");
        List<Map<String, Object>> transferOrderItems = response.jsonPath().get("transferOrderItems");
        for (int i=0; i<tolineItemNumbers.size(); i++){

            for (int j=0; j<transferOrderItems.size();j++){

                if (tolineItemNumbers.get(i).equals((String)transferOrderItems.get(j).get("txfOrdItemNumber"))){
                    ExtentReportManager.logPassDetails("transferOrderItems "+(j+1)+"'s txfOrdItemNumber field is Passed");
                    ExtentReportManager.logInfoDetails("Expected txfOrdItemNumber is "+tolineItemNumbers.get(i)+" and Actual txfOrdItemNumber is "+transferOrderItems.get(i).get("txfOrdItemNumber"));
                    Assert.assertEquals((String)transferOrderItems.get(j).get("txfOrdItemNumber"),tolineItemNumbers.get(i));

                    if (((String)transferOrderItems.get(j).get("status")).equals("Unssigned")){
                        ExtentReportManager.logPassDetails("transferOrderItems "+(j+1)+"'s status field is Passed");
                        ExtentReportManager.logInfoDetails("Expected status is Unssigned and the Actual status is "+transferOrderItems.get(j).get("status"));
                    }
                    else{
                        ExtentReportManager.logFailureDetails("transferOrderItems "+(j+1)+"'s status field is Failed");
                        ExtentReportManager.logInfoDetails("Expected status is Unssigned and the Actual status is "+transferOrderItems.get(j).get("status"));
                    }
                    Assert.assertEquals((String)transferOrderItems.get(j).get("status"),"Unssigned");

                    if (transferOrderItems.get(j).get("assigneeId")==null){
                        ExtentReportManager.logPassDetails("transferOrderItems "+(j+1)+"'s assigneeId field is Passed");
                        ExtentReportManager.logInfoDetails("Expected assigneeId is "+null+" and Actual assigneeId is "+transferOrderItems.get(j).get("assigneeId"));
                    }
                    else {
                        ExtentReportManager.logFailureDetails("transferOrderItems "+(j+1)+"'s assigneeId field is Failed");
                        ExtentReportManager.logInfoDetails("Expected assigneeId is "+assignPickerRequestPayload.get("assigneeId")+" and Actual assigneeId is "+transferOrderItems.get(j).get("assigneeId"));
                    }
                    Assert.assertEquals(transferOrderItems.get(j).get("assigneeId"), null);

                    break;
                }
            }
        }
    }

    public static int validateTransferOrderStatus(Response response,List<Map<String, Object>> transferOrderItems){
        int assignedItems = 0;
        for (Map<String, Object> transferOrderItem:transferOrderItems){
            if (((String)transferOrderItem.get("status")).equals("Assigned")){
                assignedItems++;
            }
        }

        if (assignedItems==transferOrderItems.size()){
            if (response.jsonPath().getString("status").equals("Assigned")){
                ExtentReportManager.logPassDetails("status field is Passed");
                ExtentReportManager.logInfoDetails("Expected status is Assigned and the Actual status is "+response.jsonPath().getString("status"));
            }
            else {
                ExtentReportManager.logFailureDetails("status field is Failed");
                ExtentReportManager.logInfoDetails("Expected status is Assigned and the Actual status is " + response.jsonPath().getString("status"));
            }
            Assert.assertEquals(response.jsonPath().getString("status"),"Assigned");
        }
        else {
            if (response.jsonPath().getString("status").equals("Unassigned")){
                ExtentReportManager.logPassDetails("status field is Passed");
                ExtentReportManager.logInfoDetails("Expected status is Unassigned and the Actual status is "+response.jsonPath().getString("status"));
            }
            else {
                ExtentReportManager.logFailureDetails("status field is Failed");
                ExtentReportManager.logInfoDetails("Expected status is Unassigned and the Actual status is " + response.jsonPath().getString("status"));
            }
            Assert.assertEquals(response.jsonPath().getString("status"),"Unassigned");
        }
        return assignedItems;
    }

    public static void validateTransferOrderItems(Response actualResponse, Map<String, Object> assignPickerRequestPayload) {
        List<String> tolineItemNumbers = (List<String>) assignPickerRequestPayload.get("tolineItemNumbers");
        List<Map<String, Object>> transferOrderItems = actualResponse.jsonPath().get("transferOrderItems");
        for (int i=0; i<tolineItemNumbers.size(); i++){

            for (int j=0; j<transferOrderItems.size();j++){

                if (tolineItemNumbers.get(i).equals((String)transferOrderItems.get(j).get("txfOrdItemNumber"))){
                    ExtentReportManager.logPassDetails("transferOrderItems "+(j+1)+"'s txfOrdItemNumber field is Passed");
                    ExtentReportManager.logInfoDetails("Expected txfOrdItemNumber is "+tolineItemNumbers.get(i)+" and Actual txfOrdItemNumber is "+transferOrderItems.get(i).get("txfOrdItemNumber"));
                    Assert.assertEquals((String)transferOrderItems.get(j).get("txfOrdItemNumber"),tolineItemNumbers.get(i));

                    if (((String)transferOrderItems.get(j).get("status")).equals("Assigned")){
                        ExtentReportManager.logPassDetails("transferOrderItems "+(j+1)+"'s status field is Passed");
                        ExtentReportManager.logInfoDetails("Expected status is Assigned and the Actual status is "+transferOrderItems.get(j).get("status"));
                    }
                    else{
                        ExtentReportManager.logFailureDetails("transferOrderItems "+(j+1)+"'s status field is Failed");
                        ExtentReportManager.logInfoDetails("Expected status is Assigned and the Actual status is "+transferOrderItems.get(j).get("status"));
                    }
                    Assert.assertEquals((String)transferOrderItems.get(j).get("status"),"Assigned");

                    if (((String)assignPickerRequestPayload.get("assigneeId")).equals((String)transferOrderItems.get(j).get("assigneeId"))){
                        ExtentReportManager.logPassDetails("transferOrderItems "+(j+1)+"'s assigneeId field is Passed");
                        ExtentReportManager.logInfoDetails("Expected assigneeId is "+assignPickerRequestPayload.get("assigneeId")+" and Actual assigneeId is "+transferOrderItems.get(j).get("assigneeId"));
                    }
                    else {
                        ExtentReportManager.logFailureDetails("transferOrderItems "+(j+1)+"'s assigneeId field is Failed");
                        ExtentReportManager.logInfoDetails("Expected assigneeId is "+assignPickerRequestPayload.get("assigneeId")+" and Actual assigneeId is "+transferOrderItems.get(j).get("assigneeId"));
                    }
                    Assert.assertEquals((String)transferOrderItems.get(j).get("assigneeId"), (String)assignPickerRequestPayload.get("assigneeId"));

                    break;
                }

                else if (j==transferOrderItems.size()-1 && !(tolineItemNumbers.get(i).equals((String)transferOrderItems.get(j).get("txfOrdItemNumber")))){
                    ExtentReportManager.logFailureDetails(tolineItemNumbers.get(i)+" is not assigned to Picker");
                    Assert.fail();
                }
            }
        }
    }

}
