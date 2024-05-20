package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.JSONUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import com.dmartLabs.pojo.CreateDeliveryPojo;
import com.dmartLabs.pojo.DlvIemsMainPojo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.dmartLabs.config.ConStants.JSON_FILE_PATH;

public class CreateDeliveryStep {

    RequestGenerator requestGenerator = new RequestGenerator();
    Response CreateDeliveryResponse;
    ArrayList<CreateDeliveryPojo> createDelivery = new ArrayList<>();
    public static CreateDeliveryPojo createdeliverypojo = new CreateDeliveryPojo();
    ArrayList<DlvIemsMainPojo> dlvItems = new ArrayList<>();

    // public static DlvIemsMainPojo dlvmainpojo = new DlvIemsMainPojo();
    DlvIemsMainPojo dlvmainpojo1 = new DlvIemsMainPojo();
    DlvIemsMainPojo dlvmainpojo2 = new DlvIemsMainPojo();
    public int ActualTotalBoxes=0;
    //============================================================================================================
//create Delivert with list of items using data table
    @And("Assigning Main Mandatory fields to CreateOneDeliveryWithListOfItemsUsingDT")
    public void assigningMainMandatoryFieldsToCreateOneDeliveryWithListOfItemsUsingDT(DataTable dataTable) {
        Map<String, String> CreateDeliveryDT = dataTable.asMap(String.class, String.class);
        String dlvNumber = GenricUtils.getRandomDeliveryNumber();
        createdeliverypojo.setDlvNumber(dlvNumber);
        CommonUtilities.setDeliveryNumber(dlvNumber);
        System.out.println(dlvNumber + "                          random delivey number");
        System.out.println(CommonUtilities.getDeliveryNumber() + "          common utilitites delivery number");
        createdeliverypojo.setProposedDlvDate(GenricUtils.getDate("yyyyMMdd"));
        createdeliverypojo.setSrcSiteId(CreateDeliveryDT.get("srcSiteId"));
        createdeliverypojo.setDstSiteId(CreateDeliveryDT.get("dstSiteId"));
        CommonUtilities.setDstSiteID(createdeliverypojo.getDstSiteId());
        createdeliverypojo.setWhNumber(CreateDeliveryDT.get("whNumber"));
        CommonUtilities.setWhNumber(createdeliverypojo.getWhNumber());
        createdeliverypojo.setCreatedBy(CreateDeliveryDT.get("createdBy"));
        createdeliverypojo.setCreationDate(GenricUtils.getDate("yyyyMMdd"));
        createdeliverypojo.setCreationTime(GenricUtils.getTime("HHmmss"));
        createdeliverypojo.setTotalGdsMvtStat(CreateDeliveryDT.get("totalGdsMvtStat"));
    }
    //public  static  float ActualTotalBoxes=0;

    @And("Provide dlvItemNum to CreateOneDeliveryWithListOfItemsUsingDT")
    public void provideDlvItemNumToCreateOneDeliveryWithListOfItemsUsingDT(DataTable dataTable) {

        List<Map<String, String>> dlvItemsCreateDeliveryDT = dataTable.asMaps();
        for (int i = 0; i < dlvItemsCreateDeliveryDT.size(); i++) {
            Map<String, String> dlvItemOneByOne = dlvItemsCreateDeliveryDT.get(i);//data table
            DlvIemsMainPojo dlvmainpojo = new DlvIemsMainPojo();

            dlvmainpojo.setItemSeq(dlvItemOneByOne.get("itemSeq"));
            dlvmainpojo.setStoNum(dlvItemOneByOne.get("stoNum"));
            dlvmainpojo.setStoLineNum(dlvItemOneByOne.get("stoLineNum"));
            dlvmainpojo.setEan(dlvItemOneByOne.get("ean"));
            dlvmainpojo.setMrp(dlvItemOneByOne.get("mrp"));
            dlvmainpojo.setMatNum(dlvItemOneByOne.get("matNum"));
            dlvmainpojo.setItemDesc(dlvItemOneByOne.get("itemDesc"));
            dlvmainpojo.setMatGrp(dlvItemOneByOne.get("matGrp"));
            dlvmainpojo.setCatInd(dlvItemOneByOne.get("catInd"));
            dlvmainpojo.setStLoc(dlvItemOneByOne.get("stLoc"));
            dlvmainpojo.setBatch(dlvItemOneByOne.get("batch"));
            dlvmainpojo.setProposedDlvQty(dlvItemOneByOne.get("proposedDlvQty"));
            dlvmainpojo.setCaselot(dlvItemOneByOne.get("caselot"));
            dlvmainpojo.setUom(dlvItemOneByOne.get("uom"));
            dlvmainpojo.setItemWt(dlvItemOneByOne.get("itemWt"));
            dlvmainpojo.setUomWt(dlvItemOneByOne.get("uomWt"));
            dlvmainpojo.setItemVol(dlvItemOneByOne.get("itemVol"));
            dlvmainpojo.setUomVol(dlvItemOneByOne.get("uomVol"));
            dlvmainpojo.setPickStatus(dlvItemOneByOne.get("pickStatus"));
            dlvItems.add(dlvmainpojo);
            float proposedDlvQtys=Float.parseFloat(dlvmainpojo.getProposedDlvQty());
            float caselots = Float.parseFloat(dlvmainpojo.getCaselot());
            ActualTotalBoxes = ActualTotalBoxes + (int)(proposedDlvQtys/caselots);
        }
        createdeliverypojo.setDlvItems(dlvItems);
        createDelivery.add(createdeliverypojo);
        CommonUtilities.settotalboxescount(ActualTotalBoxes);
        //     System.out.println(ActualTotalBoxes+"  =========================>ActualTotalBoxes");
    }

    @When("User calls the to createDelivery End point to   CreateOneDeliveryWithListOfItemsUsingDT")
    public void userCallsTheToCreateDeliveryEndPointToCreateOneDeliveryWithListOfItemsUsingDT() {
        CreateDeliveryResponse = requestGenerator.getRequest(GenericSteps.userCredential, createDelivery).log().all()
                .when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateDeliveryEndpoint"));
        CreateDeliveryResponse.then().log().all();
        ExtentReportManager.logJson("Response is " + CreateDeliveryResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " + CreateDeliveryResponse.getStatusCode());
        long responseTime = CreateDeliveryResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is " + responseTime + " ms");
        CommonUtilities.setResponseInstance(CreateDeliveryResponse);

    }

    //=========================================================================================================
    DlvIemsMainPojo dlvmainpojo = new DlvIemsMainPojo();

    //    #    1  creating delivery with list of delivery items
//    =============================================================================================================================
    @When("User calls the Create Delivery endPoint {string} {string} {string} {string} {string} {string}	{string} {string} {string} {string} {string}  {string} {string}	{string}	{string} {string}	{string}	{string}	{string}	{string}	{string}	{string}	{string}	{string} {string}	{string} {string} {string} {string} {string}  {string} {string}	{string}	{string} {string}	{string}	{string}	{string}	{string}	{string}	{string}	{string}	{string} {string}	{string} {string} {string} {string} {string}  {string} {string}	{string}	{string} {string}	{string}	{string}	{string}	{string}	{string}	{string}	{string}	{string}")
    public void userCallsTheCreateDeliveryEndPoint(String srcSiteId,String dstSiteId,String whNumber,String createdBy, String totalGdsMvtStat,String itemSeq,String stoNum,String stoLineNum,String ean,String mrp,String matNum,String itemDesc,String matGrp,String catInd,String stLoc,String batch,String proposedDlvQty,String caselot,String uom,String itemWt,String uomWt,String itemVol,String uomVol,String pickStatus,String itemSeq1,String stoNum1,String stoLineNum1,String ean1,String mrp1,String matNum1,String itemDesc1,String matGrp1,String catInd1,String stLoc1,String batch1,String proposedDlvQty1,String caselot1,String uom1,String itemWt1,String uomWt1,String itemVol1,String uomVol1,String pickStatus1,String itemSeq2,String stoNum2,String stoLineNum2,String ean2,String mrp2,String matNum2,String itemDesc2,String matGrp2,String catInd2,String stLoc2,String batch2,String proposedDlvQty2,String caselot2,String uom2,String itemWt2,String uomWt2,String itemVol2,String uomVol2,String pickStatus2) {
//        creating delivery with list of delivery items
        //==============================================

        String dlvNumber = GenricUtils.getRandomDeliveryNumber();
        //    System.out.println("dlvNumber in Create Delivery =========================================> "+dlvNumber);
        createdeliverypojo.setDlvNumber(dlvNumber);
        CommonUtilities.setDeliveryNumber(dlvNumber);
        createdeliverypojo.setProposedDlvDate(GenricUtils.getDate("yyyyMMdd"));
        createdeliverypojo.setSrcSiteId(srcSiteId);
        createdeliverypojo.setDstSiteId(dstSiteId);
        createdeliverypojo.setWhNumber(whNumber);
        createdeliverypojo.setCreatedBy(createdBy);
        createdeliverypojo.setCreationDate(GenricUtils.getDate("yyyyMMdd"));
        createdeliverypojo.setCreationTime(GenricUtils.getTime("HHmmss"));
        createdeliverypojo.setTotalGdsMvtStat(totalGdsMvtStat);
        dlvmainpojo.setItemSeq(itemSeq);
        dlvmainpojo.setStoNum(stoNum);
        dlvmainpojo.setStoLineNum(stoLineNum);
        dlvmainpojo.setEan(ean);
        dlvmainpojo.setMrp(mrp);
        dlvmainpojo.setMatNum(matNum);
        dlvmainpojo.setItemDesc(itemDesc);
        dlvmainpojo.setMatGrp(matGrp);
        dlvmainpojo.setCatInd(catInd);
        dlvmainpojo.setStLoc(stLoc);
        dlvmainpojo.setBatch(batch);
        dlvmainpojo.setProposedDlvQty(proposedDlvQty);
        dlvmainpojo.setCaselot(caselot);
        dlvmainpojo.setUom(uom);
        dlvmainpojo.setItemWt(itemWt);
        dlvmainpojo.setUomWt(uomWt);
        dlvmainpojo.setItemVol(itemVol);
        dlvmainpojo.setUomVol(uomVol);
        dlvmainpojo.setPickStatus(pickStatus);
        float proposedDlvQtys=Float.parseFloat(dlvmainpojo.getProposedDlvQty());
        float caselots = Float.parseFloat(dlvmainpojo.getCaselot());
        //getting expected total box quantity, proposed quantity by caseloteQuamtity give total boxes
        ActualTotalBoxes = ActualTotalBoxes + (int)(proposedDlvQtys/caselots);
        //==========================================
        dlvmainpojo1.setItemSeq(itemSeq1);
        dlvmainpojo1.setStoNum(stoNum1);
        dlvmainpojo1.setStoLineNum(stoLineNum1);
        dlvmainpojo1.setEan(ean1);
        dlvmainpojo1.setMrp(mrp1);
        dlvmainpojo1.setMatNum(matNum1);
        dlvmainpojo1.setItemDesc(itemDesc1);
        dlvmainpojo1.setMatGrp(matGrp1);
        dlvmainpojo1.setCatInd(catInd1);
        dlvmainpojo1.setStLoc(stLoc1);
        dlvmainpojo1.setBatch(batch1);
        dlvmainpojo1.setProposedDlvQty(proposedDlvQty1);
        dlvmainpojo1.setCaselot(caselot1);
        dlvmainpojo1.setUom(uom1);
        dlvmainpojo1.setItemWt(itemWt1);
        dlvmainpojo1.setUomWt(uomWt1);
        dlvmainpojo1.setItemVol(itemVol1);
        dlvmainpojo1.setUomVol(uomVol1);
        dlvmainpojo1.setPickStatus(pickStatus1);
        proposedDlvQtys=Float.parseFloat(dlvmainpojo.getProposedDlvQty());
        caselots = Float.parseFloat(dlvmainpojo.getCaselot());
        ActualTotalBoxes = ActualTotalBoxes + (int)(proposedDlvQtys/caselots);
        //proposed quantity by caselot quantity=total boxes
//==================================================
        dlvmainpojo2.setItemSeq(itemSeq2);
        dlvmainpojo2.setStoNum(stoNum2);
        dlvmainpojo2.setStoLineNum(stoLineNum2);
        dlvmainpojo2.setEan(ean2);
        dlvmainpojo2.setMrp(mrp2);
        dlvmainpojo2.setMatNum(matNum2);
        dlvmainpojo2.setItemDesc(itemDesc2);
        dlvmainpojo2.setMatGrp(matGrp2);
        dlvmainpojo2.setCatInd(catInd2);
        dlvmainpojo2.setStLoc(stLoc2);
        dlvmainpojo2.setBatch(batch2);
        dlvmainpojo2.setProposedDlvQty(proposedDlvQty2);
        dlvmainpojo2.setCaselot(caselot2);
        dlvmainpojo2.setUom(uom2);
        dlvmainpojo2.setItemWt(itemWt2);
        dlvmainpojo2.setUomWt(uomWt2);
        dlvmainpojo2.setItemVol(itemVol2);
        dlvmainpojo2.setUomVol(uomVol2);
        dlvmainpojo2.setPickStatus(pickStatus2);
        proposedDlvQtys=Float.parseFloat(dlvmainpojo.getProposedDlvQty());
        caselots = Float.parseFloat(dlvmainpojo.getCaselot());
        ActualTotalBoxes = ActualTotalBoxes + (int)(proposedDlvQtys/caselots);
        //  System.out.println("Expected Total B0xes ===================> "+expectedTotalBoxes);
        // CommonUtilities.expectedTotalBoxes = expectedTotalBoxes;




        //========================================================
        dlvItems.add(dlvmainpojo);
        dlvItems.add(dlvmainpojo1);
        dlvItems.add(dlvmainpojo2);
        createDelivery.add(createdeliverypojo);
        createdeliverypojo.setDlvItems(dlvItems);

        CreateDeliveryResponse = requestGenerator.getRequest(GenericSteps.userCredential, createDelivery).log().all()
                .when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateDeliveryEndpoint"));
        CreateDeliveryResponse.then().log().all();
        ExtentReportManager.logJson("Response is " +  CreateDeliveryResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " +   CreateDeliveryResponse.getStatusCode());
        long responseTime =   CreateDeliveryResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is "+responseTime+" ms");
        CommonUtilities.setResponseInstance(  CreateDeliveryResponse);

    }

    @Then("verify that status code be equal to {int} for createDelivery")
    public void verifyThatStatusCodeBeEqualToForCreateDelivery(int expectedStatusCode) {
        ExtentReportManager.logInfoDetails("Validate that status code is 201");
        if (  CreateDeliveryResponse.getStatusCode()==expectedStatusCode){
            ExtentReportManager.logPassDetails("Passed");
            ExtentReportManager.logInfoDetails("Expected status code is " +expectedStatusCode + " and the actual status code is " +  CreateDeliveryResponse.getStatusCode());
            System.out.println("generate token status code is validated");
        }
        else
        {
            ExtentReportManager.logPassDetails("failed");
            ExtentReportManager.logInfoDetails("Expected status code is " +expectedStatusCode + " and the actual status code  is " +  CreateDeliveryResponse.getStatusCode());
        }
        Assert.assertEquals(  CreateDeliveryResponse.statusCode(), expectedStatusCode);
    }

    //=================================================================================================================
    @When("User calls the Create Delivery endPoint for Negative point of view {string} {string} {string}	{string} {string} {string} {string} 	{string}	{string} {string} {string} {string} {string}  {string} {string}	{string}	{string} {string}	{string}	{string}	{string}	{string}	{string}	{string}	{string}	{string} {string}")
    public void userCallsTheCreateDeliveryEndPointForNegativePointOfView( String dlvNumber,String srcSiteId,String dstSiteId,String whNumber,String createdBy,String creationDate,String totalGdsMvtStat,String itemSeq,String stoNum,String stoLineNum,String ean,String mrp,String matNum,String itemDesc,String matGrp,String catInd,String stLoc,String batch,String proposedDlvQty,String caselot,String uom,String itemWt,String uomWt,String itemVol,String uomVol,String pickStatus,String CurrentScenarioIs) {
// 2  creating list of deliveries

        System.out.println("current scenario is"+"   "  +CurrentScenarioIs);
        if(!(dlvNumber.equals("")))
        {
            createdeliverypojo.setDlvNumber(dlvNumber);
        }
        else
        {
            createdeliverypojo.setDlvNumber(GenricUtils.getRandomDeliveryNumber());
        }
        createdeliverypojo.setProposedDlvDate(GenricUtils.getDate("yyyyMMdd"));
        createdeliverypojo.setSrcSiteId(srcSiteId);
        createdeliverypojo.setDstSiteId(dstSiteId);
        createdeliverypojo.setWhNumber(whNumber);
        createdeliverypojo.setCreatedBy(createdBy);
        if(!(creationDate.equals(""))) {
            createdeliverypojo.setCreationDate(creationDate);
        }
        else
        {
            createdeliverypojo.setCreationDate(GenricUtils.getDate("yyyyMMdd"));
        }
        createdeliverypojo.setCreationTime(GenricUtils.getTime("HHmmss"));
        createdeliverypojo.setTotalGdsMvtStat(totalGdsMvtStat);
        dlvmainpojo.setItemSeq(itemSeq);
        dlvmainpojo.setStoNum(stoNum);
        dlvmainpojo.setStoLineNum(stoLineNum);
        dlvmainpojo.setEan(ean);
        dlvmainpojo.setMrp(mrp);
        dlvmainpojo.setMatNum(matNum);
        dlvmainpojo.setItemDesc(itemDesc);
        dlvmainpojo.setMatGrp(matGrp);
        dlvmainpojo.setCatInd(catInd);
        dlvmainpojo.setStLoc(stLoc);
        dlvmainpojo.setBatch(batch);
        dlvmainpojo.setProposedDlvQty(proposedDlvQty);
        dlvmainpojo.setCaselot(caselot);
        dlvmainpojo.setUom(uom);
        dlvmainpojo.setItemWt(itemWt);
        dlvmainpojo.setUomWt(uomWt);
        dlvmainpojo.setItemVol(itemVol);
        dlvmainpojo.setUomVol(uomVol);
        dlvmainpojo.setPickStatus(pickStatus);
        float proposedDlvQtys=Float.parseFloat(dlvmainpojo.getProposedDlvQty());
        float caselots = Float.parseFloat(dlvmainpojo.getCaselot());
        //getting expected total box quantity

        dlvItems.add(dlvmainpojo);
        createDelivery.add(createdeliverypojo);
        createdeliverypojo.setDlvItems(dlvItems);

        CreateDeliveryResponse = requestGenerator.getRequest(GenericSteps.userCredential, createDelivery).log().all()
                .when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateDeliveryEndpoint"));
        CreateDeliveryResponse.then().log().all();
        ExtentReportManager.logJson("Response is " +  CreateDeliveryResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " +   CreateDeliveryResponse.getStatusCode());
        long responseTime =   CreateDeliveryResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is "+responseTime+" ms");
        CommonUtilities.setResponseInstance(  CreateDeliveryResponse);
    }

    //===================================================================================================
//using json Array object file
    //========================================
    @When("user calls createDelivery end point to create delivery in NeattivePointOfview {string}")
    public void userCallsCreateDeliveryEndPointToCreateDeliveryInNeattivePointOfview(String jsonFileName) throws FileNotFoundException {
        JSONArray createDeliveryTaskJsonFile = JSONUtils.getRequestPayloadAsObjectFromJsonFileArray(jsonFileName);
        String DeliveryFilestringTask = createDeliveryTaskJsonFile.toString();
        System.out.println("Request Body is =============> " + DeliveryFilestringTask);

        RequestSpecification requestSpecification = requestGenerator.getRequestFileToString(GenericSteps.userCredential, DeliveryFilestringTask).log().all();
        Response CreateDeliveryFilePwpResponse = requestSpecification.when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateDeliveryEndpoint"));
        CreateDeliveryFilePwpResponse.then().log().all();
        ExtentReportManager.logJson("Response is " +    CreateDeliveryFilePwpResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " +    CreateDeliveryFilePwpResponse.getStatusCode());
        long responseTime =     CreateDeliveryFilePwpResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is "+responseTime+" ms");
        CommonUtilities.setResponseInstance(CreateDeliveryFilePwpResponse);
    }




    //=============================================================================================================
    //  using json file String

    @When("user calls createDelivery end point to create deliveryTwo in NeattivePointOfview {string}")
    public void userCallsCreateDeliveryEndPointToCreateDeliveryTwoInNeattivePointOfview(String jsonFileName ) throws IOException {
        RequestSpecification CreateDeliveryFileRequest = RequestGenerator.getRequestFilepath(GenericSteps.userCredential, jsonFileName);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new File(JSON_FILE_PATH+jsonFileName));
        // Pretty print the JSON
        String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        System.out.println("Pretty Printed JSON Request Body:");
        System.out.println(prettyJson);
        Response CreateDeliveryFileResponse = CreateDeliveryFileRequest.when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateDeliveryEndpoint"));
        CreateDeliveryFileResponse.then().log().all();
        ExtentReportManager.logJson("Response is " +    CreateDeliveryFileResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " +     CreateDeliveryFileResponse.getStatusCode());
        long responseTime =     CreateDeliveryFileResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is "+responseTime+" ms");
        CommonUtilities.setResponseInstance(    CreateDeliveryFileResponse);
    }


}
