package ru.pariy.model.repository;

public class OrderDAOException extends RuntimeException {
    public OrderDAOException(String message, long orderId, Exception cause) {
        super(String.format("%s, orderId = %d", message, orderId), cause);
    }

    public OrderDAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
