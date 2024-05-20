package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import com.dmartLabs.pojo.AssignVehiclePOJO;
import com.dmartLabs.pojo.VehiclesPOJO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH;

public class AssignVehicleSteps {

    private static Object requestPayload;
    Map<String, Object> assignVehiclePayload;
    AssignVehiclePOJO assignVehicle;

    @And("Get delivery number to which vehicle is to be assigned")
    public void getDeliveryNumberToWhichVehicleIsToBeAssigned() {
        ExtentReportManager.logInfoDetails("Provide delivery number to which vehicle is to be assigned");
        assignVehiclePayload = new HashMap<>();
//        assignVehiclePayload.put("dlvNumber", CommonUtilities.getDeliveryNumber());
        assignVehiclePayload.put("dlvNumber", "9654560461");
    }

    @And("Provide dock vehicle type and vehicle number for a delivery to be assigned vehicle")
    public void provideDockVehicleTypeAndVehicleNumberForADeliveryToBeAssignedVehicle(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide dock vehicle type and vehicle number for a delivery to be assigned vehicle");
        Map<String, String> dockAndvehicleDetails = dataTable.asMap(String.class, String.class);
        assignVehiclePayload.put("dock", dockAndvehicleDetails.get("dock"));
        List<Map<String, String>> vehicles = new ArrayList<>();
        Map<String, String> vechicleDetail = new HashMap<>();
        vechicleDetail.put("vehicleType", dockAndvehicleDetails.get("vehicleType"));
        vechicleDetail.put("vehicleRegNum", dockAndvehicleDetails.get("vehicleRegNum"));
        vehicles.add(vechicleDetail);
        assignVehiclePayload.put("vehicles", vehicles);
        CommonUtilities.setMapRequestPayload(assignVehiclePayload);
        requestPayload = assignVehiclePayload;
    }

    @And("Provide delivery number, dock and vehicle details to assign vehicle for a delivery")
    public void provideDeliveryNumberDockAndVehicleDetailsToAssignVehicleForADelivery(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide delivery number, dock and vehicle details to assign vehicle for a delivery");
        Map<String, String> allDetails = dataTable.asMap(String.class, String.class);
        assignVehicle = new AssignVehiclePOJO();
        assignVehicle.setDlvNumber(allDetails.get("dlvNumber"));
        assignVehicle.setDock(allDetails.get("dock"));
        List<VehiclesPOJO> vehicles = new ArrayList<>();
        VehiclesPOJO vehicle = new VehiclesPOJO();
        vehicle.setVehicleType(allDetails.get("vehicleType"));
        vehicle.setVehicleRegNum(allDetails.get("vehicleRegNum"));
        vehicles.add(vehicle);
        assignVehicle.setVehicles(vehicles);
        requestPayload = assignVehicle;
    }

    @When("User calls the assign vehicle's endpoint to assign vehicle for a delivery")
    public void userCallsTheAssignVehicleSEndpointToAssignVehicleForADelivery() {
        ExtentReportManager.logInfoDetails("User calls the assign vehicle's endpoint to assign vehicle for a delivery");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(CommonUtilities.genericHeader(), requestPayload).log().all();
        Response response = requestSpecification.put(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "ASSIGN_VEHICLE")).then().log().all().extract().response();
        ExtentReportManager.logInfoDetails("Assign Vehicle Response Status Code is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Assign Vehicle Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Provide dock and get delivery number to which vehicle is to be assigned")
    public void provideDockAndGetDeliveryNumberToWhichVehicleIsToBeAssigned(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide dock and get delivery number to which vehicle is to be assigned");
        Map<String, String> dock = dataTable.asMap(String.class, String.class);
        assignVehiclePayload = new HashMap<>();
        assignVehiclePayload.put("dlvNumber", CommonUtilities.getDeliveryNumber());
//        assignVehiclePayload.put("dlvNumber", "922260913");
        assignVehiclePayload.putAll(dock);
//        requestPayload = assignVehiclePayload;
//        CommonUtilities.setMapRequestPayload(assignVehiclePayload);
    }


    @And("Provide vehicle type and vehicle number for a delivery to be assigned vehicle")
    public void provideVehicleTypeAndVehicleNumberForADeliveryToBeAssignedVehicle(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide vehicle type and vehicle number for a delivery to be assigned vehicle");
        List<Map<String, String>> vehicleDetails = dataTable.asMaps();
        assignVehiclePayload.put("vehicles", vehicleDetails);
        requestPayload=assignVehiclePayload;
        CommonUtilities.setMapRequestPayload(assignVehiclePayload);
    }
}
