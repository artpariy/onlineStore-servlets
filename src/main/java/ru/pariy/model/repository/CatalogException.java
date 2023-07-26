package ru.pariy.model.repository;

public class CatalogException extends RuntimeException {
    public CatalogException(String message, Exception cause) {
        super(message, cause);
    }
}
