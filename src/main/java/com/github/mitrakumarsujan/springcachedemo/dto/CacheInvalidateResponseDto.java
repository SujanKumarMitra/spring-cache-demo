package com.github.mitrakumarsujan.springcachedemo.dto;


public class CacheInvalidateResponseDto {
    private boolean success;
    private String message;

    public CacheInvalidateResponseDto() {
    }

    public CacheInvalidateResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
