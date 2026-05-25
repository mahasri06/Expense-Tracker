package com.expensetracker.expense_tracker.mapper;

import com.expensetracker.expense_tracker.DTO.ExpenseRequestDto;
import com.expensetracker.expense_tracker.DTO.ExpenseResponseDto;
import com.expensetracker.expense_tracker.entity.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {

    public ExpenseResponseDto mapToDto(Expense expense){
        ExpenseResponseDto dto = new ExpenseResponseDto();
        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setCategory(expense.getCategory());
        dto.setDate(expense.getDate());
        dto.setDescription(expense.getDescription());
        return dto;
    }

    public Expense mapToEntity(ExpenseRequestDto dto){
        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setDate(dto.getDate());
        expense.setDescription(dto.getDescription());
        return expense;
    }
}