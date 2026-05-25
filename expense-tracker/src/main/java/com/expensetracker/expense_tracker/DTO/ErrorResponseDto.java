package com.expensetracker.expense_tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseDto {
    private LocalDateTime dateTime;
    private int status;
    private String error;
    private String message;
}
