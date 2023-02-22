package dev.dronade.taskorca.database;
import dev.dronade.taskorca.model.User;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

/**
 * Class for adding and fetching URLs from a database.
 * @author Emily Canto
 */
public class UsersDatabase {
    private static final String DATABASE_FILE = "jdbc:sqlite:users.db";
    private static final String CREATE_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS USERS (\n"
            + "	username text NOT NULL,\n"
            + "	password text\n"
            + ");";

    public UsersDatabase() {
    }

    /**
     * Create the database to store Tasks in if it has not been created already.
     */
    public void createDatabase() {
        try {
            getConnection(DATABASE_FILE); // create a new database if it does not exist already
        } catch (SQLException error) {
            System.err.println("connect" + error.getMessage());
        }
    }

    /**
     * Setup the table inside the database.
     */
    public void setupDatabase() {
        try {
            Connection conn = getConnection(DATABASE_FILE);
            Statement statement = conn.createStatement();
            statement.execute(CREATE_TABLE_STATEMENT);
            statement.close();
            conn.close();
        } catch (SQLException error) {
            System.err.println("setup " + error.getMessage());
        }
    }

    public void signUpUser(User user) throws SQLException {
        final String SQL = "INSERT INTO USERS VALUES(?,?)";
        try (Connection conn = getConnection(DATABASE_FILE); PreparedStatement ps = conn.prepareStatement(SQL);) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
