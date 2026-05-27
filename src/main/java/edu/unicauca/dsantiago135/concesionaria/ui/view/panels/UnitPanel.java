package edu.unicauca.dsantiago135.concesionaria.ui.view.panels;

import edu.unicauca.dsantiago135.concesionaria.Controller.clsController;
import edu.unicauca.dsantiago135.concesionaria.Model.clsUnit;
import edu.unicauca.dsantiago135.concesionaria.ui.util.TableHelper;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class UnitPanel {

    private final clsController controller;
    private final TableView<clsUnit> table = TableHelper.create();

    public UnitPanel(clsController controller) {
        this.controller = controller;
        table.getColumns().addAll(
                TableHelper.col("ID", u -> String.valueOf(u.getAttUnitId())),
                TableHelper.col("Placa", clsUnit::getAttLicensePlate),
                TableHelper.col("Color", clsUnit::getAttColor),
                TableHelper.col("Estado", clsUnit::getAttStatus),
                TableHelper.col("Condición", clsUnit::getAttCondition),
                TableHelper.col("Km", u -> String.valueOf(u.getAttMileage())));
    }

    public VBox getContent() {
        var id = UiKit.field("ID unidad");
        var vehicleId = UiKit.field("ID vehículo");
        var dealershipId = UiKit.field("ID concesionaria");
        var plate = UiKit.field("Placa (AAA000)");
        var color = UiKit.field("Color");
        var mileage = UiKit.field("Kilometraje");
        var condition = UiKit.field("Condición (new/used)");
        var status = UiKit.field("Estado (available/reserved/sold)");
        var dateEntry = UiKit.datePicker("Fecha ingreso");

        GridPane form = UiKit.formGrid(2);
        UiKit.addFormRow(form, 0, "ID unidad", id);
        UiKit.addFormRow(form, 1, "ID vehículo", vehicleId);
        UiKit.addFormRow(form, 2, "ID concesionaria", dealershipId);
        UiKit.addFormRow(form, 3, "Placa", plate);
        UiKit.addFormRow(form, 4, "Color", color);
        UiKit.addFormRow(form, 5, "Kilometraje", mileage);
        UiKit.addFormRow(form, 6, "Condición", condition);
        UiKit.addFormRow(form, 7, "Estado", status);
        UiKit.addFormRow(form, 8, "Fecha ingreso", dateEntry);

        var registerBtn = UiKit.managerButton("Registrar (opRegisterUnit)", () -> controller.opRegisterUnit(
                UiKit.parseInt(id.getText(), "ID unidad"),
                UiKit.parseInt(vehicleId.getText(), "ID vehículo"),
                UiKit.parseInt(dealershipId.getText(), "ID concesionaria"),
                UiKit.requireNonBlank(plate.getText(), "Placa"),
                UiKit.requireNonBlank(color.getText(), "Color"),
                UiKit.parseInt(mileage.getText(), "Kilometraje"),
                UiKit.toSqlDate(dateEntry),
                UiKit.requireNonBlank(condition.getText(), "Condición")));

        var updateBtn = UiKit.managerButton("Actualizar (opUpdateUnit)", () -> controller.opUpdateUnit(
                UiKit.parseInt(id.getText(), "ID"),
                UiKit.emptyToNull(plate.getText()),
                UiKit.emptyToNull(color.getText()),
                UiKit.parseIntOrNull(mileage.getText()),
                UiKit.emptyToNull(condition.getText())));

        var updateStatusBtn = UiKit.primaryButton("Cambiar estado (opUpdateUnitStatus)");
        updateStatusBtn.setOnAction(e -> UiKit.run(() -> controller.opUpdateUnitStatus(
                UiKit.parseInt(id.getText(), "ID"),
                UiKit.requireNonBlank(status.getText(), "Estado"))));

        var getByIdBtn = UiKit.secondaryButton("Por ID (opGetUnitById)");
        getByIdBtn.setOnAction(e -> UiKit.run(() -> {
            clsUnit u = controller.opGetUnitById(UiKit.parseInt(id.getText(), "ID"));
            TableHelper.setItems(table, java.util.List.of(u));
        }));

        var listAllBtn = UiKit.secondaryButton("Todas (opGetAllUnits)");
        listAllBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetAllUnits())));

        var availableBtn = UiKit.secondaryButton("Disponibles (opGetAvailableUnits)");
        availableBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetAvailableUnits())));

        var reservedBtn = UiKit.secondaryButton("Reservadas (opGetReservedUnits)");
        reservedBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetReservedUnits())));

        var soldBtn = UiKit.secondaryButton("Vendidas (opGetSoldUnits)");
        soldBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetSoldUnits())));

        VBox box = UiKit.panelVBox(
                UiKit.title("Unidades (inventario)"),
                UiKit.muted("Región Unit del controller."),
                UiKit.section("Formulario", form),
                UiKit.buttonRow(registerBtn, updateBtn, updateStatusBtn, getByIdBtn,
                        listAllBtn, availableBtn, reservedBtn, soldBtn),
                table);
        table.setPrefHeight(280);
        return box;
    }
}
