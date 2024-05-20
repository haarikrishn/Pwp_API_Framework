package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.PropertyReader;
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
import static com.dmartLabs.config.ConStants.TIME_FORMAT_PROPERTIES_PATH;

public class AssignPickerSteps {

    private static Map<String, Object> assignPickerRequestPayload;
    private Map<String, String> header;
    private Response response;

    @And("Provide delivery details and assigneeId using DataTable to assign delivery to picker")
    public void provideDeliveryDetailsAndAssigneeIdToAssignDeliveryToPicker(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide delivery details and assigneeId to assign delivery to picker");
        header = CommonUtilities.genericHeader();
        assignPickerRequestPayload = new HashMap<>();
        assignPickerRequestPayload.putAll(dataTable.asMap(String.class, String.class));
        assignPickerRequestPayload.put("assignedOn", GenricUtils.getFormattedDateTime(PropertyReader.getProperty(TIME_FORMAT_PROPERTIES_PATH, "DATE_MONTH_YEAR_TIME_FORMAT")));
        CommonUtilities.setDeliveryNumber((String) assignPickerRequestPayload.get("dlvNumber"));
    }

    @And("Provide tolineItemNumbers to assign Items to Picker")
    public void provideTolineItemNumbersToAssignItemsToPicker(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide delivery details and assigneeId using DataTable to assign delivery to picker");
        List<String> tolineItemNumbers = dataTable.asList();
        assignPickerRequestPayload.put("tolineItemNumbers", tolineItemNumbers);
        CommonUtilities.setMapRequestPayload(assignPickerRequestPayload);
    }

    @And("Get delivery details and assigneeId to assign delivery to picker")
    public void getDeliveryDetailsAndAssigneeIdToAssignDeliveryToPicker() {
        ExtentReportManager.logInfoDetails("Get delivery details and assigneeId to assign delivery to picker");
        header = CommonUtilities.genericHeader();
        assignPickerRequestPayload = new HashMap<>();

        String txfOrdNumber = CommonUtilities.gettxfOrdNumber();
        String whNumber = CommonUtilities.getWhNumber();
        List<String> txfOrdItemSeqs = CommonUtilities.getTxfOrdItemSeq();

        List<String> tolineItemNumbers = new ArrayList<>();
        for (String txfOrdItemSeq:txfOrdItemSeqs){
            tolineItemNumbers.add(txfOrdNumber+whNumber+txfOrdItemSeq);
        }

        assignPickerRequestPayload.put("siteId", CommonUtilities.getDstSiteId());
        assignPickerRequestPayload.put("toNumber", txfOrdNumber);
        assignPickerRequestPayload.put("dlvNumber", CommonUtilities.getDeliveryNumber());
        assignPickerRequestPayload.put("tolineItemNumbers", tolineItemNumbers);
//        assignPickerRequestPayload.put("assigneeId", CommonUtilities.getClientId());
        assignPickerRequestPayload.put("assigneeId", "659fd2585c2fd619b4032812");
//        assignPickerRequestPayload.put("assignedOn", GenricUtils.getFormattedDateTime(PropertyReader.getProperty(TIME_FORMAT_PROPERTIES_PATH,"DATE_MONTH_YEAR_TIME_FORMAT")));
        CommonUtilities.setDeliveryNumber((String) assignPickerRequestPayload.get("dlvNumber"));
    }

    @And("Get delivery details and provide assigneeId to assign delivery to picker")
    public void getDeliveryDetailsAndProvideAssigneeIdToAssignDeliveryToPicker(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Get delivery details and provide assigneeId to assign delivery to picker");

        Map<String, String> assigneeId = dataTable.asMap(String.class, String.class);
        header = CommonUtilities.genericHeader();
        assignPickerRequestPayload = new HashMap<>();

        String txfOrdNumber = CommonUtilities.gettxfOrdNumber();
        String whNumber = CommonUtilities.getWhNumber();
        List<String> txfOrdItemSeqs = CommonUtilities.getTxfOrdItemSeq();

        List<String> tolineItemNumbers = new ArrayList<>();
        for (String txfOrdItemSeq:txfOrdItemSeqs){
            tolineItemNumbers.add(txfOrdNumber+whNumber+txfOrdItemSeq);
        }

        assignPickerRequestPayload.put("siteId", CommonUtilities.getDstSiteId());
        assignPickerRequestPayload.put("toNumber", txfOrdNumber);
        assignPickerRequestPayload.put("dlvNumber", CommonUtilities.getDeliveryNumber());
        assignPickerRequestPayload.put("tolineItemNumbers", tolineItemNumbers);
//        assignPickerRequestPayload.put("assigneeId", CommonUtilities.getClientId());
        assignPickerRequestPayload.putAll(assigneeId);
//        assignPickerRequestPayload.put("assignedOn", GenricUtils.getFormattedDateTime(PropertyReader.getProperty(TIME_FORMAT_PROPERTIES_PATH,"DATE_MONTH_YEAR_TIME_FORMAT")));
        CommonUtilities.setDeliveryNumber((String) assignPickerRequestPayload.get("dlvNumber"));
    }

    @And("Provide huMaterial and destSiteId for a delivery to be assigned")
    public void provideHuMaterialAndDestSiteIdForADeliveryToBeAssigned(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide huMaterial and destSiteId for a delivery to be assigned");
        Map<String, String> humaterial = dataTable.asMap(String.class, String.class);
                assignPickerRequestPayload.putAll(humaterial);
        CommonUtilities.setMapRequestPayload(assignPickerRequestPayload);
    }

    @And("Provide delivery details to assign delivery to picker {string} {string} {string} {string} {string} {string} {string} {string} {string} {string}")
    public void provideDeliveryDetailsToAssignDeliveryToPicker(String siteId, String toNumber, String dlvNumber, String assigneeId, String tolineItemNumber1, String tolineItemNumber2, String tolineItemNumber3, String tolineItemNumber4, String tolineItemNumber5, String huMaterial) {
        ExtentReportManager.logInfoDetails("Provide delivery details to assign delivery to picker");
        header = CommonUtilities.genericHeader();
        List<String> tolineItemNumbers = new ArrayList<>();
        if (dlvNumber.equals("null")){
            dlvNumber=null;
        }
        else if (tolineItemNumber2.equals("null")){
            tolineItemNumber2 = null;
        }
        else if (assigneeId.equals("null")){
            assigneeId = null;
        }
        else if (huMaterial.equals("null")){
            huMaterial = null;
        }

        tolineItemNumbers.add(tolineItemNumber1);
        tolineItemNumbers.add(tolineItemNumber2);
        tolineItemNumbers.add(tolineItemNumber3);
        tolineItemNumbers.add(tolineItemNumber4);
        tolineItemNumbers.add(tolineItemNumber5);

        if (tolineItemNumber1.equals("null")){
            tolineItemNumbers = null;
        }

        assignPickerRequestPayload = new HashMap<>();
        assignPickerRequestPayload.put("siteId", siteId);
        assignPickerRequestPayload.put("toNumber", toNumber);
        assignPickerRequestPayload.put("dlvNumber", dlvNumber);
        assignPickerRequestPayload.put("tolineItemNumber", tolineItemNumbers);
        assignPickerRequestPayload.put("assigneeId", CommonUtilities.getClientId());
        assignPickerRequestPayload.put("assignedOn", GenricUtils.getFormattedDateTime(PropertyReader.getProperty(TIME_FORMAT_PROPERTIES_PATH,"DATE_MONTH_YEAR_TIME_FORMAT")));
        assignPickerRequestPayload.put("huMaterial",huMaterial);
        CommonUtilities.setDeliveryNumber((String) assignPickerRequestPayload.get("dlvNumber"));
    }

    @When("User calls the Assign Picker endpoint to assign delivery to Picker")
    public void userCallsTheAssignPickerEndpointToAssignDeliveryToPicker() {
        ExtentReportManager.logInfoDetails("User calls the Assign Picker endpoint to assign delivery to Picker");
        RequestSpecification requestSpecification = RequestGenerator.getRequest(header, assignPickerRequestPayload).log().all();
        response = requestSpecification.post(PropertyReader.getProperty(ENDPOINTS_PATHS_PROPERTIES_PATH, "ASSIGN_PICKER"));
        response.then().log().all();
        ExtentReportManager.logInfoDetails("Assign Picker Response Status Code is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Assign Picker Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Assign Picker Response Time is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

}
