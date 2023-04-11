package dev.dronade.taskorca.controller;


import dev.dronade.taskorca.database.TaskDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListController {

    @FXML
    private AnchorPane GreyBox;

    @FXML
    private ListView<Task> TaskListView;

    @FXML
    private Label ListLabel;

    @FXML
    private Label FolderLabel;

    @FXML
    private Label SettingsLabel;

    private ObservableList<Task> tasks;
    private ObservableList<Task> refreshedTasks;

    private TaskDatabase databaseHandler;



    @FXML
    void initialize() throws SQLException {
        tasks = FXCollections.observableArrayList();


        databaseHandler = new TaskDatabase();
        ResultSet resultSet = databaseHandler.getTasksByUserID(AddTasksController.userID);

        while (resultSet.next()) {
            Task task = new Task();
            task.setTitle(resultSet.getString("title"));
            task.setDetails(resultSet.getString("details"));
            task.setDue_date(resultSet.getString("due_date"));
            tasks.addAll(task);

        }


        TaskListView.setItems(tasks);
        TaskListView.setCellFactory(TaskCellController -> new TaskCellController());

    }



}