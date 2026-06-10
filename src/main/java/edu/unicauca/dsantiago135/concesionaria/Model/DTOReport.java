package edu.unicauca.dsantiago135.concesionaria.Model;

import java.util.List;
import lombok.Data;

@Data
public class DTOReport {

    private double attAvgSalary;
    private int attTotalEmployees;
    private int attEmployeesAboveAvg;
    private List<DTOEmployeeReport> attDetails;
}