package edu.unicauca.dsantiago135.concesionaria.ui.view.panels;

import edu.unicauca.dsantiago135.concesionaria.Controller.clsController;
import edu.unicauca.dsantiago135.concesionaria.Model.DTOEmployeeReport;
import edu.unicauca.dsantiago135.concesionaria.Model.DTOReport;
import edu.unicauca.dsantiago135.concesionaria.ui.Theme;
import edu.unicauca.dsantiago135.concesionaria.ui.util.TableHelper;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReportsPanel {

    private final clsController controller;
    private final TableView<DTOEmployeeReport> table = TableHelper.create();
    private final Label summaryLabel = UiKit.muted("Presione el botón para generar el reporte.");

    public ReportsPanel(clsController controller) {
        this.controller = controller;
        table.getColumns().addAll(
                TableHelper.col("ID", r -> String.valueOf(r.getAttEmployeeId())),
                TableHelper.col("Nombre", DTOEmployeeReport::getAttName),
                TableHelper.col("Rol", DTOEmployeeReport::getAttRole),
                TableHelper.col("Concesionaria", DTOEmployeeReport::getAttDealershipName),
                TableHelper.col("Salario", r -> String.format("%.2f", r.getAttSalary())),
                TableHelper.col("Promedio", r -> String.format("%.2f", r.getAttAvgSalary())),
                TableHelper.col("Diferencia", r -> String.format("%.2f", r.getAttDifferenceFromAvg())),
                TableHelper.col("% sobre prom.", r -> String.format("%.1f%%", r.getAttPercentAboveAvg())));
    }

    public VBox getContent() {
        var generateBtn = UiKit.managerButton("Generar reporte (opGeneratePerformanceReport)", () -> {
            DTOReport report = controller.opGeneratePerformanceReport();
            TableHelper.setItems(table, report.getAttDetails());
            summaryLabel.setText(String.format(
                    "Promedio salarial: $%.2f  |  Total empleados: %d  |  Sobre promedio: %d",
                    report.getAttAvgSalary(),
                    report.getAttTotalEmployees(),
                    report.getAttEmployeesAboveAvg()));
            summaryLabel.setStyle("-fx-text-fill: " + Theme.ACCENT_SOFT + "; -fx-font-size: 12px;");
        });

        HBox summaryBox = new HBox(summaryLabel);

        VBox box = UiKit.panelVBox(
                UiKit.title("Reportes"),
                UiKit.muted("Reporte de rendimiento salarial basado en empleados con salario superior al promedio (opGetEmployeesAboveAvg)."),
                summaryBox,
                UiKit.buttonRow(generateBtn),
                table);
        table.setPrefHeight(320);
        return box;
    }
}
