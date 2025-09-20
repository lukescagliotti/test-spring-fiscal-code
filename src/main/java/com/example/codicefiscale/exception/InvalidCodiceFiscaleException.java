package com.example.codicefiscale.exception;

public class InvalidCodiceFiscaleException extends RuntimeException {
    private final String errorCode;
    private final String errorInfo;

    public InvalidCodiceFiscaleException(String errorCode, String errorInfo) {
        super(errorInfo);
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
