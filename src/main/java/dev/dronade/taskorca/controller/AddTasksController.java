package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrcaApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class AddTasksController {
    public static int userId;

    @FXML
    private ImageView addTasksButton;

    @FXML
    private AnchorPane GreyBox;

    @FXML
    private Label NoTasksLabel;

    @FXML
    void initialize() {
        addTasksButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            addTasksButton.setOpacity(0);
            NoTasksLabel.setOpacity(0);

            try {
                AnchorPane AddTaskPane =
                        FXMLLoader.load((Objects.requireNonNull(TaskOrcaApplication.class.getResource("AddTasksFormView.fxml"))));
                GreyBox.getChildren().setAll(AddTaskPane);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }

    public void setUserId(int userId) {

        this.userId = userId;
        System.out.println("User Id is " + this.userId);

    }

    public int getUserId(){
        return this.userId;
    }
}