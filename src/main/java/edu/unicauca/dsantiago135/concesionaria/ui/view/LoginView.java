package edu.unicauca.dsantiago135.concesionaria.ui.view;

import edu.unicauca.dsantiago135.concesionaria.ui.Theme;
import edu.unicauca.dsantiago135.concesionaria.ui.model.AppUser;
import edu.unicauca.dsantiago135.concesionaria.ui.service.AuthService;
import edu.unicauca.dsantiago135.concesionaria.ui.service.SessionContext;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LoginView {

    private final BorderPane root = new BorderPane();

    public LoginView(Runnable onSuccess) {
        AuthService authService = new AuthService();

        VBox card = UiKit.panelVBox();
        card.setMaxWidth(400);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(32));

        Label brand = new Label("CONCESIONARIA");
        brand.setStyle(
                "-fx-text-fill: " + Theme.ACCENT + "; -fx-font-size: 28px; -fx-font-weight: bold;");

        Label subtitle = new Label("Sistema de gestión");
        subtitle.setStyle("-fx-text-fill: " + Theme.TEXT_MUTED + "; -fx-font-size: 14px;");

        var username = UiKit.field("Usuario");
        var password = new PasswordField();
        password.setPromptText("Contraseña");
        password.setStyle(
                "-fx-background-color: " + Theme.BG_INPUT + "; -fx-text-fill: " + Theme.TEXT
                        + "; -fx-prompt-text-fill: " + Theme.TEXT_MUTED + "; -fx-background-radius: 6;");

        Label hint = UiKit.muted(
                "Usuarios de prueba:\n"
                        + "• Gerente → gerente / manager123\n"
                        + "• Vendedor → vendedor / seller123");

        var loginBtn = UiKit.primaryButton("Ingresar");
        loginBtn.setMaxWidth(Double.MAX_VALUE);
        loginBtn.setOnAction(e -> {
            var user = authService.authenticate(username.getText(), password.getText());
            if (user.isEmpty()) {
                UiKit.showError("Acceso denegado", "Usuario o contraseña incorrectos.");
                return;
            }
            AppUser appUser = user.get();
            SessionContext.setCurrentUser(appUser);
            onSuccess.run();
        });

        password.setOnAction(e -> loginBtn.fire());

        card.getChildren().addAll(brand, subtitle, username, password, loginBtn, hint);

        BorderPane.setAlignment(card, Pos.CENTER);
        root.setCenter(card);
        root.setPadding(new Insets(24));
        UiKit.styleRoot(root);
    }

    public BorderPane getRoot() {
        return root;
    }
}
