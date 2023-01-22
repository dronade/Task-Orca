package dev.dronade.taskorca;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TaskOrcaController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onWelcomeButtonClick() {
        welcomeText.setText("Welcome to Task Orca!");
    }
}