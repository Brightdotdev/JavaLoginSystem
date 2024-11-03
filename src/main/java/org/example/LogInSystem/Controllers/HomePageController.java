package org.example.LogInSystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.LogInSystem.Models.UserModel;

public class HomePageController{

    @FXML private Text status;
    @FXML  private Text userName;
    @FXML  private Text email;
    @FXML  private Text password;
    @FXML private Button backToHomePageButton;
    private Stage stage;

    public HomePageController(){}

    public HomePageController(Stage stage){
        this.stage = stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }




    public void backToHomePage(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/LogInSystem/FXML/hello-view.fxml"));
            Parent homePage = loader.load();
            HelloApplicationController controller = loader.getController();
            controller.setStage(stage);
            stage.setScene(new Scene(homePage));
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Omo it's not going to the home page back",e);
        }
    }


    public void setUserData(UserModel userModel) {
        System.out.println(userModel);
        userName.setText("Hiii " + userModel.getUserName());
        email.setText("Email " + userModel.getEmail());
        password.setText("Password " + userModel.getPassword());
        status.setText("Current Status: User");
    }
}
