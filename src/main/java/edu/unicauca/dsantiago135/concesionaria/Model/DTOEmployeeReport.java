package edu.unicauca.dsantiago135.concesionaria.Model;

import lombok.Data;

@Data
public class DTOEmployeeReport {

    private int attEmployeeId;
    private String attName;
    private String attRole;
    private String attDealershipName;
    private double attSalary;
    private double attAvgSalary;
    private double attDifferenceFromAvg;
    private double attPercentAboveAvg;
}