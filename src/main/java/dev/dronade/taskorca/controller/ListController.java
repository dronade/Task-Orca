package dev.dronade.taskorca.controller;


import dev.dronade.taskorca.TaskOrcaApplication;
import dev.dronade.taskorca.database.TaskDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

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

        FolderLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) FolderLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrcaApplication.class.getResource("FolderView.fxml"));
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

    private void loadData() {
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() throws Exception {
                databaseHandler = new TaskDatabase();
                ResultSet resultSet = databaseHandler.getTasksByUserID(AddTasksController.userID);
                System.out.println("task started");
                while (resultSet.next()) {
                    Task task = new Task();
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