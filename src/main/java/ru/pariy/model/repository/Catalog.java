package ru.pariy.model.repository;

import ru.pariy.model.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/onlineStore";
    private static final String DB_USR = "postgres";
    private static final String DB_PASSWORD = "p";

    /**
     * Метод для получения каталога товаров
     *
     * @param typeId - id товара
     * @return - список товаров
     */
    public static List<Product> readAllModelFromProduct(int typeId) {
        List<Product> productList = new ArrayList<>();
        final String SELECT_ALL_MODEL_QUERY = "SELECT product_id, type_id, brand_id, model, release, price, colors.name AS color_name\n" +
                "FROM product\n" + "JOIN colors ON colors.color_id = product.color_id\n" + "WHERE type_id = ?";

        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USR, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MODEL_QUERY)) {
                preparedStatement.setInt(1, typeId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int productId = resultSet.getInt("product_id");
                    int productTypeId = resultSet.getInt("type_id");
                    int brandId = resultSet.getInt("brand_id");
                    String model = resultSet.getString("model");
                    String release = String.valueOf(resultSet.getDate("release"));
                    int price = resultSet.getInt("price");
                    String colorName = resultSet.getString("color_name");
                    productList.add(new Product(productId, productTypeId, brandId, "", model, release, price, 0, colorName, 0, 0));
                }
            }
        } catch (Exception exception) {
            throw new CatalogException("Error reading all models from product table", exception);
        }
        return productList;
    }
}
