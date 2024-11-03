package org.example.LogInSystem.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.LogInSystem.DAO.UserDao;
import org.example.LogInSystem.EXCEPTIONS.InputCantBeEmptyException;
import org.example.LogInSystem.Models.UserModel;
import org.example.LogInSystem.Utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SignUpController{


   @FXML private Text checkBoxValidationText;
    private Stage stage;
    @FXML private CheckBox checkBoxValidation;
    @FXML private Button homepageButton;
    @FXML private Button submitButton;
    @FXML private PasswordField userPasswordValidation;
    @FXML private PasswordField userPassword;
    @FXML private TextField userNameField;
    @FXML private TextField emailField;




    public void goToHomePageToLogIn(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/LogInSystem/FXML/hello-view.fxml"));
            Parent homePage = loader.load();
            HelloApplicationController helloApplication = loader.getController();
            helloApplication.setStage(stage);
            helloApplication.setWelcomeTextText("Your Account has been created now you can log in");

            stage.setScene(new Scene(homePage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public  SignUpController (){}
   public  SignUpController (Stage stage){
        this.stage = stage;

    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }




    private boolean insertUserData() throws InputCantBeEmptyException {
    List<TextField> textList = List.of(userNameField,userPassword,userPasswordValidation,emailField);


    for(TextField textField  : textList ){
        if(textField.getText().trim().isEmpty()){
            throw new InputCantBeEmptyException("All Input Fields need to be filled out",textList);
        }
    }


     if (!Utils.isValidEmail(String.valueOf(emailField.getText()))) {
            emailField.setPromptText("Invalid email please input a valid email");
            emailField.setText("");
            return false;
        }
     if (!userPasswordValidation.getText().equalsIgnoreCase(userPassword.getText())) {
            userPasswordValidation.setPromptText("Invalid! password not equal");
            userPasswordValidation.setText("");
            return false;
        }
        if (!Utils.isStrongPassword(userPassword.getText())) {
            userPassword.setPromptText("Password not strong enough....we don't want hackers guessing your password");
            userPassword.setText("");
            userPasswordValidation.setText("");
            return false;
        }


        if (!checkBoxValidation.isSelected()) {
          //  String initialText  = checkBoxValidationText.getText();
            checkBoxValidationText.setStyle("-fx-text-fill: red");
            checkBoxValidationText.setText("The Checkbox must be clicked man");
            return false;
        }

                UserModel userModel = new UserModel();
                UserDao userDao;
                try {
                userModel.setUserName(userNameField.getText());
                userModel.setPassword(userPassword.getText());
                userModel.setEmail(emailField.getText());
                userDao = new UserDao();
                return userDao.addUser(userModel);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
    }

    public void signUp() throws InputCantBeEmptyException {
        try {

            boolean validation = insertUserData();
            if (validation) {
                goToHomePageToLogIn();
            }
        } catch (InputCantBeEmptyException e) {
           Utils.showErrorDialog("Input all Fields please");
        }catch (RuntimeException e){
            Utils.showErrorDialog(e.getMessage());
        }
    }


    public void backToHomePage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/LogInSystem/FXML/hello-view.fxml"));
            Parent homePage = loader.load();
            HelloApplicationController controller = loader.getController();

            controller.setStage(stage);
            stage.setScene(new Scene(homePage));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    }
