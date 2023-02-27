package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrcaApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddTasksController {

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
                        FXMLLoader.load((TaskOrcaApplication.class.getResource("AddTasksFormView.fxml")));
                GreyBox.getChildren().setAll(AddTaskPane);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }
}