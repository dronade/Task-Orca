package dev.dronade.taskorca.database;
import java.sql.*;

/**
 * Class for adding and fetching URLs from a database.
 * @author Emily Canto
 */
public class TaskDatabase {
    private static final String DATABASE_FILE = "jdbc:sqlite:tasks.db";
    private static final String CREATE_TABLE_STATEMENT = """
            CREATE TABLE IF NOT EXISTS TASKS (
            	user_id integer NOT NULL,
             	title text NOT NULL,
            	details text,
            	due_date text,
            	created_at text NOT NULL
            );""";
//FOREIGN KEY(user_id) REFERENCES USERS(id)
    public TaskDatabase() {}
    /**
     * Create the database to store Tasks in if it has not been created already.
     */
    public void createDatabase() {
        try {
            DriverManager.getConnection(DATABASE_FILE); // create a new database if it does not exist already
        } catch (SQLException error) {
            System.err.println(error.getMessage());
        }
    }

    /**
     * Setup the table inside the database.
     */
    public void setupDatabase() {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_FILE);
            Statement statement = conn.createStatement();
            statement.executeUpdate(CREATE_TABLE_STATEMENT);
            statement.close();
            conn.close();
        } catch (SQLException error) {
            System.err.println(error.getMessage());
        }
    }
}
