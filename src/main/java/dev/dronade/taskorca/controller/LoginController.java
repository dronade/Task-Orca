package dev.dronade.taskorca.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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

/**
 * @author Emily Canto
 *  This Class controls the 'LoginView'
 *  It's mostly button events and detecting if a user is logged in or not.
 */

public class LoginController {
    private int userID;

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Hyperlink loginSignUpLink;

    @FXML
    private Button loginButton;

    private UsersDatabase usersDatabase;

    @FXML
    void initialize() {
        usersDatabase = new UsersDatabase();

        loginButton.setOnAction(actionEvent -> {
            String LoginUsername = loginUsername.getText().trim();
            String LoginPassword = loginPassword.getText().trim();

            User user = new User();
            user.setUsername(LoginUsername);
            user.setPassword(LoginPassword);

            ResultSet userRow = usersDatabase.getLoggedUser(user);
            int counter = 0;

            try {
                while (userRow.next()){
                    counter ++;
                    userID = userRow.getInt("user_id");
                }
                if (counter == 1){
                    showAddTasks();
                    System.out.println("user is logged in");
                }
            } catch (SQLException exception){
                exception.printStackTrace();
            }
        });

        loginSignUpLink.setOnAction(event ->{
            Stage stage = (Stage) loginSignUpLink.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("SignUpView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - Sign Up");
        });
    }

    private void showAddTasks(){
        Stage stage = (Stage) loginSignUpLink.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("AddTasksView.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 700, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(scene);
        stage.setTitle("Task Orca - Add Tasks");

        AddTasksController addTasksController = fxmlLoader.getController();
        addTasksController.setUserID(userID);
    }
}
