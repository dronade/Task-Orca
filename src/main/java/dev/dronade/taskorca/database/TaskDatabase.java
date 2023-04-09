package dev.dronade.taskorca.database;
import dev.dronade.taskorca.model.Task;
import dev.dronade.taskorca.model.User;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

/**
 * Class for adding and fetching URLs from a database.
 * @author Emily Canto
 */
public class TaskDatabase {
    private static final String DATABASE_FILE = "jdbc:sqlite:tasks.db";
    //TODO: set task_id, and make user_id a foreign key
    private static final String CREATE_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS TASKS ("
            	+ "user_id integer NOT NULL,"
             	+ "title text NOT NULL,"
            	+ "details text,"
            	+ "due_date text,"
                + "created_at text NOT NULL"
                + ");";

    public TaskDatabase() {}
    /**
     * Create the database to store Tasks in if it has not been created already.
     */
    public void createDatabase() {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_FILE); // create a new database if it does not exist already
            conn.close();
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

    public void insertTask(Task task) {

        String query = "INSERT INTO TASKS( user_id,"
                + "created_at, title, details, due_date)"
                + "VALUES(?,?,?,?,?)";

        try {
            Connection conn = getConnection(DATABASE_FILE); PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, task.getUserID());
            ps.setTimestamp(2, task.getCreated_at());
            ps.setString(3, task.getTitle());
            ps.setString(4, task.getDetails());
            ps.setString(5, task.getDue_date());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getTasksByUserID(int userId) {
        ResultSet tasks = null;
        String query = "SELECT * FROM TASKS WHERE user_id=?";

        try {
            Connection conn = getConnection(DATABASE_FILE);PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            tasks = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
