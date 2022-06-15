package com.am.appreview.exception;

public class AppReviewException extends RuntimeException {

    private String message;

    public AppReviewException() {}

    public AppReviewException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}