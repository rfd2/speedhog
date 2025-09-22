package io.speedhog.adapter.in.rest;

public class ErrorResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private String getMessage() {
        return message;
    }
}
