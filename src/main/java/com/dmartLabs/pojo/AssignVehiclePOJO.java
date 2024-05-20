package com.dmartLabs.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AssignVehiclePOJO {

    private String dlvNumber;
    private String dock;
    private List<VehiclesPOJO> vehicles;

}
