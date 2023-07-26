package ru.pariy.model.repository;

import ru.pariy.entity.User;

import java.sql.*;

public class UserDAO {

    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/onlineStore";
    private static final String DB_USR = "postgres";
    private static final String DB_PASSWORD = "p";

    /**
     * Метод для добавления пользователя в базу данных
     *
     * @param user - добавляемый пользователь
     */
    public static void writeToDb(User user) {
        try {
            Class.forName("org.postgresql.Driver");

            final String SQL_INSERT_QUERY = "INSERT INTO users (email, password, salt, first_name, last_name, role) VALUES (?,?,?,?,?,?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USR, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY)) {
                preparedStatement.setString(1, user.getEmail());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getSalt());
                preparedStatement.setString(4, user.getFirstName());
                preparedStatement.setString(5, user.getLastName());
                preparedStatement.setString(6, user.getRole());
                preparedStatement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new UserDAOException("Error write user to db", user.getEmail(), exception);
        }
    }

    /**
     * Метод для получения пользователей из базы данных
     *
     * @param email - электронная почта пользователя
     * @return Возвращает пользователя
     */
    public static User readFromDb(String email) {
        try {
            Class.forName("org.postgresql.Driver");

            final String SQL_SELECT_QUERY = "Select * FROM users WHERE email = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USR, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_QUERY)) {
                preparedStatement.setString(1, email);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    return null;
                } else {
                    String userEmail = resultSet.getString("email");
                    String password = resultSet.getString("password");
                    String salt = resultSet.getString("salt");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String role = resultSet.getString("role");
                    return new User(userEmail, password, salt, firstName, lastName, role);
                }
            }
        } catch (Exception exception) {
            throw new UserDAOException("Error reading user from db", email, exception);
        }
    }

    /**
     * Метод проверяет наличие пользователя в базе данных
     *
     * @param email - электронная почта пользователя
     */
    public static boolean exist(String email) {
        return readFromDb(email) != null;
    }
}
