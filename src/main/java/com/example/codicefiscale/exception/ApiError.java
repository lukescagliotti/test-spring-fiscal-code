package com.example.codicefiscale.exception;

public class ApiError {
    private String errorCode;
    private String errorInfo;

    public ApiError(String errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
}
