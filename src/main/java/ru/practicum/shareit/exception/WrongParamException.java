package ru.practicum.shareit.exception;

public class WrongParamException extends RuntimeException {
    public WrongParamException(String message) {
        super(message);
    }
}
