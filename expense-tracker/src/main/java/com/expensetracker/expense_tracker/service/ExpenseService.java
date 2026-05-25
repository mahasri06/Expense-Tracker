package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.DTO.ExpenseFilterDto;
import com.expensetracker.expense_tracker.DTO.ExpenseRequestDto;
import com.expensetracker.expense_tracker.DTO.ExpenseResponseDto;
import com.expensetracker.expense_tracker.entity.Expense;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ExpenseService {
    ExpenseResponseDto addExpenses(ExpenseRequestDto dto);

    Page<ExpenseResponseDto> getAllExpenses(
            int page , int size , String sortBy , String sortDir
    );

    ExpenseResponseDto getById(Long id);

    void deleteExpense(Long id);

    ExpenseResponseDto updateExpense(Long id , ExpenseRequestDto expense);

    Page<ExpenseResponseDto> getFilteredExpenses(ExpenseFilterDto filterDto, int page, int size, String sortBy, String sortDir);
}
