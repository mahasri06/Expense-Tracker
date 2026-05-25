package com.expensetracker.expense_tracker.controller;

import com.expensetracker.expense_tracker.ApiWrapper.ApiResponse;
import com.expensetracker.expense_tracker.DTO.ExpenseRequestDto;
import com.expensetracker.expense_tracker.DTO.ExpenseResponseDto;
import com.expensetracker.expense_tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // CREATE Expense
    @PostMapping
    public ResponseEntity<ApiResponse<ExpenseResponseDto>> addExpense(
            @Valid @RequestBody ExpenseRequestDto dto) {
        log.info("Received request to add a new expense: {}", dto);
        ExpenseResponseDto responseDto = expenseService.addExpenses(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Expense added successfully", responseDto));
    }

    // GET all Expenses
    @GetMapping
    public ResponseEntity<ApiResponse<Page<ExpenseResponseDto>>> getAllExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date")String sortBy,
            @RequestParam(defaultValue = "desc")String sortDir
    ) {
        Page<ExpenseResponseDto> expenses = expenseService.getAllExpenses(page , size , sortBy , sortDir);
        return ResponseEntity.ok(ApiResponse.success("Expenses fetched successfully", expenses));
    }

    // GET Expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponseDto>> getExpenseById(@PathVariable Long id) {
        ExpenseResponseDto expense = expenseService.getById(id);
        return ResponseEntity.ok(ApiResponse.success("Expense fetched successfully", expense));
    }

    // UPDATE Expense
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExpenseResponseDto>> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequestDto dto) {

        ExpenseResponseDto updatedExpense = expenseService.updateExpense(id, dto);
        return ResponseEntity.ok(ApiResponse.success("Expense updated successfully", updatedExpense));
    }

    // DELETE Expense
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteExpense(@PathVariable Long id) {
        log.info("Received request to delete expense with ID: {}", id);
        expenseService.deleteExpense(id);
        return ResponseEntity.ok(ApiResponse.success("Expense deleted successfully"));
    }
}