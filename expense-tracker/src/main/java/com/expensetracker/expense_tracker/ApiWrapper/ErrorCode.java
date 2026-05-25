package com.expensetracker.expense_tracker.ApiWrapper;

public enum ErrorCode{
    NOT_FOUND(404),
    UNAUTHORIZED(401),
    VALIDATION_ERROR(400),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }
    public int getCode() { return code; }
}
