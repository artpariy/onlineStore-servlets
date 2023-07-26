package ru.pariy.model.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.model.product.Product;

import java.sql.*;

public class ProductDAO {

    private final static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/onlineStore";
    private final static String DB_USER = "postgres";
    private final static String DB_PASSWORD = "password";
    private final static String SELECT_PRODUCT_BY_ID_SQL = "SELECT product_id, product.type_id, brand.brand_id, brand.name AS brand_name, " +
            "model, release, price, colors.color_id, colors.name AS color_name, vendor_code, count_on_stock\n" +
            "FROM product\n" +
            "JOIN brand ON brand.brand_id = product.brand_id\n" +
            "JOIN colors ON colors.color_id = product.color_id\n" +
            "WHERE product_id = ?";

    public static Product getById(Long id) {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID_SQL)) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    throw new IllegalStateException("Record not found id=" + id);
                } else {
                    String brand = resultSet.getString("brand_name");
                    int productId = resultSet.getInt("product_id");
                    int typeId = resultSet.getInt("type_id");
                    int brandId = resultSet.getInt("brand_id");
                    String model = resultSet.getString("model");
                    String release = String.valueOf(resultSet.getDate("release"));
                    int price = resultSet.getInt("price");
                    int colorId = resultSet.getInt("color_id");
                    String colorName = resultSet.getString("color_name");
                    int count = resultSet.getInt("count_on_stock");
                    int vendorCode = resultSet.getInt("vendor_code");
                    return new Product(productId, typeId, brandId, brand, model, release, price, colorId, colorName, vendorCode, count);
                }
            }
        } catch (Exception exception) {
            throw new ProductDAOException("Error getting records by id", id, exception);
        }
    }

    /**
     * Метод для добавления товара в базу данных
     *
     * @param product - добавляемый продукт
     */
    public static void writeProduct(Product product) {
        try {
            Class.forName("org.postgresql.Driver");

            final String SQL_INSERT_QUERY = "INSERT INTO product (type_id, brand_id, model, release, price," +
                    " color_id, vendor_code, count_on_stock)" +
                    "VALUES (?,?,?,?,?,?,?,?)";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY)) {
                preparedStatement.setInt(1, product.getTypeId());
                preparedStatement.setInt(2, product.getBrandId());
                preparedStatement.setString(3, product.getModel());
                preparedStatement.setDate(4, Date.valueOf(product.getRelease()));
                preparedStatement.setInt(5, product.getPrice());
                preparedStatement.setInt(6, product.getColorId());
                preparedStatement.setInt(7, product.getVendorCode());
                preparedStatement.setInt(8, product.getCountOnStock());
                preparedStatement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new ProductDAOException("Error writing product", product.getVendorCode(), exception);
        }
    }


    /**
     * Метод для получения товара из базы данных
     *
     * @param vendorCode - код товара
     * @return Возвращает полученный товар
     */
    public static Product readProduct(int vendorCode) {
        try {
            Class.forName("org.postgresql.Driver");
            final String SQL_SELECT_QUERY = "SELECT product_id, type_id, brand_id, model, release, price, color_id," +
                    " count_on_stock FROM product WHERE vendor_code = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_QUERY)) {
                preparedStatement.setInt(1, vendorCode);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    return null;
                } else {
                    int productId = resultSet.getInt("product_id");
                    int typeId = resultSet.getInt("type_id");
                    int brandId = resultSet.getInt("brand_id");
                    String model = resultSet.getString("model");
                    String release = String.valueOf(resultSet.getDate("release"));
                    int price = resultSet.getInt("price");
                    int colorId = resultSet.getInt("color_id");
                    int count = resultSet.getInt("count_on_stock");
                    return new Product(productId, typeId, brandId, model, release, price, colorId, vendorCode, count);
                }
            }
        } catch (Exception exception) {
            throw new ProductDAOException("Error reading product", vendorCode, exception);
        }
    }

    /**
     * Метод проверяет наличие товара
     *
     * @param vendorCode - код товара
     */
    public static boolean exist(int vendorCode) {
        return readProduct(vendorCode) != null;
    }

    /**
     * Метод для изменения количества товара.
     * Используется в случае добавления товара в магазине
     *
     * @param vendorCode    - код товара
     * @param oldQuantity   - старое количество
     * @param addedQuantity - новое количество
     * @return Возвращает количество изменненых строк
     */
    public static int updateProduct(int vendorCode, int oldQuantity, int addedQuantity) {
        try {
            Class.forName("org.postgresql.Driver");
            final String SQL_UPDATE_QUERY = "UPDATE product SET count_on_stock=? WHERE vendor_code=?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_QUERY)) {
                preparedStatement.setInt(1, oldQuantity + addedQuantity);
                preparedStatement.setInt(2, vendorCode);
                return preparedStatement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new ProductDAOException("Error update product", vendorCode, exception);
        }
    }

    /**
     * Метод для обновления количества товара.
     * Используется при оформлении заказа пользователем
     *
     * @param countInOrder - количество товара в заказе
     * @param vendorCode   - код товара
     */
    public static void updateCountOnStock(int countInOrder, int vendorCode) {
        try {
            Class.forName("org.postgresql.Driver");
            final String SQL_UPDATE_COUNT_ON_STOCK_QUERY = "UPDATE product SET count_on_stock = ? WHERE vendor_code = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COUNT_ON_STOCK_QUERY)) {
                int newCountOnStock = getCountOnStock(vendorCode) - countInOrder;
                preparedStatement.setInt(1, newCountOnStock);
                preparedStatement.setInt(2, vendorCode);
                preparedStatement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new ProductDAOException("Error update count on stock", vendorCode, exception);
        }
    }

    /**
     * Данный метод получает количество определенного товара
     *
     * @param vendorCode - код товара
     * @return Возвращает количество товара
     */
    private static int getCountOnStock(int vendorCode) {
        try {
            Class.forName("org.postgresql.Driver");

            final String SQL_GET_COUNT_ON_STOCK_QUERY = "SELECT count_on_stock FROM product WHERE vendor_code = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COUNT_ON_STOCK_QUERY)) {
                preparedStatement.setInt(1, vendorCode);
                ResultSet resultSet = preparedStatement.executeQuery();
                int count = 0;
                while (resultSet.next()) {
                    count = resultSet.getInt("count_on_stock");
                }
                return count;
            }
        } catch (Exception exception) {
            throw new ProductDAOException("Error getting count on stock", vendorCode, exception);
        }
    }

    /**
     * Метод для удаления товара из базы данных
     *
     * @param vendorCode - код товара
     * @return Возвращает количество изменненных строк
     */
    public static int deleteProductFromDb(int vendorCode) {
        try {
            Class.forName("org.postgresql.Driver");
            final String SQL_DELETE_PRODUCT_QUERY = "DELETE FROM product WHERE vendor_code = ?";
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT_QUERY)) {
                preparedStatement.setInt(1, vendorCode);
                return preparedStatement.executeUpdate();
            }
        } catch (Exception exception) {
            throw new ProductDAOException("Error delete product", vendorCode, exception);
        }
    }
}
