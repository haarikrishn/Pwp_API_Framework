package com.dmartLabs.stepdefinitions;

import com.dmartLabs.commonutils.ExtentReportManager;
import com.dmartLabs.commonutils.GenricUtils;
import com.dmartLabs.commonutils.JSONUtils;
import com.dmartLabs.commonutils.RequestGenerator;
import com.dmartLabs.config.ConStants;
import com.dmartLabs.config.PropertyReader;
import com.dmartLabs.pojo.CreateToMainPojo;
import com.dmartLabs.pojo.TransferOrderItemsToPojo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.JSONArray;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CreateTOStep {

    RequestGenerator requestGenerator=new RequestGenerator();

    ArrayList<CreateToMainPojo> createToMainpojoArrayList=new ArrayList<>();
    public  static  CreateToMainPojo createToMainPojo=new CreateToMainPojo();
    ArrayList<TransferOrderItemsToPojo>transferOrderItemsToPojoArrayList=new ArrayList<>();
    Response CreateTOResponse;

    @And("Assigning Main Mandatory fields to CreateTOWithListOftransferOrderItems")
    public void assigningMainMandatoryFieldsToCreateTOWithListOftransferOrderItems(DataTable dataTable) {
        Map<String, String> CreateTODT = dataTable.asMap(String.class, String.class);
        createToMainPojo.setWhNumber(CreateTODT.get("whNumber"));
        CommonUtilities.setWhNumber(CreateTODT.get("whNumber"));
        createToMainPojo.setSrcPlant(CreateTODT.get("srcPlant"));
        createToMainPojo.setDestPlant(CreateTODT.get("destPlant"));
        createToMainPojo.setGrpDlv(CreateTODT.get("grpDlv"));
        String actualtxfOrdNumber = GenricUtils.gettxfOrdNumber();
        createToMainPojo.setTxfOrdNumber(actualtxfOrdNumber);
        CommonUtilities.settxfOrdNumber(createToMainPojo.getTxfOrdNumber());
        createToMainPojo.setMovementType(CreateTODT.get("movementType"));
        createToMainPojo.setCreationDate(GenricUtils.getDate("yyyyMMdd"));
        createToMainPojo.setCreationTime(GenricUtils.getTime("HHmmss"));

        String originaldlvumber = CommonUtilities.getDeliveryNumber();
        createToMainPojo.setDlvNumbers(Collections.singletonList(originaldlvumber));
    }

    @And("Provide transferOrderItems to CreateTOWithListOftransferOrderItems")
    public void provideTransferOrderItemsToCreateTOWithListOftransferOrderItems(DataTable dataTable) {

        List<Map<String, String>> transferOrderItemsDT = dataTable.asMaps();
        int ActualTransferOrderItemsCount = transferOrderItemsDT.size();
        CommonUtilities.setTransferOrderItemsCount(ActualTransferOrderItemsCount);
        List<String> txfOrdItemSeq = new ArrayList<>();

        for(int i=0;i<transferOrderItemsDT.size();i++)
        {
            Map<String, String> transferOrderItemsDTOneByOne = transferOrderItemsDT.get(i);

            TransferOrderItemsToPojo transferOrderItemsToPojo=new TransferOrderItemsToPojo();
            transferOrderItemsToPojo.setTxfOrdItemSeq(transferOrderItemsDTOneByOne.get("txfOrdItemSeq"));
            txfOrdItemSeq.add(transferOrderItemsDTOneByOne.get("txfOrdItemSeq"));
            transferOrderItemsToPojo.setMatNumber(transferOrderItemsDTOneByOne.get("matNumber"));
            transferOrderItemsToPojo.setMatDesc(transferOrderItemsDTOneByOne.get("matDesc"));
            transferOrderItemsToPojo.setEan(transferOrderItemsDTOneByOne.get("ean"));
            transferOrderItemsToPojo.setBatchNum(transferOrderItemsDTOneByOne.get("batchNum"));
            transferOrderItemsToPojo.setSrcStorageType(transferOrderItemsDTOneByOne.get("srcStorageType"));
            transferOrderItemsToPojo.setSrcStorageBin(transferOrderItemsDTOneByOne.get("srcStorageBin"));
            transferOrderItemsToPojo.setSrcQty(transferOrderItemsDTOneByOne.get("srcQty"));
            transferOrderItemsToPojo.setCaselot(transferOrderItemsDTOneByOne.get("caselot"));
            transferOrderItemsToPojo.setUom(transferOrderItemsDTOneByOne.get("uom"));
            transferOrderItemsToPojo.setMrp(transferOrderItemsDTOneByOne.get("mrp"));
            transferOrderItemsToPojo.setItemWeight(transferOrderItemsDTOneByOne.get("itemWeight"));
            transferOrderItemsToPojo.setItemVolume(transferOrderItemsDTOneByOne.get("itemVolume"));
            transferOrderItemsToPojo.setItemType(transferOrderItemsDTOneByOne.get("itemType"));
            transferOrderItemsToPojo.setBinSeq(transferOrderItemsDTOneByOne.get("binSeq"));
            transferOrderItemsToPojo.setBbDate(GenricUtils.getDate("yyyyMMdd"));
            transferOrderItemsToPojo.setStorageLoc(transferOrderItemsDTOneByOne.get("storageLoc"));
            transferOrderItemsToPojo.setDestStorageType(transferOrderItemsDTOneByOne.get("destStorageType"));
            transferOrderItemsToPojo.setPalletQty(transferOrderItemsDTOneByOne.get("palletQty"));
            transferOrderItemsToPojoArrayList.add(transferOrderItemsToPojo);
        }
        CommonUtilities.setTxfOrdItemSeq(txfOrdItemSeq);
        createToMainPojo.setTransferOrderItems(transferOrderItemsToPojoArrayList);
        createToMainpojoArrayList.add(createToMainPojo);

    }

    @When("User calls the to create TO End point to CreateTOWithListOftransferOrderItems")
    public void userCallsTheToCreateTOEndPointToCreateTOWithListOftransferOrderItems() {
//        GetListOfDeliveriesResponse = requestGenerator.getRequestGet(CommonUtilities.genericHeader()).log().all()
//                .when().get(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "GetListOfDeliveriesEndPoint"));
//        GetListOfDeliveriesResponse.then().log().all();
        CreateTOResponse = requestGenerator.getRequest(GenericSteps.userCredential, createToMainpojoArrayList).log().all()
                .when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateTOEndPoint"));
        CreateTOResponse.then().log().all();
        ExtentReportManager.logJson("Response is " +   CreateTOResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response status code is " +   CreateTOResponse.getStatusCode());
        long responseTime =    CreateTOResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response Time is "+responseTime+" ms");
        CommonUtilities.setResponseInstance(  CreateTOResponse);
    }
    //==========================================================================================
    //Create TO without delivery number using JSon file
    @When("user calls createTO end point to createTo without deliverynumber {string}")
    public void userCallsCreateTOEndPointToCreateToWithoutDeliverynumber(String jsonFileName) throws FileNotFoundException {

        JSONArray createTOJsonfile = JSONUtils.getRequestPayloadAsObjectFromJsonFileArray(jsonFileName);
        String createTOFileString = createTOJsonfile.toString();
        Response createTOFileResponse = requestGenerator.getRequestFileToString(GenericSteps.userCredential, createTOFileString).log().all()
                .when().post(PropertyReader.getProperty(ConStants.ENDPOINTS_PATHS_PROPERTIES_PATH, "CreateTOEndPoint"));
        createTOFileResponse.then().log().all();
        long responsetime = createTOFileResponse.getTimeIn(TimeUnit.MILLISECONDS);
        ExtentReportManager.logInfoDetails("Response status code is " + createTOFileResponse.getStatusCode());
        ExtentReportManager.logJson("Response is " +    createTOFileResponse.getBody().prettyPrint());
        ExtentReportManager.logInfoDetails("Response Time is "+responsetime+" ms");
        CommonUtilities.setResponseInstance(createTOFileResponse);
    }

}
