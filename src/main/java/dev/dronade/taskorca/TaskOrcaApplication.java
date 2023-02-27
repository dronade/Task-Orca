package dev.dronade.taskorca;

import dev.dronade.taskorca.database.TaskDatabase;
import dev.dronade.taskorca.database.UsersDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskOrcaApplication extends Application {

    private final TaskDatabase taskDatabase = new TaskDatabase();
    private final UsersDatabase userDatabase = new UsersDatabase();
    @Override
    public void start(Stage stage) throws IOException {
        userDatabase.createDatabase();
        userDatabase.setupDatabase();
        taskDatabase.createDatabase();
        taskDatabase.setupDatabase();

        //editing out so i can bypass login to work on tasks
        //FXMLLoader fxmlLoader = new FXMLLoader(TaskOrcaApplication.class.getResource("LoginView.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(TaskOrcaApplication.class.getResource("AddTasksView.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.setTitle("Task Orca - Log In");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}