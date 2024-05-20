package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExcelUtils;
import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.JSONUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import com.dmartLabs.pojo.ConfirmDeliveryPOJO;
import com.dmartLabs.pojo.DeliveryItemsPOJO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class ConfirmDeliverySteps implements ConStants {

    private static ConfirmDeliveryPOJO confirmDelivery;
    private RequestGenerator requestGenerator = new RequestGenerator();
    private static ConfirmDeliverySteps confirmDeliverySteps = new ConfirmDeliverySteps();
    private static GenericSteps genericSteps = new GenericSteps();
    private static LinkedHashMap<String, Object> deliveryItems = new LinkedHashMap<>();
    private static GetDeliveriesSteps getDeliveriesSteps = new GetDeliveriesSteps();
    static Object requestPayload;
//    static List<Object> reqPayloads = new ArrayList<>();
    Response response;

    @And("Assign truckType and dock for new Delivery Request")
    public void assignTruckTypeAndDockForNewDeliveryRequest(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Assign truckType and dock for new Delivery Request");
        Map<String, String> delNumberTruckTypeDockDetails = dataTable.asMap(String.class, String.class);
        confirmDelivery = new ConfirmDeliveryPOJO();
        confirmDelivery.setDlvNumber(CommonUtilities.getDeliveryNumber());
//        confirmDelivery.setDlvNumber(delNumberTruckTypeDockDetails.get("dlvNumber")); //change it later
//        confirmDelivery.setDlvNumber(delNumberTruckTypeDockDetails.get("dlvNumber"));
        confirmDelivery.setTruckType(delNumberTruckTypeDockDetails.get("truckType"));
        //new change
        CommonUtilities.setTruckType(delNumberTruckTypeDockDetails.get("truckType"));
        confirmDelivery.setDock(delNumberTruckTypeDockDetails.get("dock"));
    }

    @And("Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request")
    public void provideDlvItemNumPlannedDlvBoxQtyAndMinimumBoxesForTheItemsInNewDeliveryRequest(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide dlvItemNum plannedDlvBoxQty and minimumBoxes for the Items in new Delivery Request");
        List<Map<String, String>> deliveryItemsDetailsList = dataTable.asMaps();

        List<DeliveryItemsPOJO> deliveryItems = new ArrayList<>();

        for (int i = 0; i<deliveryItemsDetailsList.size(); i++){
            Map<String, String> deliveryItemsDetail = deliveryItemsDetailsList.get(i);
            DeliveryItemsPOJO deliveryItem = new DeliveryItemsPOJO();
            deliveryItem.setDlvItemNum(CommonUtilities.getDeliveryNumber()+deliveryItemsDetail.get("itemSeq"));
            deliveryItem.setPlannedDlvBoxQty(deliveryItemsDetail.get("plannedDlvBoxQty"));
            deliveryItem.setMinimumBoxes(deliveryItemsDetail.get("minimumBoxes"));
            deliveryItems.add(deliveryItem);
        }
        confirmDelivery.setDlvItems(deliveryItems);
        requestPayload=confirmDelivery;
        CommonUtilities.setConfirmDeliveryPOJO(confirmDelivery);
    }

    @And("Send the request payload for new Delivery Request {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void sendTheRequestPayloadForNewDeliveryRequest(String dlvNumber, String truckType, String dock, String dlvItemNum1, String plannedDlvBoxQty1, String minimumBoxes1, String dlvItemNum2, String plannedDlvBoxQty2, String minimumBoxes2, String dlvItemNum3, String plannedDlvBoxQty3, String minimumBoxes3, String dlvItemNum4, String plannedDlvBoxQty4, String minimumBoxes4, String dlvItemNum5, String plannedDlvBoxQty5, String minimumBoxes5, String dlvItemNum6, String plannedDlvBoxQty6, String minimumBoxes6, String dlvItemNum7, String plannedDlvBoxQty7, String minimumBoxes7) {
        ExtentReportManager.logInfoDetails("Send the request payload for new Delivery Request");
        confirmDelivery = new ConfirmDeliveryPOJO();
        confirmDelivery.setTruckType(truckType);
        confirmDelivery.setDock(dock);
        confirmDelivery.setDlvNumber(dlvNumber); //change it later

        List<DeliveryItemsPOJO> deliveryItems = new ArrayList<>();
        if (!dlvItemNum1.equals("")) {
            DeliveryItemsPOJO deliveryItem = new DeliveryItemsPOJO();
            deliveryItem.setDlvItemNum(dlvItemNum1);
            deliveryItem.setPlannedDlvBoxQty(plannedDlvBoxQty1);
            deliveryItem.setMinimumBoxes(minimumBoxes1);
            deliveryItems.add(deliveryItem);

            if (!dlvItemNum2.equals("")) {
                deliveryItem = new DeliveryItemsPOJO();
                deliveryItem.setDlvItemNum(dlvItemNum2);
                deliveryItem.setPlannedDlvBoxQty(plannedDlvBoxQty2);
                deliveryItem.setMinimumBoxes(minimumBoxes2);
                deliveryItems.add(deliveryItem);

                if (!dlvItemNum3.equals("")) {
                    deliveryItem = new DeliveryItemsPOJO();
                    deliveryItem.setDlvItemNum(dlvItemNum3);
                    deliveryItem.setPlannedDlvBoxQty(plannedDlvBoxQty3);
                    deliveryItem.setMinimumBoxes(minimumBoxes3);
                    deliveryItems.add(deliveryItem);

                    if (!dlvItemNum4.equals("")) {
                        deliveryItem = new DeliveryItemsPOJO();
                        deliveryItem.setDlvItemNum(dlvItemNum4);
                        deliveryItem.setPlannedDlvBoxQty(plannedDlvBoxQty4);
                        deliveryItem.setMinimumBoxes(minimumBoxes4);
                        deliveryItems.add(deliveryItem);

                        if (!dlvItemNum5.equals("")) {
                            deliveryItem = new DeliveryItemsPOJO();
                            deliveryItem.setDlvItemNum(dlvItemNum5);
                            deliveryItem.setPlannedDlvBoxQty(plannedDlvBoxQty5);
                            deliveryItem.setMinimumBoxes(minimumBoxes5);
                            deliveryItems.add(deliveryItem);

                            if (!dlvItemNum6.equals("")) {
                                deliveryItem = new DeliveryItemsPOJO();
                                deliveryItem.setDlvItemNum(dlvItemNum6);
                                deliveryItem.setPlannedDlvBoxQty(plannedDlvBoxQty6);
                                deliveryItem.setMinimumBoxes(minimumBoxes6);
                                deliveryItems.add(deliveryItem);

                                if (!dlvItemNum7.equals("")) {
                                    deliveryItem = new DeliveryItemsPOJO();
                                    deliveryItem.setDlvItemNum(dlvItemNum7);
                                    deliveryItem.setPlannedDlvBoxQty(plannedDlvBoxQty7);
                                    deliveryItem.setMinimumBoxes(minimumBoxes7);
                                    deliveryItems.add(deliveryItem);
                                }
                            }
                        }
                    }
                }
            }
        }
        confirmDelivery.setDlvItems(deliveryItems);
        CommonUtilities.setConfirmDeliveryPOJO(confirmDelivery);
        requestPayload = confirmDelivery;
    }

    @And("Get new delivery request items from Excel File")
    public void getNewDeliveryRequestItemsFromExcelFile(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Get new delivery request items from Excel File");
        Map<String, String> excelFileDetails = dataTable.asMap(String.class, String.class);
        String fileName = excelFileDetails.get("fileName");
        String sheetName = excelFileDetails.get("sheetName");
        int rowNum = Integer.parseInt(excelFileDetails.get("rowNum"));
        ExcelUtils excelUtils = new ExcelUtils(fileName);

        deliveryItems = excelUtils.getConfirmDeliveryRequestPayload(sheetName, rowNum);

        if (deliveryItems.containsKey("dlvNumber")){
            deliveryItems.put("dlvNumber", CommonUtilities.getDeliveryNumber());
        }

        CommonUtilities.setTruckType((String)deliveryItems.get("truckType"));

        if (deliveryItems.containsKey("dlvItems") && deliveryItems.get("dlvItems")!=null && !deliveryItems.get("dlvItems").equals("")){
            for (Map<String, String> dlvItem: (List<Map<String, String>>)deliveryItems.get("dlvItems")) {
                if (dlvItem.containsKey("dlvItemNum")){
                    String itemSeq = dlvItem.get("dlvItemNum");
                    if (dlvItem.get("dlvItemNum")!=null && !(dlvItem.get("dlvItemNum").equals(""))) {
                        dlvItem.put("dlvItemNum", CommonUtilities.getDeliveryNumber() + itemSeq);
                    }
                }
            }
        }

        requestPayload = deliveryItems;
    }

    @And("Get all Delivery Request items from Excel File")
    public void getAllDeliveryRequestItemsFromExcelFile(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Get all Delivery Request items from Excel File");
        Map<String, String> excelFileDetails = dataTable.asMap(String.class, String.class);
        String fileName = excelFileDetails.get("fileName");
        String sheetName = excelFileDetails.get("sheetName");
        String expectedStatusCode = excelFileDetails.get("expectedStatusCode");
        ExcelUtils excelUtils = new ExcelUtils(fileName);
        List<LinkedHashMap<String, Object>> allRequestPayload = excelUtils.getConfirmDeliveryAllRequestPayload(sheetName);

        for (int i=0;i<allRequestPayload.size();i++){

            deliveryItems = allRequestPayload.get(i);

            if (deliveryItems.containsKey("dlvNumber")) {
                deliveryItems.put("dlvNumber", CommonUtilities.getDeliveryNumber());
            }
            CommonUtilities.setTruckType((String)deliveryItems.get("truckType"));

            if (deliveryItems.containsKey("dlvItems") && deliveryItems.get("dlvItems")!=null && !deliveryItems.get("dlvItems").equals("")){
                for (Map<String, String> dlvItem: (List<Map<String, String>>)deliveryItems.get("dlvItems")) {
                    if (dlvItem.containsKey("dlvItemNum")){
                        String itemSeq = dlvItem.get("dlvItemNum");
                        if (dlvItem.get("dlvItemNum")!=null && !(dlvItem.get("dlvItemNum").equals(""))) {
                            dlvItem.put("dlvItemNum", CommonUtilities.getDeliveryNumber() + itemSeq);
                        }
                    }
                }
            }

            CommonUtilities.setDeliveryNumber((String)deliveryItems.get("dlvNumber"));

            requestPayload = deliveryItems;

            if (i< allRequestPayload.size()-1 && !(expectedStatusCode.equals("400"))){
//                deliveryItems = allRequestPayload.get(i);
                confirmDeliverySteps.userCallsTheConfirmDeliverySEndpointToConfirmTheDeliveryRequest();
                genericSteps.verifyThatStatusCodeBeEqualTo("expectedStatusCode");
                getDeliveriesSteps.provideDlvNumberAndTruckTypeToGetConfirmedDeliveryRequest();
                getDeliveriesSteps.userCallsTheGetDeliveryByDlvNumberAndTruckTypeToGetTheConfirmedDelivery();
                genericSteps.verifyThatStatusCodeBeEqualTo("expectedStatusCode");
                confirmDeliverySteps.verifyThatNewDeliveryRequestIsConfirmedSuccessfully();
            }
            else if (i< allRequestPayload.size()-1 && expectedStatusCode.equals("400")){
                confirmDeliverySteps.userCallsTheConfirmDeliverySEndpointToConfirmTheDeliveryRequest();
                genericSteps.verifyThatStatusCodeBeEqualTo(expectedStatusCode);
            }
        }

    }

    @And("Get Confirm Delivery request payload from JSON File")
    public void getNewDeliveryRequestItemsFromJSONFile(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Get Confirm Delivery request payload from JSON File");
        Map<String, String> jsonFileName = dataTable.asMap(String.class, String.class);
        JSONObject confirmDelivery = JSONUtils.getRequestPayloadAsObjectFromJsonFile(jsonFileName.get("fileName"));
        confirmDelivery.put("dlvNumber", CommonUtilities.getDeliveryNumber());
        requestPayload = confirmDelivery.toString();
    }

    @And("Get Confirm Delivery request payload from JSON File {string}")
    public void getConfirmDeliveryRequestPayloadFromJSONFile(String jsonFileName) {
        ExtentReportManager.logInfoDetails("Get Confirm Delivery request payload from JSON File {string}");
        JSONObject confirmDelivery = JSONUtils.getRequestPayloadAsObjectFromJsonFile(jsonFileName);
        confirmDelivery.put("dlvNumber", CommonUtilities.getDeliveryNumber());
        requestPayload = confirmDelivery.toString();
    }

    @When("User calls the Confirm Delivery's endpoint to confirm the Delivery Request")
    public void userCallsTheConfirmDeliverySEndpointToConfirmTheDeliveryRequest() {
        ExtentReportManager.logInfoDetails("User calls the Confirm Delivery's endpoint to confirm the Delivery Request");
        RequestSpecification requestSpecification = requestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "CONFIRM_DELIVERY_ENDPOINT")).then().log().all().extract().response();
        ExtentReportManager.logInfoDetails("Confirm Delivery Response Status Code is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setConfirmDeliveryResponse(response);
    }

    @Then("Verify that new delivery is confirmed successfully")
    public void verifyThatNewDeliveryIsConfirmedSuccessfully() {

        ExtentReportManager.logInfoDetails("Verify that new delivery request is confirmed successfully");

        ConfirmDeliveryPOJO threadSafeConfirmDeliveryPojo = CommonUtilities.getConfirmDeliveryPOJO();
        Response threadSafeGetDeliveryResponse = CommonUtilities.getResponseInstance();


        if (threadSafeConfirmDeliveryPojo.getDlvNumber().equals(threadSafeGetDeliveryResponse.jsonPath().getString("dlvNumber"))){
            ExtentReportManager.logPassDetails("dlvNumber field is Passed");
            ExtentReportManager.logInfoDetails("Expected dlvNumber is " +threadSafeConfirmDeliveryPojo.getDlvNumber() + " and the Actual dlvNumber is " +threadSafeGetDeliveryResponse.jsonPath().getString("dlvNumber"));
        }
        else {
            ExtentReportManager.logFailureDetails("dlvNumber field is Failed");
            ExtentReportManager.logInfoDetails("Expected dlvNumber is " +threadSafeConfirmDeliveryPojo.getDlvNumber() + " and the Actual dlvNumber is " +threadSafeGetDeliveryResponse.jsonPath().getString("dlvNumber"));
        }
        Assert.assertEquals(threadSafeGetDeliveryResponse.jsonPath().getString("dlvNumber"), threadSafeConfirmDeliveryPojo.getDlvNumber());

        if (threadSafeGetDeliveryResponse.jsonPath().getString("deliveryStatus").equals("CONFIRMED")){
            ExtentReportManager.logPassDetails("Status is Passed");
            ExtentReportManager.logInfoDetails("New Delivery Request is CONFIRMED");
        }
        else{
            ExtentReportManager.logFailureDetails("Status is Failed");
            ExtentReportManager.logInfoDetails("New Delivery Request is not CONFIRMED");
        }
        Assert.assertEquals(threadSafeGetDeliveryResponse.jsonPath().getString("deliveryStatus"), "CONFIRMED");

        List<Map<String,Object>> actualVehiclesDetails = threadSafeGetDeliveryResponse.jsonPath().get("vehicles");
        CommonUtilities.validateTruckType(actualVehiclesDetails, threadSafeConfirmDeliveryPojo.getTruckType());

        if (threadSafeConfirmDeliveryPojo.getDock().equals(threadSafeGetDeliveryResponse.jsonPath().getString("dock"))){
            ExtentReportManager.logPassDetails("dock field is Passed");
            ExtentReportManager.logInfoDetails("Expected dock is " +threadSafeConfirmDeliveryPojo.getDock() + " and the Actual dock is " +threadSafeGetDeliveryResponse.jsonPath().getString("dock"));
        }
        else {
            ExtentReportManager.logFailureDetails("dock field is Failed");
            ExtentReportManager.logInfoDetails("Expected dock is " +threadSafeConfirmDeliveryPojo.getDock() + " and the Actual dock is " +threadSafeGetDeliveryResponse.jsonPath().getString("dock"));
        }
        Assert.assertEquals(threadSafeGetDeliveryResponse.jsonPath().getString("dock"), threadSafeConfirmDeliveryPojo.getDock());

        List<DeliveryItemsPOJO> expectedDlvItems = threadSafeConfirmDeliveryPojo.getDlvItems();
        List<Map<String, String>> actualDlvItems = threadSafeGetDeliveryResponse.jsonPath().get("dlvItems");
        CommonUtilities.validateDlvItems(actualDlvItems, expectedDlvItems);
    }

    @And("Verify that new delivery request is confirmed successfully")
    public void verifyThatNewDeliveryRequestIsConfirmedSuccessfully() {

        ExtentReportManager.logInfoDetails("Verify that new delivery request is confirmed successfully");

        Response threadSafeGetDeliveryResponse = CommonUtilities.getResponseInstance();

        if (deliveryItems.get("dlvNumber").equals(threadSafeGetDeliveryResponse.jsonPath().getString("dlvNumber"))){
            ExtentReportManager.logPassDetails("dlvNumber field is Passed");
            ExtentReportManager.logInfoDetails("Expected dlvNumber is " +deliveryItems.get("dlvNumber") + " and the Actual dlvNumber is " +threadSafeGetDeliveryResponse.jsonPath().getString("dlvNumber"));
        }
        else {
            ExtentReportManager.logFailureDetails("dlvNumber field is Failed");
            ExtentReportManager.logInfoDetails("Expected dlvNumber is " +deliveryItems.get("dlvNumber") + " and the Actual dlvNumber is " +threadSafeGetDeliveryResponse.jsonPath().getString("dlvNumber"));
        }
        Assert.assertEquals(threadSafeGetDeliveryResponse.jsonPath().getString("dlvNumber"),deliveryItems.get("dlvNumber"));

        if (threadSafeGetDeliveryResponse.jsonPath().getString("deliveryStatus").equals("CONFIRMED")){
            ExtentReportManager.logPassDetails("Status is Passed");
            ExtentReportManager.logInfoDetails("New Delivery Request is CONFIRMED");
        }
        else{
            ExtentReportManager.logFailureDetails("Status is Failed");
            ExtentReportManager.logInfoDetails("New Delivery Request is not CONFIRMED");
        }
        Assert.assertEquals(threadSafeGetDeliveryResponse.jsonPath().getString("deliveryStatus"), "CONFIRMED");

        List<Map<String,Object>> actualVehiclesDetails = threadSafeGetDeliveryResponse.jsonPath().get("vehicles");
        CommonUtilities.validateTruckType(actualVehiclesDetails, (String) deliveryItems.get("truckType"));

        if (deliveryItems.get("dock").equals(threadSafeGetDeliveryResponse.jsonPath().getString("dock"))){
            ExtentReportManager.logPassDetails("dock field is Passed");
            ExtentReportManager.logInfoDetails("Expected dock is " +deliveryItems.get("dock") + " and the Actual dock is " +threadSafeGetDeliveryResponse.jsonPath().getString("dock"));
        }
        else {
            ExtentReportManager.logFailureDetails("dock field is Failed");
            ExtentReportManager.logInfoDetails("Expected dock is " +deliveryItems.get("dock") + " and the Actual dock is " +threadSafeGetDeliveryResponse.jsonPath().getString("dock"));
        }
        Assert.assertEquals(threadSafeGetDeliveryResponse.jsonPath().getString("dock"), deliveryItems.get("dock"));

        List<Map<String, String>> expectedDlvItems = (List<Map<String, String>>)deliveryItems.get("dlvItems");
        List<Map<String, String>> actualDlvItems = threadSafeGetDeliveryResponse.jsonPath().get("dlvItems");
        CommonUtilities.validateDlvItemsUsingListMap(actualDlvItems, expectedDlvItems);

    }

    @Then("Verify that response has status as {int}")
    public void verifyThatResponseHasStatusAs(int expectedStatus) {
        ExtentReportManager.logInfoDetails("Verify that response has status as {int}");
        int actualStatusCode = CommonUtilities.getResponseInstance().jsonPath().getInt("status");
        Assert.assertEquals(actualStatusCode, expectedStatus);
    }

    @Then("Verify that response has status as {string}")
    public void verifyThatResponseHasStatusAs(String expectedResponse) {
        Assert.assertTrue(response.jsonPath().getString("message").contains(expectedResponse));
    }
}
