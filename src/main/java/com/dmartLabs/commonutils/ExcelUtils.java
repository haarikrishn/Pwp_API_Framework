package com.dmartLabs.commonutils;


import com.dmartLabs.config.ConStants;
import com.dmartLabs.pojo.ConfirmDeliveryPOJO;
import cucumber.api.DataTable;
import org.apache.poi.ss.usermodel.*;
import rst.pdfbox.layout.text.Constants;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelUtils extends Constants {

    static FileInputStream fis;
    static Workbook workbook;
    static DataFormatter df;
    static FileOutputStream fos;
    static ExcelUtils excelUtils;

    public ExcelUtils(String fileName) {
        try {
            fis = new FileInputStream(ConStants.EXCEL_FILE_PATH + fileName);
            workbook = WorkbookFactory.create(fis);
            df = new DataFormatter();
//            fos = new FileOutputStream(ConStants.EXCEL_FILE_PATH + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

//    /**
//     * @param mobNumber
//     * @throws IOException
//     */
//    public void setMobileNumberDirect(String mobNumber) {
//
//        Sheet sheet = workbook.getSheet("Sheet1");
//        int temp = 1;
//        for (int i = 0; i < sheet.getLastRowNum(); i++) {
//
//            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
//                String key = df.formatCellValue(sheet.getRow(i).getCell(j));
//                if (key.equalsIgnoreCase("Mobile Number")) {
//                    System.out.println(key + " is a key");
//                    Row row = sheet.getRow(temp);
//                    Cell cell = row.createCell(j);
//                    cell.setCellValue(mobNumber);
//                    System.out.println(key + " data is updated");
//                } else if (key.equalsIgnoreCase(null)) {
//                    break;
//                }
//            }
//        }
//
//        try {
//            workbook.write(fos);
//            fos.close();
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * @param regNumber
//     * @throws IOException
//     */
//    public void setStateRegNumberDirect(String regNumber) {
//
//        Sheet sheet = workbook.getSheet("Sheet1");
//        int temp = 1;
//        for (int i = 0; i < sheet.getLastRowNum(); i++) {
//
//            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
//                String key = df.formatCellValue(sheet.getRow(i).getCell(j));
//                if (key.equalsIgnoreCase("State Reg Number")) {
//                    System.out.println(key + " is a key");
//                    Row row = sheet.getRow(temp);
//                    Cell cell = row.createCell(j);
//                    cell.setCellValue(regNumber);
//                    System.out.println(key + " data is updated");
//                } else if (key.equalsIgnoreCase(null)) {
//                    break;
//                }
//            }
//        }
//        try {
//            workbook.write(fos);
//            fos.close();
//            workbook.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    /**
     * @param keyData
     * @param value
     * @throws IOException
     */
    public void setValueBasedOnKey(String keyData, String value) {

        Sheet sheet = workbook.getSheet("Sheet1");
        int temp = 1;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                String key = df.formatCellValue(sheet.getRow(i).getCell(j));
                if (key.equalsIgnoreCase(keyData)) {
                    System.out.println(key + " is a key");
                    Row row = sheet.getRow(temp);
                    Cell cell = row.createCell(j);
                    cell.setCellValue(value);
                    System.out.println(key + " data is updated");
                } else if (key.equalsIgnoreCase(null)) {
                    break;
                }
            }
        }
    }


    /**
     *
     * @param keyData
     * @return
     */
    public String getExcellDataBasedOnKey(String keyData) {

        Sheet sheet = workbook.getSheet("Sheet1");
        int temp = 1;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                String key = df.formatCellValue(sheet.getRow(i).getCell(j));
                if (key.equalsIgnoreCase(keyData)) {
                    System.out.println(key + " is a key");
                    System.out.println(df.formatCellValue(sheet.getRow(temp).getCell(j)) + " data is get");
                   return df.formatCellValue(sheet.getRow(temp).getCell(j));
                } else if (key.equalsIgnoreCase(null)) {
                    break;
                }
            }
        }
        return null;
    }

    /**
     * @return
     */
    public String getExcelKeyData(int columnNumber) {

        Sheet sheet = workbook.getSheet("Sheet1");
        int temp = 0;
        System.out.println(df.formatCellValue(sheet.getRow(temp).getCell(columnNumber)) + " data is get");
        return df.formatCellValue(sheet.getRow(temp).getCell(columnNumber));
    }



    public String getExcellDataBasedOnRowAndKeyWise(int row, String keyData) {

        Sheet sheet = workbook.getSheet("Sheet1");
//        int temp = row;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
                String key = df.formatCellValue(sheet.getRow(i).getCell(j));
                if (key.equalsIgnoreCase(keyData)) {
                    System.out.println(key + " is a key");
                    try {
                        System.out.println(df.formatCellValue(sheet.getRow(row).getCell(j)) + " data is get");
                        return df.formatCellValue(sheet.getRow(row).getCell(j));
                    }catch (IndexOutOfBoundsException e){
                        e.printStackTrace();
                    }

                } else if (key.equalsIgnoreCase(null)) {
                    break;
                }
            }
        }
        return null;
    }

    /**
     *
     */
    public static void closeWorkbook() {
        try {
//            workbook.write(fos);
            fis.close();
//            fos.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public LinkedHashMap<String, Object> getConfirmDeliveryRequestPayload(String sheetName, int rowNum) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        if (rowNum>totalRows || rowNum<=0){
            System.out.println("Please Enter valid Row Number !!!!");
            return null;
        }
        else {
            LinkedHashMap<String, Object> items = new LinkedHashMap<>();
            List<String> allKeys = new ArrayList<>();

            int dlvItemKeyIndex=0;

            for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
                allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
                if (allKeys.get(i).equals("dlvItems")){
                    dlvItemKeyIndex = i;
                }
            }

            List<Map<String, String>> deliveryItems = new ArrayList<>();
            Map<String, String> deliveryItem = new HashMap<>();
            for (int j=0;j<sheet.getRow(rowNum).getPhysicalNumberOfCells();j++){
                Row row = sheet.getRow(rowNum);
                String values = df.formatCellValue(row.getCell(j));
                if (!(values.equals(""))) {

                    if (values.equals("\"\"")){
                        values = "";
                    }

                    if (values.equals("null")){
                        values = null;
                    }

                    if (allKeys.get(j).contains("dlvItemNum") ||
                            allKeys.get(j).contains("plannedDlvBoxQty") ||
                            allKeys.get(j).contains("minimumBoxes")){

                                deliveryItem.put(allKeys.get(j), values);
                    }
                    else{
                        if (values!=null && values.contains(",")){
                            String[] arr = values.split(",");
                            List<String> arr1 = new ArrayList<>();
                            for (String val:arr) {
                                arr1.add(val);
                            }
                            items.put(allKeys.get(j), arr1);
                        }
                        else if(values == null && allKeys.get(j).contains("dlvItems")){
                            items.put(allKeys.get(j), values);
                            return items;
                        }
                        else if (values!=null && values.equals("[]") && allKeys.get(j).contains("dlvItems")){
                            items.put(allKeys.get(j), deliveryItems);
                            return items;
                        }
                        else if (values==null){
                            items.put(allKeys.get(j), values);
                        }
                        else{
                            items.put(allKeys.get(j), values);
                            if (allKeys.get(j).contains("dlvItems")){
                                //    return items;
                            }
                        }
                    }
                }

                Set<String> keys = deliveryItem.keySet();

                if (keys.size()==3){
                    deliveryItems.add(deliveryItem);
                    deliveryItem = new HashMap<>();
                }
                else if ( (deliveryItem.containsKey("dlvItemNum") && deliveryItem.containsKey("plannedDlvBoxQty") && df.formatCellValue(row.getCell(j+1)).equals(""))
                        || (deliveryItem.containsKey("dlvItemNum") && deliveryItem.containsKey("minimumBoxes") && !(deliveryItem.containsKey("plannedDlvBoxQty")))
                        || (deliveryItem.containsKey("plannedDlvBoxQty") && deliveryItem.containsKey("minimumBoxes") && !(deliveryItem.containsKey("dlvItemNum"))) ){
                    deliveryItems.add(deliveryItem);
                    deliveryItem = new HashMap<>();
                }
                else if ( (deliveryItem.containsKey("dlvItemNum") && df.formatCellValue(row.getCell(j+1)).equals("") && df.formatCellValue(row.getCell(j+2)).equals(""))
                        || (deliveryItem.containsKey("plannedDlvBoxQty") && df.formatCellValue(row.getCell(j+1)).equals("") && !(deliveryItem.containsKey("dlvItemNum")))
                        || (deliveryItem.containsKey("minimumBoxes") && !(deliveryItem.containsKey("dlvItemNum")) && !(deliveryItem.containsKey("plannedDlvBoxQty"))) ){
                    deliveryItems.add(deliveryItem);
                    deliveryItem = new HashMap<>();
                }
            }
            if (deliveryItems.size()>0){
                items.put(allKeys.get(dlvItemKeyIndex), deliveryItems);
            }
            try {
                ExcelUtils.closeWorkbook();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return items;
        }
    }

    public List<LinkedHashMap<String, Object>> getConfirmDeliveryAllRequestPayload(String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        List<LinkedHashMap<String, Object>> allRequestPayloads = new ArrayList<>();

        LinkedHashMap<String, Object> requestPayload;

        List<String> allKeys = new ArrayList<>();

        int dlvItemKeyIndex=0;

        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
            if (allKeys.get(i).equals("dlvItems")){
                dlvItemKeyIndex = i;
            }
        }

        List<Map<String, String>> deliveryItems;
        Map<String, String> deliveryItem = new HashMap<>();

        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            requestPayload = new LinkedHashMap<>();
            deliveryItems = new ArrayList<>();

            if (sheet.getRow(i).getCell(0).getStringCellValue().equals("end")){
                break;
            }
            else{
                Row row = sheet.getRow(i);
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(row.getCell(j));
                    if (!(values.equals(""))) {

                        if (values.equals("\"\"")){
                            values = "";
                        }

                        if (values.equals("null")){
                            values = null;
                        }

                        if (allKeys.get(j).contains("dlvItemNum") ||
                                allKeys.get(j).contains("plannedDlvBoxQty") ||
                                allKeys.get(j).contains("minimumBoxes")){
                                    deliveryItem.put(allKeys.get(j), values);
                        }
                        else{
                            if (values!=null && values.contains(",")){
                                String[] arr = values.split(",");
                                List<String> arr1 = new ArrayList<>();
                                for (String val:arr) {
                                    arr1.add(val);
                                }
                                requestPayload.put(allKeys.get(j), arr1);
                            }
                            else if(values == null && allKeys.get(j).contains("dlvItems")){
                                deliveryItems = null;
                                requestPayload.put(allKeys.get(j), deliveryItems);
                                break;
                            }
                            else if (values!=null && values.equals("[]") && allKeys.get(j).contains("dlvItems")){
                                requestPayload.put(allKeys.get(j), deliveryItems);
                                break;
                            }
                            else if (values==null){
                                requestPayload.put(allKeys.get(j), values);
                            }
                            else{
                                requestPayload.put(allKeys.get(j), values);
                                if (allKeys.get(j).contains("dlvItems")){
                                    break;
                                }
                            }
                        }
                    }
                    Set<String> keys = deliveryItem.keySet();

                    if (keys.size()==3){
                        deliveryItems.add(deliveryItem);
                        deliveryItem = new HashMap<>();
                    }
                    else if ( (deliveryItem.containsKey("dlvItemNum") && deliveryItem.containsKey("plannedDlvBoxQty") && df.formatCellValue(row.getCell(j+1)).equals(""))
                            || (deliveryItem.containsKey("dlvItemNum") && deliveryItem.containsKey("minimumBoxes") && !(deliveryItem.containsKey("plannedDlvBoxQty")))
                            || (deliveryItem.containsKey("plannedDlvBoxQty") && deliveryItem.containsKey("minimumBoxes") && !(deliveryItem.containsKey("dlvItemNum"))) ){
                        deliveryItems.add(deliveryItem);
                        deliveryItem = new HashMap<>();
                    }
                    else if ( (deliveryItem.containsKey("dlvItemNum") && df.formatCellValue(row.getCell(j+1)).equals("") && df.formatCellValue(row.getCell(j+2)).equals(""))
                            || (deliveryItem.containsKey("plannedDlvBoxQty") && df.formatCellValue(row.getCell(j+1)).equals("") && !(deliveryItem.containsKey("dlvItemNum")))
                            || (deliveryItem.containsKey("minimumBoxes") && !(deliveryItem.containsKey("dlvItemNum")) && !(deliveryItem.containsKey("plannedDlvBoxQty"))) ){
                        deliveryItems.add(deliveryItem);
                        deliveryItem = new HashMap<>();
                    }

                    if (deliveryItems != null && deliveryItems.size()>0){
                        requestPayload.put(allKeys.get(dlvItemKeyIndex), deliveryItems);
                    }
                }
                allRequestPayloads.add(requestPayload);
            }
        }

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allRequestPayloads;
    }

    public Map<String, String> getConfirmedDeliveryDetails(String sheetName, int row) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        if (row>totalRows || row<=0){
            System.out.println("Please Enter valid Row Number !!!!");
            return null;
        }
        else {
            Map<String, String> fieldDetails = new LinkedHashMap<>();
            List<String> allKeys = new ArrayList<>();

            for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
                allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
                if (allKeys.get(i).equals("dlvItems")){
                }
            }

            for (int j=0;j<sheet.getRow(row).getPhysicalNumberOfCells();j++){
                String values = df.formatCellValue(sheet.getRow(row).getCell(j));
                if (!(values.equals(""))) {
                    fieldDetails.put(allKeys.get(j), values);
                }
            }
            try {
                ExcelUtils.closeWorkbook();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return fieldDetails;
        }
    }

    public List<Map<String, String>> getAllConfirmedDeliveryDetails(String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getPhysicalNumberOfRows();

        List<Map<String, String>> allFieldDetails = new ArrayList<>();
        Map<String, String> FieldDetails;
        List<String> allKeys = new ArrayList<>();

        for (int i=0;i<sheet.getRow(0).getPhysicalNumberOfCells();i++){
            allKeys.add(sheet.getRow(0).getCell(i).getStringCellValue());
        }

        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++) {
            if (!(sheet.getRow(i).getCell(0).getStringCellValue().equals(""))){
                FieldDetails = new LinkedHashMap<>();
                for (int j = 0; j < sheet.getRow(i).getPhysicalNumberOfCells(); j++) {
                    String values = df.formatCellValue(sheet.getRow(i).getCell(j));
                    if (!(values.equals(""))) {
                        FieldDetails.put(allKeys.get(j), values);
                    }
                }
                allFieldDetails.add(FieldDetails);
            }
        }

        try {
            ExcelUtils.closeWorkbook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allFieldDetails;
    }

    public static void main(String[] args) {

        ExcelUtils excelUtils = new ExcelUtils("PWP_TestData.xlsx");
        LinkedHashMap<String, Object> confirmDeliveryRequest = excelUtils.getConfirmDeliveryRequestPayload("GetDeliveryByDeliveryNumber", 3);
//        String dlvNumber = "0098765432";
//        if (confirmDeliveryRequest.containsKey("dlvItems") && confirmDeliveryRequest.get("dlvItems")!=null && !confirmDeliveryRequest.get("dlvItems").equals("")){
//            for (Map<String, String> dlvItem: (List<Map<String, String>>)confirmDeliveryRequest.get("dlvItems")) {
//                if (dlvItem.containsKey("dlvItemNum")){
//                    String itemSeq = dlvItem.get("dlvItemNum");
//                    if (dlvItem.get("dlvItemNum")!=null && !(dlvItem.get("dlvItemNum").equals(""))) {
//                        dlvItem.put("dlvItemNum", dlvNumber + itemSeq);
//                   }
//                }
//            }
//        }
        System.out.println(confirmDeliveryRequest);

//        String abc="\"\"";
//        if (abc.equals("\"\"")){
//            abc = "ad";
//            System.out.println(abc);
//        }
//        if (confirmDeliveryRequest.get("dock").equals("\"\"")){
//            String values="";
//            confirmDeliveryRequest.put("dock", values);
//            System.out.println("dock is empty string ==> "+confirmDeliveryRequest.get("dock"));
//        }




        List<LinkedHashMap<String, Object>> allRequest = excelUtils.getConfirmDeliveryAllRequestPayload("InvalidDock");
        for (LinkedHashMap<String, Object> request:
        allRequest) {
            System.out.println(request);
            System.out.println();
        }

    }
}