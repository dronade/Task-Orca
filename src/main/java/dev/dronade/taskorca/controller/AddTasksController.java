package dev.dronade.taskorca.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class AddTasksController {

    @FXML
    private ImageView addTasksButton;

    @FXML
    private Label NoTasksLabel;

    @FXML
    void initialize() {
        addTasksButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

        });
    }
}