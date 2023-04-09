package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrcaApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AddTasksController {
    //TODO: make naming scheme consistent
    public static int userID;

    @FXML
    private ImageView addTasksButton;

    @FXML
    private AnchorPane GreyBox;

    @FXML
    private Label NoTasksLabel;

    @FXML
    private Label AddTaskListLabel;

    @FXML
    private Label AddTaskFolderLabel;

    @FXML
    private Label AddTaskSettingsLabel;

    @FXML
    void initialize() {
        addTasksButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            addTasksButton.setOpacity(0);
            NoTasksLabel.setOpacity(0);

            try {
                AnchorPane AddTaskPane =
                        FXMLLoader.load((Objects.requireNonNull(TaskOrcaApplication.class.getResource("AddTasksFormView.fxml"))));

                AddTasksFormController addTasksFormController = new AddTasksFormController();
                addTasksFormController.setUserID(getUserID());

                GreyBox.getChildren().setAll(AddTaskPane);

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        AddTaskListLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            AddTaskListLabel.getScene().getWindow().hide();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrcaApplication.class.getResource("ListView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //scene.getStylesheets().add(TaskOrcaApplication.class.getResource("css/main.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Task Orca - List");
            stage.showAndWait();
        });
    }

    public void setUserID(int userID) {
        this.userID = userID;
        System.out.println("User Id is " + this.userID);

    }

    public int getUserID(){
        return this.userID;
    }
}