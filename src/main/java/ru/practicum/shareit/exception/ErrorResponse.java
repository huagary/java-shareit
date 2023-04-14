package ru.practicum.shareit.exception;

public class ErrorResponse {
    Integer status;
    String error;

    public ErrorResponse(int status, String error) {
        this.status = status;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public Integer getStatus() {
        return status;
    }
}
