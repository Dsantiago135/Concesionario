package edu.unicauca.dsantiago135.concesionaria.ui.view.panels;

import edu.unicauca.dsantiago135.concesionaria.Controller.clsController;
import edu.unicauca.dsantiago135.concesionaria.Model.clsDealership;
import edu.unicauca.dsantiago135.concesionaria.ui.util.TableHelper;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DealershipPanel {

    private final clsController controller;
    private final TableView<clsDealership> table = TableHelper.create();

    public DealershipPanel(clsController controller) {
        this.controller = controller;
        table.getColumns().addAll(
                TableHelper.col("ID", d -> String.valueOf(d.getAttDealershipId())),
                TableHelper.col("Nombre", clsDealership::getAttName),
                TableHelper.col("Estado", clsDealership::getAttState),
                TableHelper.col("Dirección", clsDealership::getAttAddress),
                TableHelper.col("Teléfono", clsDealership::getAttPhone));
    }

    public VBox getContent() {
        var id = UiKit.field("ID");
        var name = UiKit.field("Nombre");
        //var state = UiKit.field("Estado (active/inactive)");
        ComboBox<String> state = new ComboBox<>();
        state.getItems().addAll("active","inactive");
        state.setValue("active");
        var address = UiKit.field("Dirección");
        var phone = UiKit.field("Teléfono");
        
        Runnable clearForm = () -> {
            id.clear();
            name.clear();
            address.clear();
            phone.clear();
            state.setValue("active");
        };
        
        var clearBtn = UiKit.secondaryButton("Limpiar");
        clearBtn.setOnAction(e -> clearForm.run());

        GridPane registerForm = UiKit.formGrid(2);
        UiKit.addFormRow(registerForm, 0, "ID", id);
        UiKit.addFormRow(registerForm, 1, "Nombre", name);
        UiKit.addFormRow(registerForm, 2, "Estado", state);
        UiKit.addFormRow(registerForm, 3, "Dirección", address);
        UiKit.addFormRow(registerForm, 4, "Teléfono", phone);


        GridPane.setColumnSpan(clearBtn, 2);
        registerForm.add(clearBtn, 0, 5);

        var registerBtn = UiKit.managerButton("Registrar (opRegisterDealership)", () -> {
        	controller.opRegisterDealership(
        			UiKit.parseInt(id.getText(), "ID"),
        			UiKit.requireNonBlank(name.getText(), "Nombre"),
        			state.getValue(),
        			UiKit.requireNonBlank(address.getText(), "Dirección"),
        			UiKit.requireNonBlank(phone.getText(), "Teléfono"));
        	clearForm.run();
        });
        
        

        var updateBtn = UiKit.managerButton("Actualizar (opUpdateDealership)", () -> {
        	controller.opUpdateDealership(
        			UiKit.parseInt(id.getText(), "ID"),
        			UiKit.emptyToNull(name.getText()),
        			UiKit.emptyToNull(address.getText()),
        			UiKit.emptyToNull(phone.getText()));
        	clearForm.run();
        });

        var disableBtn = UiKit.managerButton("Inactivar (opDisableDealership)", () ->
                controller.opDisableDealership(UiKit.parseInt(id.getText(), "ID")));

        var getByIdBtn = UiKit.secondaryButton("Buscar por ID (opGetDealershipById)");
        getByIdBtn.setOnAction(e -> UiKit.run(() -> {
            clsDealership d = controller.opGetDealershipById(UiKit.parseInt(id.getText(), "ID"));
            TableHelper.setItems(table, java.util.List.of(d));
        }));

        var listBtn = UiKit.secondaryButton("Listar todas (opGetAllDealerships)");
        listBtn.setOnAction(e -> UiKit.runSilent(() ->
                TableHelper.setItems(table, controller.opGetAllDealerships())));

        VBox box = UiKit.panelVBox(
                UiKit.title("Concesionarias"),
                UiKit.muted("Operaciones de la fachada clsController — región Dealership."),
                UiKit.section("Registrar / actualizar / inactivar", registerForm),
                UiKit.buttonRow(registerBtn, updateBtn, disableBtn, getByIdBtn, listBtn),
                table);
        table.setPrefHeight(280);
        return box;
    }
}
