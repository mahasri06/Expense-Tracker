package com.expensetracker.expense_tracker.ApiWrapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean status;
    private String message;
    private T data;
    private ErrorCode errorcode;
    private LocalDateTime localDateTime;

    private ApiResponse(boolean status , String message , T data , ErrorCode errorcode , LocalDateTime localDateTime){
        this.status = status;
        this.message = message;
        this.data = data;
        this.errorcode = errorcode;
        this.localDateTime = localDateTime;
    }

    public static <T> ApiResponse<T> success(String message , T data){
        return new ApiResponse<>(true , message , data  ,null , LocalDateTime.now());
    }

    public static <T> ApiResponse<T> success(String message){
        return new ApiResponse<>(true , message , null  , null , LocalDateTime.now());
    }
    public static <T> ApiResponse<T> failure(String message , ErrorCode errorcode){
        return new ApiResponse<>(false , message , null  ,errorcode ,LocalDateTime.now());
    }
}
