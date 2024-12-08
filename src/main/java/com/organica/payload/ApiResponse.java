package com.organica.payload;

public class ApiResponse {

    private String Message;

    public ApiResponse(String message) {
        Message = message;
    }

    public ApiResponse(){}

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
