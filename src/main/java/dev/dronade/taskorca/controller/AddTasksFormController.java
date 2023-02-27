package dev.dronade.taskorca.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddTasksFormController {

    @FXML
    private TextField TaskTitleInput;

    @FXML
    private TextField AdditionalDetailsInput;

    @FXML
    private DatePicker DueDateInput;

    @FXML
    private Label TaskTitleLabel;

    @FXML
    private Label AdditionalDetailsLabel;

    @FXML
    private Label DueDateLabel;

    @FXML
    private Button CreateTaskButton;


    @FXML
    void initialize() {

    }
}