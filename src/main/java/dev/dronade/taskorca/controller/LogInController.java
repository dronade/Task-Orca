package dev.dronade.taskorca.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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

        loginButton.setOnAction(event -> {
            System.out.println("wow it works!");
        });
    }
}
