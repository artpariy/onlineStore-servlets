package ru.pariy.model.product;

import org.eclipse.tags.shaded.org.apache.regexp.RE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TypeDAO {
    private final static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/onlineStore";
    private final static String DB_USER = "postgres";
    private final static String DB_PASSWORD = "password";

    public static int getTypeId(String typeName) {
        int id = 0;
        try {
            Class.forName("org.postgresql.Driver");
            final String SELECT_TYPE_QUERY = "SELECT type_id FROM type WHERE name = ?";

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TYPE_QUERY)) {
                preparedStatement.setString(1, typeName);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getInt("type_id");
                }
            }
        } catch (Exception exception) {
            throw new ProductException("Error getting type id, type", typeName, exception);
        }
        return id;
    }

}
