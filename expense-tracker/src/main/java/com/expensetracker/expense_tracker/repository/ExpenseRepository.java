package com.expensetracker.expense_tracker.repository;

import com.expensetracker.expense_tracker.entity.Expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> , JpaSpecificationExecutor<Expense> {

}
