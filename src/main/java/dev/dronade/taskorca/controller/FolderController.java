package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrca;
import dev.dronade.taskorca.database.TasksDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

/**
 * @author Emily Canto
 *  This Class controls the 'FolderView'
 *  It needs to be refactored heavily (future work) but essentially deals with input to the tree structure list
 */

public class FolderController {

    @FXML
    private Label ListLabel;

    @FXML
    private Label AddTasksLabelFolder;

    @FXML
    private ImageView AddTasksButtonFolder;

    @FXML
    private TreeView<String> TaskTreeView;

    private String folderName1 = "Study";
    private String folderName2 = "Work";
    private String folderName3 = "Chores";
    private String folderName4 = "Appointments";
    private String folderName5 = "Other";

    private TasksDatabase databaseHandler;
    private ObservableList<Task> folder1 = FXCollections.observableArrayList();
    private ObservableList<Task> folder2 = FXCollections.observableArrayList();
    private ObservableList<Task> folder3 = FXCollections.observableArrayList();
    private ObservableList<Task> folder4 = FXCollections.observableArrayList();
    private ObservableList<Task> folder5 = FXCollections.observableArrayList();

    public void initialize() {
        AddTasksButtonFolder.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) AddTasksButtonFolder.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("AddTasksView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - Add Tasks");
        });

        AddTasksLabelFolder.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) AddTasksLabelFolder.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("AddTasksView.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 700, 500);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setScene(scene);
            stage.setTitle("Task Orca - Add Tasks");
        });

        TreeItem rootItem = new TreeItem("Folders");
        TreeItem<String> folder1Item = new TreeItem<>(folderName1);
        TreeItem<String> folder2Item = new TreeItem<>(folderName2);
        TreeItem<String> folder3Item = new TreeItem<>(folderName3);
        TreeItem<String> folder4Item = new TreeItem<>(folderName4);
        TreeItem<String> folder5Item = new TreeItem<>(folderName5);

        loadTree1(folder1Item);
        loadTree2(folder2Item);
        loadTree3(folder3Item);
        loadTree4(folder4Item);
        loadTree5(folder5Item);

        rootItem.getChildren().add(folder1Item);
        rootItem.getChildren().add(folder2Item);
        rootItem.getChildren().add(folder3Item);
        rootItem.getChildren().add(folder4Item);
        rootItem.getChildren().add(folder5Item);
        TaskTreeView.setRoot(rootItem);
        TaskTreeView.setShowRoot(false);

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
    }

    private void loadTree1(TreeItem<String> folderItem) {
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() throws Exception {
                databaseHandler = new TasksDatabase();
                ResultSet resultSet = databaseHandler.getTasksByFolder(folderName1);
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTitle(resultSet.getString("title"));
                    folder1.addAll(task);
                }
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                folder1.forEach((task) -> {
                    folderItem.getChildren().add(new TreeItem<>(task.getTitle()));
                });
            }

        };
        new Thread(task).start();
    }

    private void loadTree2(TreeItem<String> folderItem) {
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() throws Exception {
                databaseHandler = new TasksDatabase();
                ResultSet resultSet = databaseHandler.getTasksByFolder(folderName2);
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTitle(resultSet.getString("title"));
                    folder2.addAll(task);
                }
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                folder2.forEach((task) -> {
                    folderItem.getChildren().add(new TreeItem<>(task.getTitle()));
                });
            }

        };
        new Thread(task).start();
    }
    private void loadTree3(TreeItem<String> folderItem) {
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() throws Exception {
                databaseHandler = new TasksDatabase();
                ResultSet resultSet = databaseHandler.getTasksByFolder(folderName3);
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTitle(resultSet.getString("title"));
                    folder3.addAll(task);
                }
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                folder3.forEach((task) -> {
                    folderItem.getChildren().add(new TreeItem<>(task.getTitle()));
                });
            }

        };
        new Thread(task).start();
    }
    private void loadTree4(TreeItem<String> folderItem) {
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() throws Exception {
                databaseHandler = new TasksDatabase();
                ResultSet resultSet = databaseHandler.getTasksByFolder(folderName4);
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTitle(resultSet.getString("title"));
                    folder4.addAll(task);
                }
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                folder4.forEach((task) -> {
                    folderItem.getChildren().add(new TreeItem<>(task.getTitle()));
                });
            }

        };
        new Thread(task).start();
    }
    private void loadTree5(TreeItem<String> folderItem) {
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() throws Exception {
                databaseHandler = new TasksDatabase();
                ResultSet resultSet = databaseHandler.getTasksByFolder(folderName5);
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTitle(resultSet.getString("title"));
                    folder5.addAll(task);
                }
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                folder5.forEach((task) -> {
                    folderItem.getChildren().add(new TreeItem<>(task.getTitle()));
                });
            }

        };
        new Thread(task).start();
    }
}