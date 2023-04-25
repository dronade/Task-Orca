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
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class ListController {

    @FXML
    public MenuItem FilterDueDate;

    @FXML
    public MenuItem FilterCreationDate;

    @FXML
    private ListView<Task> TaskListView;

    @FXML
    private Label FolderLabel;

    @FXML
    private Label AddTasksLabelList;

    @FXML
    private ImageView AddTasksButtonList;

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

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

        AddTasksButtonList.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) AddTasksButtonList.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrcaApplication.class.getResource("AddTasksView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - Add Tasks");
        });

        AddTasksLabelList.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) AddTasksLabelList.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrcaApplication.class.getResource("AddTasksView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - Add Tasks");
        });

        FilterDueDate.setOnAction(actionEvent -> {
            System.out.println("filter due date clicked");
            tasks.sort(new SortByDueDate());
        });

        FilterCreationDate.setOnAction(actionEvent -> {
            System.out.println("filter creation date clicked");
            tasks.sort(new SortByCreationDate());
        });
    }

    private void loadData() {
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() throws Exception {
                databaseHandler = new TaskDatabase();
                ResultSet resultSet = databaseHandler.getTasksByUserID(AddTasksController.userID);
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTaskID(resultSet.getInt("rowid"));
                    task.setTitle(resultSet.getString("title"));
                    task.setDetails(resultSet.getString("details"));
                    task.setDue_date(resultSet.getString("due_date"));
                    task.setCreated_at(new Timestamp(Long.parseLong(resultSet.getString("created_at"))));
                    tasks.addAll(task);
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    static class SortByDueDate implements Comparator<Task> {
        private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public int compare(Task a, Task b) {
            String aStringDate = a.getDue_date();
            String bStringDate = b.getDue_date();
            Date aDate = new Date();
            Date bDate = new Date();
            try {
                aDate = formatter.parse(aStringDate);
                bDate = formatter.parse(bStringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (aDate.after(bDate)) {
                return 1;
            } else if (aDate.equals(bDate)) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    static class SortByCreationDate implements Comparator<Task> {
        @Override
        public int compare(Task a, Task b) {
            Timestamp aDate = a.getCreated_at();
            Timestamp bDate = b.getCreated_at();
            if (aDate.after(bDate)) {
                return 1;
            } else if (aDate.equals(bDate)) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}