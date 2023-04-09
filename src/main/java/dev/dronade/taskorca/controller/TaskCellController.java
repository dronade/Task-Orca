package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.database.TaskDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskCellController extends ListCell {

    private TaskDatabase databaseHandler;

    @FXML
    private Label TaskTitle;

    protected void updateItem(Task task, boolean empty) throws SQLException {
        super.updateItem(task, empty);
        if(empty || task == null) {
            setText(null);
        } else {
            databaseHandler = new TaskDatabase();
            ResultSet resultSet = databaseHandler.getTasksByUserID(AddTasksController.userID);

            while (resultSet.next()) {
                task.setTitle(resultSet.getString("title"));
                task.setDetails(resultSet.getString("details"));
                task.setDue_date(resultSet.getString("due_date"));
                task.setCreated_at(resultSet.getTimestamp("created_at"));
                // to do
            }
        }

    }
}
