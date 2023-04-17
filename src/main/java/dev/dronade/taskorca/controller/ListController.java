package dev.dronade.taskorca.controller;


import dev.dronade.taskorca.database.TaskDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.application.Platform;
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

    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private ObservableList<Task> refreshedTasks;

    private TaskDatabase databaseHandler;

    public void initialize() {
        TaskListView.setItems(tasks);
        TaskListView.setCellFactory(TaskCellController -> new TaskCellController());
        loadData();
    }

    private void loadData() {
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() throws Exception {
                databaseHandler = new TaskDatabase();
                ResultSet resultSet = databaseHandler.getTasksByUserID(AddTasksController.userID);
                System.out.println("task started");
                while (resultSet.next()) {
                    Task task = new Task();
                    System.out.println(resultSet.getString("title"));
                    task.setTitle(resultSet.getString("title"));
                    task.setDetails(resultSet.getString("details"));
                    task.setDue_date(resultSet.getString("due_date"));
                    tasks.addAll(task);
                }
                return null;
            }
        };
        new Thread(task).start();
    }
}