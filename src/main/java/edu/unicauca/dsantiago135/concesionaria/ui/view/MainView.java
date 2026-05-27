package edu.unicauca.dsantiago135.concesionaria.ui.view;

import edu.unicauca.dsantiago135.concesionaria.Controller.clsController;
import edu.unicauca.dsantiago135.concesionaria.ui.Theme;
import edu.unicauca.dsantiago135.concesionaria.ui.service.SessionContext;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import edu.unicauca.dsantiago135.concesionaria.ui.view.panels.CustomerPanel;
import edu.unicauca.dsantiago135.concesionaria.ui.view.panels.DealershipPanel;
import edu.unicauca.dsantiago135.concesionaria.ui.view.panels.EmployeePanel;
import edu.unicauca.dsantiago135.concesionaria.ui.view.panels.SalePanel;
import edu.unicauca.dsantiago135.concesionaria.ui.view.panels.SalesGoalPanel;
import edu.unicauca.dsantiago135.concesionaria.ui.view.panels.UnitPanel;
import edu.unicauca.dsantiago135.concesionaria.ui.view.panels.VehiclePanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class MainView {

    private final BorderPane root = new BorderPane();

    public MainView(clsController controller, Runnable onLogout) {
        var user = SessionContext.getCurrentUser();

        Label header = new Label("Concesionaria — " + user.displayName()
                + " (" + user.role().getDisplayName() + ")");
        header.setStyle("-fx-text-fill: " + Theme.TEXT + "; -fx-font-size: 16px; -fx-font-weight: bold;");

        Label roleHint = UiKit.muted(SessionContext.isManager()
                ? "Acceso completo a todas las operaciones del sistema."
                : "Acceso de vendedor: ventas, clientes, consultas de inventario. Las operaciones administrativas están deshabilitadas.");

        var logoutBtn = UiKit.secondaryButton("Cerrar sesión");
        logoutBtn.setOnAction(e -> onLogout.run());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox topBar = new HBox(16, header, roleHint, spacer, logoutBtn);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(12, 16, 12, 16));
        topBar.setStyle("-fx-background-color: " + Theme.BG_PANEL + ";");

        TabPane tabs = new TabPane();
        tabs.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabs.setStyle("-fx-background-color: " + Theme.BG_DARK + ";");

        tabs.getTabs().add(createTab("Concesionarias", new DealershipPanel(controller).getContent()));
        tabs.getTabs().add(createTab("Empleados", new EmployeePanel(controller).getContent()));
        tabs.getTabs().add(createTab("Clientes", new CustomerPanel(controller).getContent()));
        tabs.getTabs().add(createTab("Vehículos", new VehiclePanel(controller).getContent()));
        tabs.getTabs().add(createTab("Unidades", new UnitPanel(controller).getContent()));
        tabs.getTabs().add(createTab("Ventas", new SalePanel(controller).getContent()));
        tabs.getTabs().add(createTab("Metas", new SalesGoalPanel(controller).getContent()));

        root.setTop(topBar);
        root.setCenter(tabs);
        UiKit.styleRoot(root);
    }

    private Tab createTab(String title, javafx.scene.Node content) {
        Tab tab = new Tab(title, UiKit.scrollable(content));
        tab.setStyle("-fx-background-color: " + Theme.BG_DARK + ";");
        return tab;
    }

    public BorderPane getRoot() {
        return root;
    }
}
