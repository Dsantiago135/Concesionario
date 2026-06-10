package edu.unicauca.dsantiago135.concesionaria.ui.view.panels;

import edu.unicauca.dsantiago135.concesionaria.Controller.clsController;
import edu.unicauca.dsantiago135.concesionaria.Model.clsUnit;
import edu.unicauca.dsantiago135.concesionaria.ui.util.TableHelper;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit.EntityOption;
import javafx.scene.control.ComboBox;
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
        ComboBox<EntityOption> vehicleId = UiKit.entityCombo("Seleccione vehículo");
        ComboBox<EntityOption> dealershipId = UiKit.entityCombo("Seleccione concesionaria");
        var plate = UiKit.field("Placa (AAA000)");
        var color = UiKit.field("Color");
        var mileage = UiKit.field("Kilometraje");
        //var condition = UiKit.field("Condición (new/used)");
        ComboBox<String> condition = new ComboBox<>();
        condition.getItems().addAll("new","used");
        condition.setValue("new");
        //var status = UiKit.field("Estado (available/reserved/sold)");
        ComboBox<String> status = new ComboBox<>();
        status.getItems().addAll("available","reserved","sold");
        status.setValue("available");
        var dateEntry = UiKit.datePicker("Fecha ingreso");

        UiKit.runSilent(() -> {
            UiKit.populateEntityCombo(vehicleId, controller.opGetAllVehicles().stream()
                    .filter(v -> "active".equalsIgnoreCase(v.getAttState()))
                    .map(v -> new EntityOption(v.getAttVehicleId(),
                            v.getAttBrand() + " " + v.getAttModel() + " (" + v.getAttYear() + ")"))
                    .toList());
            UiKit.populateEntityCombo(dealershipId, controller.opGetAllDealerships().stream()
                    .filter(d -> "active".equalsIgnoreCase(d.getAttState()))
                    .map(d -> new EntityOption(d.getAttDealershipId(), d.getAttName()))
                    .toList());
        });

        Runnable clearForm = () -> {
            id.clear();
            vehicleId.setValue(null);
            dealershipId.setValue(null);
            plate.clear();
            color.clear();
            mileage.clear();
            condition.setValue("new");
            status.setValue("available");
        };
        
        var clearBtn = UiKit.secondaryButton("Limpiar");
        clearBtn.setOnAction(e -> clearForm.run());

        GridPane form = UiKit.formGrid(2);
        UiKit.addFormRow(form, 0, "ID unidad", id);
        UiKit.addFormRow(form, 1, "Vehículo", vehicleId);
        UiKit.addFormRow(form, 2, "Concesionaria", dealershipId);
        UiKit.addFormRow(form, 3, "Placa", plate);
        UiKit.addFormRow(form, 4, "Color", color);
        UiKit.addFormRow(form, 5, "Kilometraje", mileage);
        UiKit.addFormRow(form, 6, "Condición", condition);
        UiKit.addFormRow(form, 7, "Estado", status);
        UiKit.addFormRow(form, 8, "Fecha ingreso", dateEntry);
        
        
        GridPane.setColumnSpan(clearBtn, 2);
        form.add(clearBtn, 0, 9);

        var registerBtn = UiKit.managerButton("Registrar (opRegisterUnit)", () -> {
        	controller.opRegisterUnit(
        			UiKit.parseInt(id.getText(), "ID unidad"),
        			UiKit.requireSelectedId(vehicleId, "vehículo"),
        			UiKit.requireSelectedId(dealershipId, "concesionaria"),
        			UiKit.requireNonBlank(plate.getText(), "Placa"),
        			UiKit.requireNonBlank(color.getText(), "Color"),
        			UiKit.parseInt(mileage.getText(), "Kilometraje"),
        			UiKit.toSqlDate(dateEntry),
        			condition.getValue());
        	clearForm.run();
        });

        var updateBtn = UiKit.managerButton("Actualizar (opUpdateUnit)", () -> {
        	controller.opUpdateUnit(
        			UiKit.parseInt(id.getText(), "ID"),
        			UiKit.emptyToNull(plate.getText()),
        			UiKit.emptyToNull(color.getText()),
        			UiKit.parseIntOrNull(mileage.getText()),
        			condition.getValue());
        	clearForm.run();
        });

        var updateStatusBtn = UiKit.primaryButton("Cambiar estado (opUpdateUnitStatus)");
        updateStatusBtn.setOnAction(e -> UiKit.run(() -> {
        	controller.opUpdateUnitStatus(
        			UiKit.parseInt(id.getText(), "ID"),
        			status.getValue());
        	clearForm.run();
        }));

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
