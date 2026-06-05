package edu.unicauca.dsantiago135.concesionaria.ui.view.panels;

import edu.unicauca.dsantiago135.concesionaria.Controller.clsController;
import edu.unicauca.dsantiago135.concesionaria.Model.clsSale;
import edu.unicauca.dsantiago135.concesionaria.ui.service.SessionContext;
import edu.unicauca.dsantiago135.concesionaria.ui.util.TableHelper;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SalePanel {

    private final clsController controller;
    private final TableView<clsSale> table = TableHelper.create();

    public SalePanel(clsController controller) {
        this.controller = controller;
        table.getColumns().addAll(
                TableHelper.col("ID", s -> String.valueOf(s.getAttSaleId())),
                TableHelper.col("Estado", clsSale::getAttStatus),
                TableHelper.col("Precio", s -> String.valueOf(s.getAttPrice())),
                TableHelper.col("Cliente", s -> s.getAttCustomer() != null ? s.getAttCustomer().getAttName() : ""),
                TableHelper.col("Empleado", s -> s.getAttEmployee() != null ? s.getAttEmployee().getAttName() : ""),
                TableHelper.col("Placa", s -> s.getAttUnit() != null ? s.getAttUnit().getAttLicensePlate() : ""));
    }

    public VBox getContent() {
        var saleId = UiKit.field("ID venta/reserva");
        var customerId = UiKit.field("ID cliente");
        var employeeId = UiKit.field("ID empleado (vacío = sesión actual)");
        var unitId = UiKit.field("ID unidad");
        var price = UiKit.field("Precio");
        //var statusFilter = UiKit.field("Estado filtro (confirmed/cancelled/inprogress)");
        ComboBox<String> statusFilter = new ComboBox<>();
        statusFilter.getItems().addAll("confirmed","cancelled","inprogress");
        statusFilter.setValue("confirmed");
        var dateEnd = UiKit.datePicker("Fecha fin reserva");
        
        
        Runnable clearForm = () ->  {
            saleId.clear();
            customerId.clear();
            employeeId.clear();
            unitId.clear();
            price.clear();
            statusFilter.setValue("new");
            dateEnd.setValue(null);
        };

        if (!SessionContext.isManager()) {
            employeeId.setText(String.valueOf(SessionContext.getCurrentUser().employeeId()));
            employeeId.setEditable(false);
        }
        
        var clearBtn = UiKit.secondaryButton("Limpiar");
        clearBtn.setOnAction(e -> clearForm.run());

        GridPane form = UiKit.formGrid(2);
        UiKit.addFormRow(form, 0, "ID venta", saleId);
        UiKit.addFormRow(form, 1, "ID cliente", customerId);
        UiKit.addFormRow(form, 2, "ID empleado", employeeId);
        UiKit.addFormRow(form, 3, "ID unidad", unitId);
        UiKit.addFormRow(form, 4, "Precio", price);
        UiKit.addFormRow(form, 5, "Estado filtro", statusFilter);
        UiKit.addFormRow(form, 6, "Fin reserva", dateEnd);
        
        GridPane.setColumnSpan(clearBtn, 2);
        form.add(clearBtn, 0, 7);

        var registerSaleBtn = UiKit.primaryButton("Venta directa (opRegisterSale)");
        registerSaleBtn.setOnAction(e -> UiKit.run(() -> {
        	controller.opRegisterSale(
        			UiKit.parseInt(customerId.getText(), "ID cliente"),
        			resolveEmployeeId(employeeId),
        			UiKit.parseInt(unitId.getText(), "ID unidad"),
        			UiKit.parseDouble(price.getText(), "Precio"));
        	clearForm.run();
        }));

        var registerResBtn = UiKit.primaryButton("Reservar (opRegisterReservation)");
        registerResBtn.setOnAction(e -> UiKit.run(() -> {
        	controller.opRegisterReservation(
        			UiKit.parseInt(customerId.getText(), "ID cliente"),
        			resolveEmployeeId(employeeId),
        			UiKit.parseInt(unitId.getText(), "ID unidad"),
        			UiKit.parseDouble(price.getText(), "Precio"),
        			UiKit.toSqlDate(dateEnd));
        	clearForm.run();
        }));

        var completeBtn = UiKit.primaryButton("Completar reserva (opCompleteReservation)");
        completeBtn.setOnAction(e -> UiKit.run(() ->
                controller.opCompleteReservation(UiKit.parseInt(saleId.getText(), "ID venta"))));

        var cancelBtn = UiKit.primaryButton("Cancelar reserva (opCancelReservation)");
        cancelBtn.setOnAction(e -> UiKit.run(() ->
                controller.opCancelReservation(UiKit.parseInt(saleId.getText(), "ID venta"))));

        var getByIdBtn = UiKit.secondaryButton("Por ID (opGetSaleById)");
        getByIdBtn.setOnAction(e -> UiKit.run(() -> {
            clsSale s = controller.opGetSaleById(UiKit.parseInt(saleId.getText(), "ID"));
            TableHelper.setItems(table, java.util.List.of(s));
        }));

        var listAllBtn = UiKit.secondaryButton("Todas (opGetAllSales)");
        listAllBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetAllSales())));

        var byStatusBtn = UiKit.secondaryButton("Por estado (opGetSalesByStatus)");
        byStatusBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetSalesByStatus(
                        statusFilter.getValue()))));

        VBox box = UiKit.panelVBox(
                UiKit.title("Ventas y reservas"),
                UiKit.muted("Región Sale — operaciones principales del vendedor."),
                UiKit.section("Formulario", form),
                UiKit.buttonRow(registerSaleBtn, registerResBtn, completeBtn, cancelBtn,
                        getByIdBtn, listAllBtn, byStatusBtn),
                table);
        table.setPrefHeight(280);
        return box;
    }

    private int resolveEmployeeId(javafx.scene.control.TextField employeeId) {
        String text = employeeId.getText();
        if (text == null || text.isBlank()) {
            return SessionContext.getCurrentUser().employeeId();
        }
        return UiKit.parseInt(text, "ID empleado");
    }
}
