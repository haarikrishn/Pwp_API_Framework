package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class GetDispatchDeliverySteps {
    Response response;

    @When("User calls the dispatch delivery endpoint to get the list of dispatch delivery")
    public void userCallsTheDispatchDeliveryEndpointToGetTheListOfDispatchDelivery() {
        ExtentReportManager.logInfoDetails("User calls the dispatch delivery endpoint to get the list of dispatch delivery");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader()).log().all();
        response = requestSpecification.get(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "GET_DISPATCH_DELIVERY"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Get Dispatch Delivery Response Status Code is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Get Dispatch Delivery Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Verify that vehicle is assigned for a delivery")
    public void verifyThatVehicleIsAssignedForADelivery() {
//        ExtentReportManager.logInfoDetails("Verify that vehicle is assigned for a delivery");
//        Map<String, Object> assignVehicleRequestPayload = CommonUtilities.getMapRequestPayload();
//        response = CommonUtilities.getResponseInstance();
//
//        List<Map<String, Object>> responsePayload = response.as(new TypeRef<List<Map<String, Object>>>() {});
//
//        System.out.println("delivery Number is ==================> "+assignVehicleRequestPayload.get("dlvNumber"));;
//
//        for (int i=0; i<responsePayload.size(); i++){
//            Map<String, Object> dispatchDelivery = responsePayload.get(i);
//            if (dispatchDelivery.get("dlvNumber").equals(assignVehicleRequestPayload.get("dlvNumber"))){
//                List<Map<String, String>> responseVehicles = (List<Map<String, String>>) dispatchDelivery.get("vehicles");
//                List<Map<String, String>> requestVehicles = (List<Map<String, String>>) assignVehicleRequestPayload.get("vehicles");
//                ExtentReportManager.logPassDetails("Passed");
//                ExtentReportManager.logInfoDetails("Expected dlvNumber is "+assignVehicleRequestPayload.get("dlvNumber")+" and the Actual vehicleType is "+dispatchDelivery.get("dlvNumber"));
//
//                if (responseVehicles.get(0).get("vehicleType").equals(requestVehicles.get(0).get("vehicleType"))){
//                    ExtentReportManager.logPassDetails("Passed");
//                    ExtentReportManager.logInfoDetails("Expected vehicleType is "+requestVehicles.get(0).get("vehicleType")+" and the Actual vehicleType is "+responseVehicles.get(0).get("vehicleType"));
//                }
//                else {
//                    ExtentReportManager.logPassDetails("Failed");
//                    ExtentReportManager.logInfoDetails("Expected vehicleType is "+requestVehicles.get(0).get("vehicleType")+" and the Actual vehicleType is "+responseVehicles.get(0).get("vehicleType"));
//                }
//                Assert.assertEquals(responseVehicles.get(0).get("vehicleType"), requestVehicles.get(0).get("vehicleType"));
//
//                if (responseVehicles.get(0).get("vehicleRegNum").equals(requestVehicles.get(0).get("vehicleRegNum"))){
//                    ExtentReportManager.logPassDetails("Passed");
//                    ExtentReportManager.logInfoDetails("Expected vehicleRegNum is "+requestVehicles.get(0).get("vehicleRegNum")+" and the Actual vehicleRegNum is "+responseVehicles.get(0).get("vehicleRegNum"));
//                }
//                else {
//                    ExtentReportManager.logPassDetails("Failed");
//                    ExtentReportManager.logInfoDetails("Expected vehicleRegNum is "+requestVehicles.get(0).get("vehicleRegNum")+" and the Actual vehicleRegNum is "+responseVehicles.get(0).get("vehicleRegNum"));
//                }
//                Assert.assertEquals(responseVehicles.get(0).get("vehicleRegNum"), requestVehicles.get(0).get("vehicleRegNum"));
//            }
//            else if (i==responsePayload.size()-1 && !(dispatchDelivery.get("dlvNumber").equals((String)assignVehicleRequestPayload.get("dlvNumber")))){
//                ExtentReportManager.logFailureDetails("Failed");
//                ExtentReportManager.logInfoDetails("Vehicle is not Assigned to Delivery");
//                Assert.fail();
//            }
//        }
//

        ExtentReportManager.logInfoDetails("Verify that vehicle is assigned for a delivery");
        Map<String, Object> assignVehicleRequestPayload = CommonUtilities.getMapRequestPayload();
        response = CommonUtilities.getResponseInstance();

//        response.jsonPath().get

        List<Map<String, Object>> responsePayload = response.as(new TypeRef<List<Map<String, Object>>>() {});

        for (int i=0; i<responsePayload.size(); i++){
            Map<String, Object> dispatchDelivery = responsePayload.get(i);
            if (dispatchDelivery.get("dlvNumber").equals((String)assignVehicleRequestPayload.get("dlvNumber"))){
                ExtentReportManager.logPassDetails("Pass");
                ExtentReportManager.logInfoDetails("Expected dlvNumber is "+assignVehicleRequestPayload.get("dlvNumber")+" and the Actual dlvNumber is "+dispatchDelivery.get("dlvNumber"));


                List<Map<String, String>> responseVehicles = (List<Map<String, String>>) dispatchDelivery.get("vehicles");
                List<Map<String, String>> requestVehicles = (List<Map<String, String>>) assignVehicleRequestPayload.get("vehicles");
                if (responseVehicles.get(0).get("vehicleType").equals(requestVehicles.get(0).get("vehicleType"))){
                    ExtentReportManager.logPassDetails("Passed");
                    ExtentReportManager.logInfoDetails("Expected vehicleType is "+requestVehicles.get(0).get("vehicleType")+" and the Actual vehicleType is "+responseVehicles.get(0).get("vehicleType"));
                }
                else {
                    ExtentReportManager.logPassDetails("Failed");
                    ExtentReportManager.logInfoDetails("Expected vehicleType is "+requestVehicles.get(0).get("vehicleType")+" and the Actual vehicleType is "+responseVehicles.get(0).get("vehicleType"));
                }
                Assert.assertEquals(responseVehicles.get(0).get("vehicleType"), requestVehicles.get(0).get("vehicleType"));

                if (responseVehicles.get(0).get("vehicleRegNum").equals(requestVehicles.get(0).get("vehicleRegNum"))){
                    ExtentReportManager.logPassDetails("Passed");
                    ExtentReportManager.logInfoDetails("Expected vehicleRegNum is "+requestVehicles.get(0).get("vehicleRegNum")+" and the Actual vehicleRegNum is "+responseVehicles.get(0).get("vehicleRegNum"));
                }
                else {
                    ExtentReportManager.logPassDetails("Failed");
                    ExtentReportManager.logInfoDetails("Expected vehicleRegNum is "+requestVehicles.get(0).get("vehicleRegNum")+" and the Actual vehicleRegNum is "+responseVehicles.get(0).get("vehicleRegNum"));
                }
                Assert.assertEquals(responseVehicles.get(0).get("vehicleRegNum"), requestVehicles.get(0).get("vehicleRegNum"));
                break;
            }
            else if (i==responsePayload.size()-1 && !(dispatchDelivery.get("dlvNumber").equals((String)assignVehicleRequestPayload.get("dlvNumber")))){
                ExtentReportManager.logFailureDetails("Fail");
                ExtentReportManager.logInfoDetails("Expected dlvNumber is "+assignVehicleRequestPayload.get("dlvNumber")+" and the Actual dlvNumber is "+dispatchDelivery.get("dlvNumber"));
                Assert.fail();
            }

        }

    }

    @And("Verify that vehicle is not assigned for a delivery")
    public void verifyThatVehicleIsNotAssignedForADelivery() {
        ExtentReportManager.logInfoDetails("Verify that vehicle is not assigned for a delivery");
        Map<String, Object> assignVehicleRequestPayload = CommonUtilities.getMapRequestPayload();
        response = CommonUtilities.getResponseInstance();

        List<Map<String, Object>> responsePayload = response.as(new TypeRef<List<Map<String, Object>>>() {});

        for (int i=0; i<responsePayload.size(); i++){
            Map<String, Object> dispatchDelivery = responsePayload.get(i);
            if (dispatchDelivery.get("dlvNumber").equals((String)assignVehicleRequestPayload.get("dlvNumber"))){
                ExtentReportManager.logFailureDetails("Failed");
                ExtentReportManager.logInfoDetails("Vehicle is assigned to a delivery");
                Assert.fail();
            }
            else if (i==responsePayload.size()-1 && !(dispatchDelivery.get("dlvNumber").equals((String)assignVehicleRequestPayload.get("dlvNumber")))){
                ExtentReportManager.logPassDetails("Pass");
                ExtentReportManager.logInfoDetails("Vehicle is not Assigned to Delivery");
                Assert.assertEquals(response.getStatusCode(),200);
            }
        }

    }
}
