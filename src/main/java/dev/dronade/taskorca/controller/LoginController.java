package dev.dronade.taskorca.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dev.dronade.taskorca.TaskOrcaApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {


    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Hyperlink loginSignUpLink;

    @FXML
    private Button loginButton;

    @FXML
    void initialize() {

        String LoginUsername = loginUsername.getText().trim();
        String LoginPassword = loginPassword.getText().trim();

        loginSignUpLink.setOnAction(event ->{
            loginSignUpLink.getScene().getWindow().hide();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrcaApplication.class.getResource("SignUpView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - Sign Up");
            stage.showAndWait();
        });

        loginButton.setOnAction(event -> {
            if (!LoginUsername.equals("") && (!LoginPassword.equals(""))){
                loginUser(LoginUsername, LoginPassword);
            } else {
                System.out.println("username & password cannot be empty");
            }
        });
    }

    private void loginUser(String username, String password){
        // need to do
    }
}
