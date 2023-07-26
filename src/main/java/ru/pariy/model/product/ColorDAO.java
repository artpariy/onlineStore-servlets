package ru.pariy.model.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ColorDAO {
    private final static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/onlineStore";
    private final static String DB_USER = "postgres";
    private final static String DB_PASSWORD = "password";

    public static int getColorId(String colorName) {
        int id = 0;
        try {
            Class.forName("org.postgresql.Driver");
            final String SQL_SELECT_COLOR_QUERY = "SELECT color_id FROM colors WHERE name = ?";

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COLOR_QUERY)) {
                preparedStatement.setString(1, colorName);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getInt("color_id");
                }
            }
        } catch (Exception exception) {
            throw new ProductException("Error getting color id, color", colorName, exception);
        }
        return id;
    }
}
