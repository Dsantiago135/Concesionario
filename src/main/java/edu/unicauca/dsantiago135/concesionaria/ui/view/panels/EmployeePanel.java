package edu.unicauca.dsantiago135.concesionaria.ui.view.panels;

import edu.unicauca.dsantiago135.concesionaria.Controller.clsController;
import edu.unicauca.dsantiago135.concesionaria.Model.clsEmployee;
import edu.unicauca.dsantiago135.concesionaria.ui.util.TableHelper;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EmployeePanel {

    private final clsController controller;
    private final TableView<clsEmployee> table = TableHelper.create();

    public EmployeePanel(clsController controller) {
        this.controller = controller;
        table.getColumns().addAll(
                TableHelper.col("ID", e -> String.valueOf(e.getAttEmployeeId())),
                TableHelper.col("Nombre", clsEmployee::getAttName),
                TableHelper.col("Rol", clsEmployee::getAttRole),
                TableHelper.col("Estado", clsEmployee::getAttState),
                TableHelper.col("Salario", e -> String.valueOf(e.getAttSalary())),
                TableHelper.col("Teléfono", clsEmployee::getAttPhone));
    }

    public VBox getContent() {
        var id = UiKit.field("ID empleado");
        var dealershipId = UiKit.field("ID concesionaria");
        var name = UiKit.field("Nombre");
        var phone = UiKit.field("Teléfono");
        var salary = UiKit.field("Salario");
        //var role = UiKit.field("Rol (seller/manager)");
        ComboBox<String> role = new ComboBox<>();
        role.getItems().setAll("seller","manager");
        role.setValue("seller");
        //var state = UiKit.field("Estado (active/inactive)");
        ComboBox<String> state = new ComboBox<>();
        state.getItems().addAll("active","inactive");
        state.setValue("active");
        var hireDate = UiKit.datePicker("Fecha contratación");
        
        
        Runnable clearForm = () -> { 
            id.clear();
            dealershipId.clear();
            name.clear();
            phone.clear();
            salary.clear();
            role.setValue("seller");
            state.setValue("active");
            hireDate.setValue(null);
        };
        
        var clearBtn = UiKit.secondaryButton("Limpiar");
        clearBtn.setOnAction(e -> clearForm.run());

        GridPane form = UiKit.formGrid(2);
        UiKit.addFormRow(form, 0, "ID empleado", id);
        UiKit.addFormRow(form, 1, "ID concesionaria", dealershipId);
        UiKit.addFormRow(form, 2, "Nombre", name);
        UiKit.addFormRow(form, 3, "Teléfono", phone);
        UiKit.addFormRow(form, 4, "Salario", salary);
        UiKit.addFormRow(form, 5, "Rol", role);
        UiKit.addFormRow(form, 6, "Estado", state);
        UiKit.addFormRow(form, 7, "Contratación", hireDate);
        
        
        GridPane.setColumnSpan(clearBtn, 2);
        form.add(clearBtn, 0, 8);

        var registerBtn = UiKit.managerButton("Registrar (opRegisterEmployee)", () -> {
        	controller.opRegisterEmployee(
        			UiKit.parseInt(id.getText(), "ID"),
        			UiKit.parseInt(dealershipId.getText(), "ID concesionaria"),
        			UiKit.requireNonBlank(name.getText(), "Nombre"),
        			UiKit.requireNonBlank(phone.getText(), "Teléfono"),
        			UiKit.parseDouble(salary.getText(), "Salario"),
        			UiKit.toSqlDate(hireDate),
        			role.getValue(),
        			state.getValue());
        	clearForm.run();
        });

        var updateBtn = UiKit.managerButton("Actualizar (opUpdateEmployee)", () -> {
        	controller.opUpdateEmployee(
        			UiKit.parseInt(id.getText(), "ID"),
        			UiKit.emptyToNull(name.getText()),
        			UiKit.emptyToNull(phone.getText()),
        			UiKit.parseDoubleOrNull(salary.getText()));
        	clearForm.run();
        });

        var disableBtn = UiKit.managerButton("Inactivar (opDisableEmployee)", () ->
                controller.opDisableEmployee(UiKit.parseInt(id.getText(), "ID")));

        var getByIdBtn = UiKit.secondaryButton("Por ID (opGetEmployeeById)");
        getByIdBtn.setOnAction(e -> UiKit.run(() -> {
            clsEmployee emp = controller.opGetEmployeeById(UiKit.parseInt(id.getText(), "ID"));
            TableHelper.setItems(table, java.util.List.of(emp));
        }));

        var listAllBtn = UiKit.secondaryButton("Listar todos (opGetAllEmployees)");
        listAllBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetAllEmployees())));

        var byDealershipBtn = UiKit.secondaryButton("Por concesionaria (opGetEmployeesByDealership)");
        byDealershipBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetEmployeesByDealership(
                        UiKit.parseInt(dealershipId.getText(), "ID concesionaria")))));

        var aboveAvgBtn = UiKit.managerButton("Sobre promedio salarial (opGetEmployeesAboveAvgSalary)", () ->
                TableHelper.setItems(table, controller.opGetEmployeesAboveAvgSalary()));

        VBox box = UiKit.panelVBox(
                UiKit.title("Empleados"),
                UiKit.muted("Región Employee del controller."),
                UiKit.section("Formulario", form),
                UiKit.buttonRow(registerBtn, updateBtn, disableBtn, getByIdBtn, listAllBtn, byDealershipBtn, aboveAvgBtn),
                table);
        table.setPrefHeight(280);
        return box;
    }
}
