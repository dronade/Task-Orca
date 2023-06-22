package dev.dronade.taskorca.database;
import dev.dronade.taskorca.model.User;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

/**
 * @author Emily Canto
 * Class for creating the users database + performing crud on it.
 */

public class UsersDatabase {
    private static final String DATABASE_FILE = "jdbc:sqlite:users.db";
    private static final String CREATE_TABLE_STATEMENT = """
            CREATE TABLE IF NOT EXISTS USERS (
                user_id integer PRIMARY KEY,
            	username text NOT NULL,
            	password text
            );""";

    public UsersDatabase() {
    }

    // Create the database to store Users in if it has not been created already.
    public void createDatabase() {
        try {
            getConnection(DATABASE_FILE);
        } catch (SQLException error) {
            System.err.println("create" + error.getMessage());
        }
    }

    // Set up the table inside the database.
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
        final String SQL = "INSERT INTO USERS (username,password)" +
                " VALUES (?,?)";
        try {
            Connection conn = getConnection(DATABASE_FILE); PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getLoggedUser(User user) {
        ResultSet resultSet = null;
        if (!user.getUsername().equals("") || !user.getPassword().equals("")) {
            String query = "SELECT * FROM USERS WHERE " +
                    "username =? AND password =?";
            try {
                Connection conn = getConnection(DATABASE_FILE);
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());

                resultSet = preparedStatement.executeQuery();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } else {
            System.out.println("Please enter your username & password");

        }
        return resultSet;
    }
}
