package com.trade_accounting.models.util;

public class ErrorResponse<T> extends Response<T> {

    private Error error;

    public ErrorResponse(Error error) {
        this.success = false;
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}