package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrcaApplication;
import dev.dronade.taskorca.database.TaskDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TaskCellController extends ListCell<Task> {

    @FXML
    private ImageView UpArrow;

    @FXML
    private ImageView DownArrow;

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


    @Override
    public void updateItem(Task task, boolean empty) {

        db = new TaskDatabase();

        super.updateItem(task, empty);

        if (empty || task == null) {
            setText(null);
            setGraphic(null);
        } else {

            if (fxmlLoader == null) {
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