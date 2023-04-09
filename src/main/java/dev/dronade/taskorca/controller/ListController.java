package dev.dronade.taskorca.controller;


import com.jfoenix.controls.JFXListView;
import dev.dronade.taskorca.database.TaskDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListController {

    @FXML
    private AnchorPane GreyBox;

    @FXML
    private JFXListView<Task> TaskListView;

    @FXML
    private Label ListLabel;

    @FXML
    private Label FolderLabel;

    @FXML
    private Label SettingsLabel;

    private ObservableList<Task> tasks;
    private ObservableList<Task> refreshedTasks;



    @FXML
    void initialize() throws SQLException {
        tasks = FXCollections.observableArrayList();

        //TaskListView.setItems(tasks);
        //TaskListView.setOnMouseClicked(event -> System.out.println(TaskListView.getSelectionModel().getSelectedItem()));
        TaskListView.setCellFactory(TaskCellController -> new TaskCellController());

    }


}