package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.database.TaskDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Calendar;

public class AddTasksFormController {

    private int userID;
    private TaskDatabase taskDatabase;

    @FXML
    private TextField TaskTitleInput;

    @FXML
    private TextField AdditionalDetailsInput;

    @FXML
    private DatePicker DueDateInput;

    @FXML
    private Label TaskTitleLabel;

    @FXML
    private Label AdditionalDetailsLabel;

    @FXML
    private Label DueDateLabel;

    @FXML
    private Button CreateTaskButton;


    @FXML
    void initialize() {

        taskDatabase = new TaskDatabase();
        Task task = new Task();

        CreateTaskButton.setOnAction(event -> {

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskTitle = TaskTitleInput.getText().trim();
            String taskDetails = AdditionalDetailsInput.getText().trim();
            String taskDue_Date = DueDateInput.getValue().toString();

            if (!taskTitle.equals("") || !taskDetails.equals("")) {

                System.out.println("user_id: " + AddTasksController.userId);

                task.setUserId(AddTasksController.userId);
                task.setCreated_at(timestamp);
                task.setDetails(taskDetails);
                task.setTitle(taskTitle);
                task.setDue_date(taskDue_Date);

                taskDatabase.insertTask(task);

                TaskTitleInput.setText("");
                AdditionalDetailsInput.setText("");
                //DueDateInput.setChronology();

                System.out.println("task added");

            } else {
                System.out.println("task not added");

            }

        });

    }

}