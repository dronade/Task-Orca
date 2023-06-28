package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrca;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CalendarController {
    @FXML
    private Label ListLabel;

    @FXML
    private Label FolderLabel;

    @FXML
    void initialize() {
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

        FolderLabel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Stage stage = (Stage) FolderLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("FolderView.fxml"));
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
}
