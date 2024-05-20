package com.dmartLabs.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Setter
@Getter
public class ConfirmDeliveryPOJO {

    private String dlvNumber;
    private String truckType;
    private String dock;
    //private Map<DeliveryItemsPOJO, List<Map<String, String>>> dlvItems;
    private List<DeliveryItemsPOJO> dlvItems;
}
