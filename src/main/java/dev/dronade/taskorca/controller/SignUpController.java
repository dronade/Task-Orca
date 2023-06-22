package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrca;
import dev.dronade.taskorca.database.UsersDatabase;
import dev.dronade.taskorca.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Emily Canto
 *  This Class controls the 'SignUpView'
 *  This class deals with the data entry of creating a user.
 */

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
            Stage stage = (Stage) signUpLoginLink.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("LoginView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - Log In");
        });


        signUpButton.setOnAction(event -> {
            createUser();
        });
    }

    private void createUser(){
        UsersDatabase usersDatabase = new UsersDatabase();

        String username = signUpUsername.getText();
        String password = signUpPassword.getText();

        User user = new User(username, password);

        try {
            usersDatabase.signUpUser(user);
            System.out.println("user has been signed up successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
