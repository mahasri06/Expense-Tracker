package com.expensetracker.expense_tracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ExpenseRequestDto {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 50)
    private String title;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotBlank(message = "Category must be required")
    private String category;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @Size(max = 200)
    private String description;

}
