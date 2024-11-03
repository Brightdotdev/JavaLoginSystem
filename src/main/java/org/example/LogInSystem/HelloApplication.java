package org.example.LogInSystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.LogInSystem.Controllers.HelloApplicationController;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        // Load Welcome.fxml and set the controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/LogInSystem/FXML/hello-view.fxml"));
        Parent root = loader.load();

        HelloApplicationController controller = loader.getController();

        controller.setStage(stage);
        // Set up the primary stage
        stage.setScene(new Scene(root));
        stage.setTitle("Welcome Page");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}