package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GetPicker {

    Response response;
    Map<String, String> queryParam;
    Map<String, String> header;

    @And("Provide dcId to get the checked-in Picker's details")
    public void provideDcIdToGetTheCheckedInPickerSDetails(DataTable dataTable) {
        ExtentReportManager.logInfoDetails("Provide dcId to get the checked-in Picker's details");
        header = CommonUtilities.genericHeader();
        queryParam = dataTable.asMap(String.class, String.class);
    }

    @And("Get dcId to get the checked-in Picker's details")
    public void getDcIdToGetTheCheckedInPickerSDetails() {
        ExtentReportManager.logInfoDetails("Get dcId to get the checked-in Picker's details");
        header = CommonUtilities.genericHeader();
        queryParam = new HashMap<>();
        queryParam.put("siteId", CommonUtilities.getSiteId());
    }

    @When("User calls the Get Picker endpoint to get checked-in Pickers")
    public void userCallsTheGetPickerEndpointToGetCheckedInPickers() {
        ExtentReportManager.logInfoDetails("User calls the Get Picker endpoint to get checked-in Pickers");
        RequestSpecification requestSpecification = RequestGenerator.getRequestWithQueryParam(header, queryParam);
        response = requestSpecification.get(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH,"GET_PICKER")).then().log().all().extract().response();
        ExtentReportManager.logInfoDetails("Get Picker Response Status Code is : "+response.getStatusCode());
        ExtentReportManager.logInfoDetails("Get Picker Response Payload is - ");
        ExtentReportManager.logJson(response.prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time is : "+response.getTimeIn(TimeUnit.MILLISECONDS)+" ms");
        CommonUtilities.setResponseInstance(response);
    }

    @And("Verify that delivery is assigned to picker")
    public void verifyThatDeliveryIsAssignedToPicker() {
        ExtentReportManager.logInfoDetails("Verify that delivery is assigned to picker");
        response = CommonUtilities.getResponseInstance();
        Map<String, Object> assignPickerRequestPayload = CommonUtilities.getMapRequestPayload();
        List<Map<String, Object>> responseList = response.as(new TypeRef<List<Map<String, Object>>>() {});

        for (int i=0; i<responseList.size(); i++){

            if (responseList.get(i).containsKey("onBreak"))
                continue;

            if (((String)responseList.get(i).get("assigneeId")).equals((String) assignPickerRequestPayload.get("assigneeId"))){
                ExtentReportManager.logPassDetails("assigneeId field is passed");
                ExtentReportManager.logInfoDetails("Expected assigneeId is "+assignPickerRequestPayload.get("assigneeId")+" and the Actual assigneeId is "+responseList.get(i).get("assigneeId"));

                Map<String, Object> response = responseList.get(i);

                if (((String)response.get("attendeeStatus")).equals("Online")){
                    ExtentReportManager.logPassDetails("attendeeStatus field is Passed");
                    ExtentReportManager.logInfoDetails("Expected attendeeStatus is Online and the Actual attendeeStatus is "+response.get("attendeeStatus"));
                }
                else {
                    ExtentReportManager.logFailureDetails("attendeeStatus field is Failed");
                    ExtentReportManager.logInfoDetails("Expected attendeeStatus is Online and the Actual attendeeStatus is "+response.get("attendeeStatus"));
                }
                Assert.assertEquals((String)response.get("attendeeStatus"), "Online");

                List<Map<String, Object>> tos = (List<Map<String, Object>>) response.get("tos");
                for (int j=0; j<tos.size();j++){
                    if (((String)tos.get(j).get("dlvNumber")).equals(CommonUtilities.getDeliveryNumber())){
                        Map<String, Object> to = tos.get(j);
                        if (((String)to.get("srcPlant")).equals(CommonUtilities.getSiteId())){
                            ExtentReportManager.logPassDetails("srcPlant field is Passed");
                            ExtentReportManager.logInfoDetails("Expected srcPlant is "+CommonUtilities.getSiteId()+" and the Actual srcPlant is "+to.get("srcPlant"));
                        }
                        else {
                            ExtentReportManager.logFailureDetails("srcPlant field is Failed");
                            ExtentReportManager.logInfoDetails("Expected srcPlant is "+CommonUtilities.getSiteId()+" and the Actual srcPlant is "+to.get("srcPlant"));
                        }
                        Assert.assertEquals((String)to.get("srcPlant"), CommonUtilities.getSiteId());

                        if (((String)to.get("txfOrdNumber")).equals(CommonUtilities.gettxfOrdNumber())){
                            ExtentReportManager.logPassDetails("txfOrdNumber field is Passed");
                            ExtentReportManager.logInfoDetails("Expected txfOrdNumber is "+CommonUtilities.gettxfOrdNumber()+" and the Actual txfOrdNumber is "+to.get("txfOrdNumber"));
                        }
                        else {
                            ExtentReportManager.logFailureDetails("txfOrdNumber field is Failed");
                            ExtentReportManager.logInfoDetails("Expected txfOrdNumber is "+CommonUtilities.gettxfOrdNumber()+" and the Actual txfOrdNumber is "+to.get("txfOrdNumber"));
                        }
                        Assert.assertEquals((String)to.get("txfOrdNumber"), CommonUtilities.gettxfOrdNumber());

                        ExtentReportManager.logPassDetails("dlvNumber field is passed");
                        ExtentReportManager.logInfoDetails("Expected dlvNumber is "+CommonUtilities.getDeliveryNumber()+" and the Actual dlvNumber is "+to.get("dlvNumber"));

                        List<String> tolineItemNumbers = (List<String>) assignPickerRequestPayload.get("tolineItemNumbers");
                        if (((int)to.get("assigneeTotalBins"))==tolineItemNumbers.size()){
                            ExtentReportManager.logPassDetails("assigneeTotalBins field is Passed");
                            ExtentReportManager.logInfoDetails("Expected assigneeTotalBins is "+tolineItemNumbers.size()+" and the Actual assigneeTotalBins is "+to.get("assigneeTotalBins"));
                        }
                        else {
                            ExtentReportManager.logFailureDetails("assigneeTotalBins field is Failed");
                            ExtentReportManager.logInfoDetails("Expected assigneeTotalBins is "+tolineItemNumbers.size()+" and the Actual assigneeTotalBins is "+to.get("assigneeTotalBins"));
                        }
                        Assert.assertEquals((int)to.get("assigneeTotalBins"), tolineItemNumbers.size());

                        if (((int)to.get("totalBins"))==CommonUtilities.getTxfOrdItemSeq().size()){
                            ExtentReportManager.logPassDetails("totalBins field is Passed");
                            ExtentReportManager.logInfoDetails("Expected totalBins is "+CommonUtilities.getTxfOrdItemSeq().size()+" and the Actual totalBins is "+to.get("totalBins"));
                        }
                        else {
                            ExtentReportManager.logFailureDetails("totalBins field is Passed");
                            ExtentReportManager.logInfoDetails("Expected totalBins is "+CommonUtilities.getTxfOrdItemSeq().size()+" and the Actual totalBins is "+to.get("totalBins"));
                        }
                        Assert.assertEquals((int)to.get("totalBins"), CommonUtilities.getTxfOrdItemSeq().size());

                        if (((int)to.get("unassignedItems"))==(CommonUtilities.getTxfOrdItemSeq().size()-tolineItemNumbers.size())){
                            ExtentReportManager.logPassDetails("unassignedItems field is Passed");
                            ExtentReportManager.logInfoDetails("Expected unassignedItems is "+(CommonUtilities.getTxfOrdItemSeq().size()-tolineItemNumbers.size())+" and the Actual unassignedItems is "+to.get("unassignedItems"));
                        }
                        else {
                            ExtentReportManager.logFailureDetails("unassignedItems field is Failed");
                            ExtentReportManager.logInfoDetails("Expected unassignedItems is "+(CommonUtilities.getTxfOrdItemSeq().size()-tolineItemNumbers.size())+" and the Actual unassignedItems is "+to.get("unassignedItems"));
                        }
                        Assert.assertEquals((int)to.get("unassignedItems"), CommonUtilities.getTxfOrdItemSeq().size()-tolineItemNumbers.size());

                        if (((String)to.get("destPlant")).equals(CommonUtilities.getDstSiteId())){
                            ExtentReportManager.logPassDetails("destPlant field is Passed");
                            ExtentReportManager.logInfoDetails("Expected destPlant is "+CommonUtilities.getDstSiteId()+" and the Actual destPlant is "+to.get("destPlant"));
                        }
                        else {
                            ExtentReportManager.logFailureDetails("destPlant field is Failed");
                            ExtentReportManager.logInfoDetails("Expected destPlant is "+CommonUtilities.getDstSiteId()+" and the Actual destPlant is "+to.get("destPlant"));
                        }
                        Assert.assertEquals((String)to.get("destPlant"), CommonUtilities.getDstSiteId());

                        if (((String)assignPickerRequestPayload.get("siteId")).equals("4019")){
                            if (((String)to.get("dispatchType")).equals("Box")){
                                ExtentReportManager.logPassDetails("dispatchType field is Passed");
                                ExtentReportManager.logInfoDetails("Expected dispatchType is Box and the Actual dispatchType is "+to.get("dispatchType"));
                            }
                            else {
                                ExtentReportManager.logPassDetails("dispatchType field is Failed");
                                ExtentReportManager.logInfoDetails("Expected dispatchType is Box and the Actual dispatchType is "+to.get("dispatchType"));
                            }
                            Assert.assertEquals((String) to.get("dispatchType"), "Box");
                        }

                        else if (((String)assignPickerRequestPayload.get("siteId")).equals("5019")){
                            if (((String)to.get("dispatchType")).equals("Pallet")){
                                ExtentReportManager.logPassDetails("dispatchType field is Passed");
                                ExtentReportManager.logInfoDetails("Expected dispatchType is Box and the Actual dispatchType is "+to.get("dispatchType"));
                            }
                            else {
                                ExtentReportManager.logPassDetails("dispatchType field is Failed");
                                ExtentReportManager.logInfoDetails("Expected dispatchType is Box and the Actual dispatchType is "+to.get("dispatchType"));
                            }
                            Assert.assertEquals((String) to.get("dispatchType"), "Pallet");
                        }
                        break;
                    }

                    else if (j==tos.size()-1 && !((String)tos.get(j).get("dlvNumber")).equals(CommonUtilities.getDeliveryNumber())){
                        ExtentReportManager.logFailureDetails("dlvNumber field is failed");
                        ExtentReportManager.logInfoDetails("delivery is not assgined to Picker");
                        Assert.fail();
                    }
                }
                break;
            }

            else if (i==responseList.size()-1 && !((String)responseList.get(i).get("assigneeId")).equals((String) assignPickerRequestPayload.get("assigneeId"))){
                ExtentReportManager.logFailureDetails("assigneeId  field is Failed");
                ExtentReportManager.logInfoDetails("Picker is not Present");
                Assert.fail();
            }
        }
    }
}

