package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class GetAllTOsSteps {

    @When("User calls the Get TO's endpoint to get list of TOs")
    public void userCallsTheGetTOSEndpointToGetListOfTOs() {
        ExtentReportManager.logInfoDetails("User calls the Get TO's endpoint to get list of TOs");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader()).log().all();
        Response response = requestSpecification.get(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "GET_TOs")).then().log().all().extract().response();
        ExtentReportManager.logInfoDetails("Get TOs Response Status Code is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Get TOs Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }
}
