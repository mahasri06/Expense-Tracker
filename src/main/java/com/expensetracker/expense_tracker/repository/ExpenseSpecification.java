package com.expensetracker.expense_tracker.repository;

import com.expensetracker.expense_tracker.DTO.ExpenseFilterDto;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

import com.expensetracker.expense_tracker.entity.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseSpecification {
    public static Specification<Expense> getFilteredExpenses(ExpenseFilterDto filterDto){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filterDto.getCategory() != null && !filterDto.getCategory().isEmpty()){
                predicates.add(
                        cb.equal(root.get("category") , filterDto.getCategory())
                );
            }
            if (filterDto.getMinAmount() != null){
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("amount") , filterDto.getMinAmount())
                );
            }
            if (filterDto.getMaxAmount() != null){
                predicates.add(
                        cb.lessThanOrEqualTo(root.get("amount") , filterDto.getMaxAmount())
                );
            }

            if (filterDto.getStartDate() != null){
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("date") , filterDto.getStartDate())
                );
            }
            if (filterDto.getEndDate() != null){
                predicates.add(
                        cb.lessThanOrEqualTo(root.get("date") , filterDto.getEndDate())
                );
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
