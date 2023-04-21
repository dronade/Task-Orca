package dev.dronade.taskorca.controller;

import dev.dronade.taskorca.database.TaskDatabase;
import dev.dronade.taskorca.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Calendar;

public class AddTasksFormController {
    //TODO: make naming scheme consistent

    private TaskDatabase taskDatabase;
    private int userID;

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
    private Label SuccessTaskLabel;

    @FXML
    private Label FolderLabel;

    @FXML
    private ChoiceBox<String> FolderSelection;


    @FXML
    void initialize() {

        taskDatabase = new TaskDatabase();
        Task task = new Task();

        FolderSelection.getItems().add("Work");
        FolderSelection.getItems().add("Study");
        FolderSelection.getItems().add("Chores");
        FolderSelection.getItems().add("Appointments");
        FolderSelection.getItems().add("Other");

        CreateTaskButton.setOnAction(event -> {

            Calendar calendar = Calendar.getInstance();

            java.sql.Timestamp timestamp =
                    new java.sql.Timestamp(calendar.getTimeInMillis());

            String taskTitle = TaskTitleInput.getText().trim();
            String taskDetails = AdditionalDetailsInput.getText().trim();
            String taskDue_Date = DueDateInput.getValue().toString();
            String taskFolder = FolderSelection.getValue();

            if (!taskTitle.equals("") || !taskDetails.equals("")) {

                System.out.println("user_id: " + AddTasksController.userID);

                task.setUserID(AddTasksController.userID);
                task.setCreated_at(timestamp);
                task.setDetails(taskDetails);
                task.setTitle(taskTitle);
                task.setDue_date(taskDue_Date);
                task.setFolder(taskFolder);

                taskDatabase.insertTask(task);

                TaskTitleInput.setText("");
                AdditionalDetailsInput.setText("");
                //TODO: make due date reset

                SuccessTaskLabel.setVisible(true);
                System.out.println("task added");

            } else {
                System.out.println("task not added");
                SuccessTaskLabel.setVisible(false);

            }

        });

    }

    public int getUserID() {
        System.out.println(userID);

        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
        System.out.println(this.userID);
    }

}