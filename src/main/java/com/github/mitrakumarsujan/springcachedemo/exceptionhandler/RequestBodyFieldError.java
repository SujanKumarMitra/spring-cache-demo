package com.github.mitrakumarsujan.springcachedemo.exceptionhandler;

public class RequestBodyFieldError {

    private String fieldName;
    private String valuePassed;
    private String errorMessage;

    public RequestBodyFieldError() {
    }

    public RequestBodyFieldError(String fieldName, String valuePassed, String errorMessage) {
        this.fieldName = fieldName;
        this.valuePassed = valuePassed;
        this.errorMessage = errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValuePassed() {
        return valuePassed;
    }

    public void setValuePassed(String valuePassed) {
        this.valuePassed = valuePassed;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "RequestBodyFieldError{" +
                "fieldName='" + fieldName + '\'' +
                ", valuePassed='" + valuePassed + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
