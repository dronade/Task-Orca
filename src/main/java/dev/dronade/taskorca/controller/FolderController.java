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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class FolderController {

    @FXML
    private AnchorPane GreyBox;

    @FXML
    private MenuButton FilterBox;

    @FXML
    private Label ListLabel;

    @FXML
    private Label FolderLabel;

    @FXML
    private Label SettingsLabel;

    @FXML
    private TreeView<String> TaskTreeView;

    private String folderName = "study";

    private TaskDatabase databaseHandler;
    private ObservableList<Task> folder1 = FXCollections.observableArrayList();

    public void initialize() {
        TreeItem rootItem = new TreeItem("Folders");
        TreeItem<String> folder1Item = new TreeItem<>("Study");
        folder1Item.getChildren().add(new TreeItem<>("test this works"));
        folder1Item.getChildren().add(new TreeItem<>("test this works2"));

        loadTree();
        folder1.forEach((task) -> {
           folder1Item.getChildren().add(new TreeItem<>(task.getTitle()));
           System.out.println("folder for each called");
        });

        rootItem.getChildren().add(folder1Item);
        TaskTreeView.setRoot(rootItem);
        TaskTreeView.setShowRoot(false);

        ListLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) ListLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrcaApplication.class.getResource("ListView.fxml"));
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

    private void loadTree() {
        javafx.concurrent.Task<Void> task = new javafx.concurrent.Task<>() {
            @Override
            protected Void call() throws Exception {
                databaseHandler = new TaskDatabase();
                ResultSet resultSet = databaseHandler.getTasksByFolder(folderName);
                System.out.println("task started");
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setTitle(resultSet.getString("title"));
                    folder1.addAll(task);
                }
                return null;
            }
        };
        new Thread(task).start();
    }
}