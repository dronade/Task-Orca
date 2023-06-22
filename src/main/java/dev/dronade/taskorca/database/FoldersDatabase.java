package dev.dronade.taskorca.database;
import java.sql.*;

/**
 * @author Emily Canto
 * Class for creating the folders database.
 */

public class FoldersDatabase {
    private static final String DATABASE_FILE = "jdbc:sqlite:tasks.db";
    private static final String CREATE_TABLE_STATEMENT = """
            CREATE TABLE IF NOT EXISTS FOLDERS (
             	name text NOT NULL,
            	parent integer NOT NULL,
            	user_id integer NOT NULL,
            );""";

    public FoldersDatabase() {}

    // Create the database to store Folders in if it has not been created already.
    public void createDatabase() {
        try {
            Connection conn = DriverManager.getConnection(DATABASE_FILE);
            conn.close();
        } catch (SQLException error) {
            System.err.println(error.getMessage());
        }
    }

    // Set up the table inside the database.
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
