package com.expensetracker.expense_tracker.exception;

public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(Long id){
        System.out.println("Expense not found with id: " + id);
    }
    public ExpenseNotFoundException(String message){
        super(message);
    }
}