package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExcelUtils;
import com.dmartLabs.commonutils.ExtentReportManager;
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
import org.testng.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GetDeliveriesSteps implements ConStants {

    private static GetDeliveriesSteps getDeliveriesSteps = new GetDeliveriesSteps();
    private static GenericSteps genericSteps = new GenericSteps();
    private static Map<String, String> queryParams;
    private static String expectedStatusCode;
    private Response response;

    //DataTable
    @And("Provide delivery number and truck type to get confirm delivery request")
    public void provideDeliveryNumberAndTruckTypeToGetConfirmDeliveryRequest(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide delivery number and truck type to get confirm delivery request");
        queryParams = dataTable.asMap(String.class, String.class);
    }

    //Scenario Outline
    @And("Provide delivery number {string} and truck type {string} to get confirm delivery request")
    public void provideDeliveryNumberAndTruckTypeToGetConfirmDeliveryRequest(String dlvNumber, String truckType) {
        ExtentReportManager.logInfoDetails("Provide delivery number {string} and truck type {string} to get confirm delivery request");
        queryParams = new HashMap<>();
        queryParams.put("dlvNumber", dlvNumber);
        queryParams.put("truckType", truckType);
    }

    //Excel
    @And("Provide fileName sheetName and rowNumber for delivery number and truck type to get confirm delivery request")
    public void provideRowNumberForDeliveryNumberAndTruckTypeToGetConfirmDeliveryRequest(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide fileName sheetName and rowNumber for delivery number and truck type to get confirm delivery request");
        Map<String, String> excelFileDetails = dataTable.asMap(String.class, String.class);
        ExcelUtils excelUtils = new ExcelUtils(excelFileDetails.get("fileName"));
        queryParams = excelUtils.getConfirmedDeliveryDetails(excelFileDetails.get("sheetName"), Integer.parseInt(excelFileDetails.get("rowNum")));
    }

    //ExcelDDT
    @And("Provide fileName and sheetName to all get confirm delivery request")
    public void provideFileNameAndSheetNameToAllGetConfirmDeliveryRequest(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide fileName and sheetName to all get confirm delivery request");
        Map<String, String> excelFileDetails = dataTable.asMap(String.class, String.class);
        expectedStatusCode = excelFileDetails.get("expectedStatusCode");
        ExcelUtils excelUtils = new ExcelUtils(excelFileDetails.get("fileName"));
        List<Map<String, String>> allQueryParam = excelUtils.getAllConfirmedDeliveryDetails(excelFileDetails.get("sheetName"));

        for (int i=0; i<allQueryParam.size(); i++) {
            queryParams = allQueryParam.get(i);
            if (i < allQueryParam.size() - 1) {
                getDeliveriesSteps.userCallsTheGetDeliveryByDlvNumberAndTruckTypeToGetTheConfirmedDelivery();
                genericSteps.verifyThatStatusCodeBeEqualTo(expectedStatusCode);
            }
        }

    }

    @And("Provide dlvNumber and truckType to get confirmed delivery request")
    public void provideDlvNumberAndTruckTypeToGetConfirmedDeliveryRequest() {
        ExtentReportManager.logInfoDetails("Provide dlvNumber and truckType to get confirmed delivery request");
        queryParams = new HashMap<>();
        queryParams.put("dlvNumber", CommonUtilities.getDeliveryNumber());
        queryParams.put("truckType", CommonUtilities.getTruckType());
    }

    @When("User calls the get delivery by dlvNumber and truckType to get the confirmed delivery")
    public void userCallsTheGetDeliveryByDlvNumberAndTruckTypeToGetTheConfirmedDelivery() {
        ExtentReportManager.logInfoDetails("User calls the get delivery by dlvNumber and truckType to get the confirmed delivery");
        RequestSpecification requestSpecification = RequestGenerator.getRequestWithQueryParam(CommonUtilities.genericHeader(), queryParams).log().all();
        response = requestSpecification.get(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "GET_DELIVERY_BY_DELIVERYNUMBER_TRUCKTYPE_ENDPOINT"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Get Delivery Response Status Code is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Get Delivery Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

}
