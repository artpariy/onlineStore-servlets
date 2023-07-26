package ru.pariy.model.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BrandDAO {
    private final static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/onlineStore";
    private final static String DB_USER = "postgres";
    private final static String DB_PASSWORD = "password";

    public static int getBrandId(String brandName) {
        int id = 0;
        try {
            Class.forName("org.postgresql.Driver");
            final String SELECT_BRAND_QUERY = "SELECT brand_id FROM brand WHERE name = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BRAND_QUERY)) {
                preparedStatement.setString(1, brandName);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getInt("brand_id");
                }
            }
        } catch (Exception exception) {
            throw new ProductException("Error getting brand id, brand", brandName, exception);
        }
        return id;
    }
}
