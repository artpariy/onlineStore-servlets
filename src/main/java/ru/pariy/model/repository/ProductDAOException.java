package ru.pariy.model.repository;

public class ProductDAOException extends RuntimeException{
    public ProductDAOException(String message, int vendorCode, Exception cause) {
        super(String.format("%s, vendorCode = %d", message, vendorCode), cause);
    }

    public ProductDAOException(String message, long id, Exception cause) {
        super(String.format("%s, id = %d", message, id), cause);
    }

    public ProductDAOException(String message, Exception cause) {
        super(message, cause);
    }
}
