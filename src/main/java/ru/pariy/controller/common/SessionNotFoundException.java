package ru.pariy.controller.common;

public class SessionNotFoundException extends RuntimeException implements UserException{
    public SessionNotFoundException(String message) {
        super(message);
    }
}
