package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrcaApplication;
import dev.dronade.taskorca.database.TaskDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class TaskCellController extends ListCell<Task> {


    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private ImageView iconImageView;

    @FXML
    private Label taskLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private ImageView deleteButton;

    @FXML
    public ImageView listUpdateButton;

    private FXMLLoader fxmlLoader;

    private TaskDatabase db;

    @FXML
    void initialize() throws SQLException {


    }

    @Override
    public void updateItem(Task task, boolean empty) {

        db = new TaskDatabase();

        super.updateItem(task, empty);

        if (empty || task == null) {
            setText(null);
            setGraphic(null);
        }else {

            if (fxmlLoader == null ) {
                fxmlLoader = new FXMLLoader(TaskOrcaApplication.class.getResource("TaskCell.fxml"));
                fxmlLoader.setController(this);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            taskLabel.setText(task.getTitle());
            dateLabel.setText(task.getDue_date());
            descriptionLabel.setText(task.getDetails());

            setText(null);
            setGraphic(rootAnchorPane);


        }
    }

}