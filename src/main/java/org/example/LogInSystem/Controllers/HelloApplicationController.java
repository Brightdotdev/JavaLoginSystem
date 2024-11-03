package org.example.LogInSystem.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
public class HelloApplicationController{


    @FXML private Label welcomeText;
    private Stage stage;
    public HelloApplicationController() {
        System.out.println("Yeah it's working");
    }

    public void initialize () {
        welcomeText.setText("Welcome will you like to log in or sign up as a new user");
    }
    public HelloApplicationController(Stage stage){
        this.stage = stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setWelcomeTextText(String welcomeText) {
        this.welcomeText.setText(welcomeText);
    }

    public void changeToLogin(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/LogInSystem/FXML/LogIn.fxml"));
            Parent logInRoot = loader.load();
            LogInController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(new Scene(logInRoot));
        }catch (Exception e){
           e.printStackTrace();
        }
    }

    public void changeToSignUp(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/LogInSystem/FXML/signUp.fxml"));
            Parent signUpRoot = loader.load();
            SignUpController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(new Scene(signUpRoot));

        }catch(Exception e){
            e.printStackTrace();
        }
    }


}
