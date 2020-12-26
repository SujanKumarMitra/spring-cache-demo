package com.github.mitrakumarsujan.springcachedemo.exceptionhandler;

public class RequestGlobalError {

    private String objectName;
    private String errorMessage;

    public RequestGlobalError() {
    }

    public RequestGlobalError(String objectName, String errorMessage) {
        this.objectName = objectName;
        this.errorMessage = errorMessage;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "RequestObjectError{" +
                "objectName='" + objectName + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
