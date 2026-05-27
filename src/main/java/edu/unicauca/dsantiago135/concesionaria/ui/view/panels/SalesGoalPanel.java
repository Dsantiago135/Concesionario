package edu.unicauca.dsantiago135.concesionaria.ui.view.panels;

import edu.unicauca.dsantiago135.concesionaria.Controller.clsController;
import edu.unicauca.dsantiago135.concesionaria.Model.clsSalesGoal;
import edu.unicauca.dsantiago135.concesionaria.ui.util.TableHelper;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SalesGoalPanel {

    private final clsController controller;
    private final TableView<clsSalesGoal> table = TableHelper.create();

    public SalesGoalPanel(clsController controller) {
        this.controller = controller;
        table.getColumns().addAll(
                TableHelper.col("ID", g -> String.valueOf(g.getAttSalesGoalId())),
                TableHelper.col("Tipo", clsSalesGoal::getAttGoalType),
                TableHelper.col("Objetivo", g -> String.valueOf(g.getAttTargetValue())),
                TableHelper.col("Estado", clsSalesGoal::getAttState),
                TableHelper.col("Inicio", g -> g.getAttStartDate() != null ? g.getAttStartDate().toString() : ""),
                TableHelper.col("Fin", g -> g.getAttEndDate() != null ? g.getAttEndDate().toString() : ""));
    }

    public VBox getContent() {
        var id = UiKit.field("ID meta");
        var dealershipId = UiKit.field("ID concesionaria");
        var employeeId = UiKit.field("ID empleado (vacío = meta concesionaria)");
        var goalType = UiKit.field("Tipo (monthly/quarterly/yearly)");
        var target = UiKit.field("Valor objetivo");
        var stateFilter = UiKit.field("Estado filtro (active/inactive/complete)");
        var startDate = UiKit.datePicker("Fecha inicio");

        GridPane form = UiKit.formGrid(2);
        UiKit.addFormRow(form, 0, "ID meta", id);
        UiKit.addFormRow(form, 1, "ID concesionaria", dealershipId);
        UiKit.addFormRow(form, 2, "ID empleado", employeeId);
        UiKit.addFormRow(form, 3, "Tipo meta", goalType);
        UiKit.addFormRow(form, 4, "Objetivo", target);
        UiKit.addFormRow(form, 5, "Estado filtro", stateFilter);
        UiKit.addFormRow(form, 6, "Inicio", startDate);

        var registerBtn = UiKit.managerButton("Registrar (opRegisterSalesGoal)", () -> controller.opRegisterSalesGoal(
                UiKit.parseInt(dealershipId.getText(), "ID concesionaria"),
                UiKit.parseIntOrNull(employeeId.getText()),
                UiKit.requireNonBlank(goalType.getText(), "Tipo"),
                UiKit.parseInt(target.getText(), "Objetivo"),
                UiKit.toSqlDate(startDate)));

        var updateBtn = UiKit.managerButton("Actualizar (opUpdateSalesGoal)", () -> controller.opUpdateSalesGoal(
                UiKit.parseInt(id.getText(), "ID"),
                UiKit.parseIntOrNull(target.getText()),
                UiKit.emptyToNull(goalType.getText())));

        var disableBtn = UiKit.managerButton("Inactivar (opDisableSalesGoal)", () ->
                controller.opDisableSalesGoal(UiKit.parseInt(id.getText(), "ID")));

        var completeBtn = UiKit.managerButton("Completar (opCompleteSalesGoal)", () ->
                controller.opCompleteSalesGoal(UiKit.parseInt(id.getText(), "ID")));

        var getByIdBtn = UiKit.secondaryButton("Por ID (opGetSalesGoalById)");
        getByIdBtn.setOnAction(e -> UiKit.run(() -> {
            clsSalesGoal g = controller.opGetSalesGoalById(UiKit.parseInt(id.getText(), "ID"));
            TableHelper.setItems(table, java.util.List.of(g));
        }));

        var listAllBtn = UiKit.secondaryButton("Todas (opGetAllSalesGoals)");
        listAllBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetAllSalesGoals())));

        var byStateBtn = UiKit.secondaryButton("Por estado (opGetSalesGoalsByState)");
        byStateBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetSalesGoalsByState(
                        UiKit.requireNonBlank(stateFilter.getText(), "Estado filtro")))));

        VBox box = UiKit.panelVBox(
                UiKit.title("Metas de ventas"),
                UiKit.muted("Región SalesGoal — gestión solo gerente; consultas para todos."),
                UiKit.section("Formulario", form),
                UiKit.buttonRow(registerBtn, updateBtn, disableBtn, completeBtn,
                        getByIdBtn, listAllBtn, byStateBtn),
                table);
        table.setPrefHeight(280);
        return box;
    }
}
