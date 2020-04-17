package org.example.controller;

public class MyException extends Exception {
    private String message;

    public MyException(String msgText) {
        message = msgText;
    }

    public String getMessage() {
        return message;
    }
}
