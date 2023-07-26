package ru.pariy.model.product;

public class ProductException extends RuntimeException {
    public ProductException(String message, String name, Exception cause) {
        super(String.format("%s name = %s", message, name), cause);
    }
}
