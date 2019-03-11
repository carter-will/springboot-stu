package com.carter.sbdemo.constant;

public class ErrorResponseEntity {


    private int code;

    private String message;

    /**
     *   alt + insert   自动生成  Constructor
     */

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
