package org.example.LogInSystem.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import org.example.LogInSystem.DAO.UserDao;
import org.example.LogInSystem.EXCEPTIONS.InputCantBeEmptyException;
import org.example.LogInSystem.Models.UserModel;
import org.example.LogInSystem.Utils.Utils;

import java.sql.SQLException;
import java.util.List;

public class LogInController{
    private Stage stage;

    @FXML private Button homepageButton;



    @FXML private TextField emailField;

    @FXML private PasswordField userPassword;

    @FXML private Button logInButton;



    public  LogInController(){}

    public  LogInController(Stage stage){
        this.stage = stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void goToUserPage(UserModel userModel) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/LogInSystem/FXML/homePage.fxml"));
             Parent homePage =  loader.load();
            HomePageController homePageController = loader.getController();
            homePageController.setStage(stage);
            homePageController.setUserData(userModel);
            stage.setScene(new Scene(homePage));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private boolean userVerification () throws InputCantBeEmptyException, SQLException {

        List<TextField> textList = List.of(userPassword,emailField);

        for(TextField textField  : textList ){
            if(textField.getText().trim().isEmpty()){
                throw new InputCantBeEmptyException("All Input Fields need to be filled out",textList);
            }
        }
        System.out.println("Logging in");

        String email =  emailField.getText();
        String password = userPassword.getText();
        UserDao userDao = new UserDao();
        try {
          return userDao.userVerification(email,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void logUserIn() throws SQLException {
        try{
            if(userVerification()){
                UserDao userDao = new UserDao();
              UserModel userModel = userDao.viewUser(emailField.getText());
                goToUserPage(userModel);
            }
        } catch (InputCantBeEmptyException e) {
            Utils.showErrorDialog("Inputs cannot be empty");
        }
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
}
