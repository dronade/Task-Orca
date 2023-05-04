package dev.dronade.taskorca.database;
import dev.dronade.taskorca.model.Task;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

/**
 * @author Emily Canto
 * Class for adding and fetching Tasks from a database.
 */

public class TaskDatabase {
    private static final String DATABASE_FILE = "jdbc:sqlite:tasks.db";
    private static final String CREATE_TABLE_STATEMENT = """
            CREATE TABLE IF NOT EXISTS TASKS (
            	user_id integer NOT NULL,
             	title text NOT NULL,
            	details text,
            	due_date text,
            	folder text NOT NULL,
            	created_at text NOT NULL
            );""";

    public TaskDatabase() {}
    /**
     * Create the database to store Tasks in if it has not been created already.
     */
    public void createDatabase() {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_FILE);
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
                + "created_at, title, details, due_date, folder)"
                + "VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = getConnection(DATABASE_FILE); PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, task.getUserID());
            ps.setTimestamp(2, task.getCreated_at());
            ps.setString(3, task.getTitle());
            ps.setString(4, task.getDetails());
            ps.setString(5, task.getDue_date());
            ps.setString(6, task.getFolder());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getTasksByUserID(int userId) {
        ResultSet tasks = null;
        String query = "SELECT *,rowid FROM TASKS WHERE user_id=?";

        try {
            Connection conn = getConnection(DATABASE_FILE);PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            tasks = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public ResultSet getTasksByFolder(String folder) {
        ResultSet tasks = null;
        String query = "SELECT *,rowid FROM TASKS WHERE folder=?";

        try {
            Connection conn = getConnection(DATABASE_FILE);PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, folder);
            tasks = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void deleteTask(int userId, int taskId)  {
        String query = "DELETE FROM TASKS WHERE user_id=? AND rowid =?";

        try {
            Connection conn = getConnection(DATABASE_FILE);PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, taskId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
