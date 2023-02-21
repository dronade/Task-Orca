package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrcaApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField signUpUsername;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private Hyperlink signUpLoginLink;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {
        signUpLoginLink.setOnAction(event ->{
            signUpLoginLink.getScene().getWindow().hide();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrcaApplication.class.getResource("LoginView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - Log In");
            stage.showAndWait();
        });
    }

}
