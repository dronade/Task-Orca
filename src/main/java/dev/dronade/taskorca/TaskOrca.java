package dev.dronade.taskorca;

import dev.dronade.taskorca.database.FoldersDatabase;
import dev.dronade.taskorca.database.TasksDatabase;
import dev.dronade.taskorca.database.UsersDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author Emily Canto
 *  Main class for starting the task orca application.
 */

public class TaskOrca extends Application {

    private final TasksDatabase tasksDatabase = new TasksDatabase();
    private final UsersDatabase usersDatabase = new UsersDatabase();
    private final FoldersDatabase foldersDatabase = new FoldersDatabase();

    @Override
    public void start(Stage stage) throws IOException {
        usersDatabase.createDatabase();
        usersDatabase.setupDatabase();
        tasksDatabase.createDatabase();
        tasksDatabase.setupDatabase();
        foldersDatabase.createDatabase();
        foldersDatabase.setupDatabase();

        // Defaults to login page.
        FXMLLoader fxmlLoader = new FXMLLoader(TaskOrca.class.getResource("LoginView.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.setTitle("Task Orca - Log In");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}