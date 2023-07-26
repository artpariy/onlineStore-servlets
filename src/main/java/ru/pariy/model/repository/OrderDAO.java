package ru.pariy.model.repository;

import ru.pariy.entity.Order;
import ru.pariy.model.product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    private final static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/onlineStore";
    private final static String DB_USER = "postgres";
    private final static String DB_PASSWORD = "password";


    /**
     * Метод для добавления заказов в базу данных
     *
     * @param order - заказ, добавляемый в базу данных
     */
    public static void addToOrders(Order order) {
        try {
            Class.forName("org.postgresql.Driver");

            final String SQL_INSERT_QUERY_ORDERS = "INSERT INTO orders (phone_number, address, user_login) VALUES (?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY_ORDERS)) {
                preparedStatement.setString(1, order.getPhoneNumber());
                preparedStatement.setString(2, order.getAddress());
                preparedStatement.setString(3, order.getUserLogin());
                preparedStatement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new OrderDAOException("Error adding order", exception);
        }
    }

    /**
     * Метод для получения id заказа из базы данных
     *
     * @return Возвращает список всех id заказов
     */
    public static List<Long> readIdFromOrders() {
        List<Long> orderIdList = new ArrayList<>();
        try {
            Class.forName("org.postgresql.Driver");

            long id;
            final String SQL_SELECT_ID_QUERY = "SELECT id FROM orders";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ID_QUERY)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getLong("id");
                    orderIdList.add(id);
                }
            }
        } catch (Exception exception) {
            throw new OrderDAOException("Error reading id from orders table", exception);
        }
        return orderIdList;
    }

    /**
     * Метод добавляет в базу данных дополнительную информацию о заказе
     *
     * @param orderId - id заказа
     * @param product - товар, который присутствует в заказе
     */
    public static void addToOrdersInfo(long orderId, Product product) {
        try {
            Class.forName("org.postgresql.Driver");

            final String SQL_INSERT_QUERY_ORDERS_INFO = "INSERT INTO orders_info (id, type_id, brand_id, product_model," +
                    "release, price, color_id, vendor_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY_ORDERS_INFO)) {
                preparedStatement.setLong(1, orderId);
                preparedStatement.setInt(2, product.getTypeId());
                preparedStatement.setInt(3, product.getBrandId());
                preparedStatement.setString(4, product.getModel());
                preparedStatement.setDate(5, Date.valueOf(product.getRelease()));
                preparedStatement.setInt(6, product.getPrice());
                preparedStatement.setInt(7, product.getColorId());
                preparedStatement.setInt(8, product.getVendorCode());
                int row = preparedStatement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new OrderDAOException("Error adding to ordersInfo table", orderId, exception);
        }
    }
}
