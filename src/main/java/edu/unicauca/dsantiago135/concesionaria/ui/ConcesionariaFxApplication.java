package edu.unicauca.dsantiago135.concesionaria.ui;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import edu.unicauca.dsantiago135.concesionaria.ConcesionariaApplication;
import edu.unicauca.dsantiago135.concesionaria.Controller.clsController;
import edu.unicauca.dsantiago135.concesionaria.ui.util.UiKit;
import edu.unicauca.dsantiago135.concesionaria.ui.view.LoginView;
import edu.unicauca.dsantiago135.concesionaria.ui.view.MainView;
import edu.unicauca.dsantiago135.concesionaria.ui.service.SessionContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class ConcesionariaFxApplication extends Application {

    private ConfigurableApplicationContext springContext;

    public static void launch(String[] args) {
        Application.launch(ConcesionariaFxApplication.class, args);
    }

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(ConcesionariaApplication.class)
                .headless(false)
                .run();
    }

    @Override
    public void start(Stage stage) {
        clsController controller = springContext.getBean(clsController.class);
        showLogin(stage, controller);
        stage.setMinWidth(420);
        stage.setMinHeight(500);
        stage.show();
    }

    private void applyTheme(Scene scene) {
        var css = Objects.requireNonNull(
                getClass().getResource("/styles/dark-purple.css"),
                "No se encontró dark-purple.css");
        scene.getStylesheets().add(css.toExternalForm());
    }

    private void showLogin(Stage stage, clsController controller) {
        LoginView loginView = new LoginView(() -> showMain(stage, controller));
        Scene scene = new Scene(loginView.getRoot(), 480, 560);
        UiKit.styleRoot(scene.getRoot());
        applyTheme(scene);
        stage.setTitle("Concesionaria — Inicio de sesión");
        stage.setScene(scene);
    }

    private void showMain(Stage stage, clsController controller) {
        MainView mainView = new MainView(controller, () -> {
            SessionContext.clear();
            Platform.runLater(() -> showLogin(stage, controller));
        });
        Scene scene = new Scene(mainView.getRoot(), 1100, 720);
        UiKit.styleRoot(scene.getRoot());
        applyTheme(scene);
        stage.setTitle("Concesionaria — Panel de gestión");
        stage.setScene(scene);
        stage.setMaximized(true);
    }

    @Override
    public void stop() {
        if (springContext != null) {
            springContext.close();
        }
        Platform.exit();
    }
}
