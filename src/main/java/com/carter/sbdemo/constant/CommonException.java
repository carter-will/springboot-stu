package com.carter.sbdemo.constant;

public class CommonException extends Exception{

    private static final long serialVersionUID = 4564124491192825748L;


    private int code;

    private String message;

    public CommonException() {

    }

    public CommonException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
