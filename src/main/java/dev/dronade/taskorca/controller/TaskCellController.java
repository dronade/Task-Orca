package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.TaskOrca;
import dev.dronade.taskorca.database.TasksDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * @author Emily Canto
 *  This Class controls the 'TaskCell'
 *  It deals with the formatting of tasks in the tiered list, and also handling the drag and drop feature.
 */

public class TaskCellController extends ListCell<Task> {

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private Label taskLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private CheckBox DoneBox;

    private FXMLLoader fxmlLoader;
    private TasksDatabase db;

    public TaskCellController() {
        setOnDragDetected(event -> {
            if (getItem() == null) {
                return;
            }
            ObservableList<Task> items = getListView().getItems();
            int index = items.indexOf(getItem());

            Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(String.valueOf(index));
            dragboard.setContent(content);
            event.consume();
        });

        setOnDragOver(event -> {
            if (event.getGestureSource() != this &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        setOnDragEntered(event -> {
            if (event.getGestureSource() != this &&
                    event.getDragboard().hasString()) {
                setOpacity(0.3);
            }
        });

        setOnDragExited(event -> {
            if (event.getGestureSource() != this &&
                    event.getDragboard().hasString()) {
                setOpacity(1);
            }
        });

        setOnDragDropped(event -> {
            if (getItem() == null) {
                return;
            }
            Dragboard dragboard = event.getDragboard();
            boolean success = false;

            if (dragboard.hasString()) {
                ObservableList<Task> items = getListView().getItems();
                int draggedIdx = Integer.parseInt(dragboard.getString());
                int thisIdx = items.indexOf(getItem());

                Task temp = items.get(draggedIdx);
                items.set(draggedIdx, items.get(thisIdx));
                items.set(thisIdx, temp);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
        setOnDragDone(DragEvent::consume);
    }

    @Override
    public void updateItem(Task task, boolean empty) {
        db = new TasksDatabase();
        super.updateItem(task, empty);

        if (empty || task == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (fxmlLoader == null) {
                fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("TaskCell.fxml"));
                fxmlLoader.setController(this);
                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            int taskId = task.getTaskID();
            taskLabel.setText(task.getTitle());
            dateLabel.setText(task.getDue_date());
            descriptionLabel.setText(task.getDetails());

            setText(null);
            setGraphic(rootAnchorPane);

            DoneBox.setOnMouseClicked(event -> {
                db.deleteTask(AddTasksController.userID,taskId);
                getListView().getItems().remove(getItem());
                DoneBox.setSelected(false);
            });
        }
    }
}