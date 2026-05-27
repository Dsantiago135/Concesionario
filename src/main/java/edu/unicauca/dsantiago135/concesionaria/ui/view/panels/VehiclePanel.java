package edu.unicauca.dsantiago135.concesionaria.ui.view.panels;

import edu.unicauca.dsantiago135.concesionaria.Controller.clsController;
import edu.unicauca.dsantiago135.concesionaria.Model.clsVehicle;
import edu.unicauca.dsantiago135.concesionaria.ui.util.TableHelper;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class VehiclePanel {

    private final clsController controller;
    private final TableView<clsVehicle> table = TableHelper.create();

    public VehiclePanel(clsController controller) {
        this.controller = controller;
        table.getColumns().addAll(
                TableHelper.col("ID", v -> String.valueOf(v.getAttVehicleId())),
                TableHelper.col("Marca", clsVehicle::getAttBrand),
                TableHelper.col("Modelo", clsVehicle::getAttModel),
                TableHelper.col("Año", v -> String.valueOf(v.getAttYear())),
                TableHelper.col("Combustible", clsVehicle::getAttFuelType),
                TableHelper.col("Categoría", clsVehicle::getAttCategory),
                TableHelper.col("Estado", clsVehicle::getAttState));
    }

    public VBox getContent() {
        var id = UiKit.field("ID");
        var state = UiKit.field("Estado");
        var brand = UiKit.field("Marca");
        var model = UiKit.field("Modelo");
        var year = UiKit.field("Año");
        var bodyType = UiKit.field("Carrocería");
        var fuelType = UiKit.field("Combustible (electric/gasoline/hybrid)");
        var category = UiKit.field("Categoría (standard/luxury)");

        GridPane form = UiKit.formGrid(2);
        UiKit.addFormRow(form, 0, "ID", id);
        UiKit.addFormRow(form, 1, "Estado", state);
        UiKit.addFormRow(form, 2, "Marca", brand);
        UiKit.addFormRow(form, 3, "Modelo", model);
        UiKit.addFormRow(form, 4, "Año", year);
        UiKit.addFormRow(form, 5, "Carrocería", bodyType);
        UiKit.addFormRow(form, 6, "Combustible", fuelType);
        UiKit.addFormRow(form, 7, "Categoría", category);

        var registerBtn = UiKit.managerButton("Registrar (opRegisterVehicle)", () -> controller.opRegisterVehicle(
                UiKit.parseInt(id.getText(), "ID"),
                UiKit.requireNonBlank(state.getText(), "Estado"),
                UiKit.requireNonBlank(brand.getText(), "Marca"),
                UiKit.requireNonBlank(model.getText(), "Modelo"),
                UiKit.parseInt(year.getText(), "Año"),
                UiKit.requireNonBlank(bodyType.getText(), "Carrocería"),
                UiKit.requireNonBlank(fuelType.getText(), "Combustible"),
                UiKit.requireNonBlank(category.getText(), "Categoría")));

        var updateBtn = UiKit.managerButton("Actualizar (opUpdateVehicle)", () -> controller.opUpdateVehicle(
                UiKit.parseInt(id.getText(), "ID"),
                UiKit.emptyToNull(brand.getText()),
                UiKit.emptyToNull(model.getText()),
                UiKit.parseIntOrNull(year.getText()),
                UiKit.emptyToNull(bodyType.getText()),
                UiKit.emptyToNull(fuelType.getText()),
                UiKit.emptyToNull(category.getText())));

        var disableBtn = UiKit.managerButton("Inactivar (opDisableVehicle)", () ->
                controller.opDisableVehicle(UiKit.parseInt(id.getText(), "ID")));

        var listBtn = UiKit.secondaryButton("Listar (opGetAllVehicles)");
        listBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetAllVehicles())));

        VBox box = UiKit.panelVBox(
                UiKit.title("Vehículos (líneas)"),
                UiKit.muted("Región Vehicle — consulta disponible para todos; escritura solo gerente."),
                UiKit.section("Formulario", form),
                UiKit.buttonRow(registerBtn, updateBtn, disableBtn, listBtn),
                table);
        table.setPrefHeight(280);
        return box;
    }
}
