package com.springtutorial.store.dtos;

public class ResponseDto<T> {
    private boolean status;
    private String message;
    private T data;
    private int statusCode;

    // constructors
    public ResponseDto() {
    }

    public ResponseDto(boolean status, String message, int statusCode, T data) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
    }

    // static methods
    public static <T> ResponseDto<T> success(String message, int statusCode, T data) {
        return new ResponseDto<>(true, message, statusCode, data);
    }

    public static <T> ResponseDto<T> success(String message, T data) {
        return success(message, 200, data);
    }

    public static <T> ResponseDto<T> failure(String message, int statusCode) {
        return new ResponseDto<>(false, message, statusCode, null);
    }

    public static <T> ResponseDto<T> failure(String message) {
        return failure(message, 400);
    }

    // getters and setters
    public boolean isSuccess() {
        return status;
    }

    public void setSuccess(boolean success) {
        this.status = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
