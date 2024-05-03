package com.sebastian.exceptions;

public class WarehouseException extends RuntimeException {

    public WarehouseException(String message, Throwable cause) {
        super(message, cause);
    }
}
