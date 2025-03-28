package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrca;
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

/**
 * @author Emily Canto
 *  This Class controls the 'AddTaskView'
 *  It mostly consists of button event handlers to change to various scenes.
 */

public class AddTasksController {
    public static int userID;

    @FXML
    private ImageView addTasksButton;

    @FXML
    private AnchorPane GreyBox;

    @FXML
    private Label AddTasksLabel;

    @FXML
    private Label ListLabel;

    @FXML
    private Label FolderLabel;

    @FXML
    private Label CalendarLabel;

    @FXML
    void initialize() {
        addTasksButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            addTasksButton.setOpacity(0);
            AddTasksLabel.setOpacity(0);

            try {
                AnchorPane AddTaskPane =
                        FXMLLoader.load((Objects.requireNonNull(TaskOrca.class.getResource("AddTasksFormView.fxml"))));

                AddTasksFormController addTasksFormController = new AddTasksFormController();
                addTasksFormController.setUserID(getUserID());

                GreyBox.getChildren().setAll(AddTaskPane);

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        ListLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) ListLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("ListView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - List");
        });

        FolderLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) FolderLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("FolderView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - Folders");
        });

        CalendarLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) CalendarLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("CalendarView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - Folders");
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