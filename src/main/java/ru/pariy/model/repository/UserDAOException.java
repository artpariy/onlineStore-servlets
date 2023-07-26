package ru.pariy.model.repository;

public class UserDAOException extends RuntimeException {
    public UserDAOException(String message, String userEmail, Exception cause) {
        super(String.format("%s, userEmail = %s", message, userEmail), cause);
    }
}
