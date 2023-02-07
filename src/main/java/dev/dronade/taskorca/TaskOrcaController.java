package dev.dronade.taskorca;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TaskOrcaController {
    private final Database database = new Database();

    @FXML
    private void initialize() {
        database.createDatabase();
        database.setupDatabase();
    }

    @FXML
    private Label welcomeText;

    @FXML
    protected void onWelcomeButtonClick() {
        welcomeText.setText("Welcome to Task Orca!");
    }
}