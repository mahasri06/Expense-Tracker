package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.DTO.ExpenseFilterDto;
import com.expensetracker.expense_tracker.repository.ExpenseSpecification;
// removed BadRequestException import
import org.springframework.data.domain.*;
import com.expensetracker.expense_tracker.DTO.ExpenseRequestDto;
import com.expensetracker.expense_tracker.DTO.ExpenseResponseDto;
import com.expensetracker.expense_tracker.entity.Expense;
import com.expensetracker.expense_tracker.exception.ExpenseNotFoundException;
import com.expensetracker.expense_tracker.repository.ExpenseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.expensetracker.expense_tracker.mapper.ExpenseMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ExpenseServiceImpl implements ExpenseService{
    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository , ExpenseMapper expenseMapper){
        this.expenseRepository = expenseRepository;
        this.expenseMapper = expenseMapper;
    }


    @Override
    public ExpenseResponseDto addExpenses(ExpenseRequestDto dto){
        log.info("Adding new expense with title: {}", dto.getTitle());
        Expense expense = expenseMapper.mapToEntity(dto);

        Expense saved  = expenseRepository.save(expense);

        return expenseMapper.mapToDto(saved);
    }

    @Override
    public Page<ExpenseResponseDto> getAllExpenses(
            int page , int size , String sortBy , String sortDir
    ){

        Sort sort  = sortDir.equalsIgnoreCase("desc")?
                Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page , size , sort);

        Page<Expense> expensePage = expenseRepository.findAll(pageable);

        return expensePage.map(expenseMapper::mapToDto);
    }

    @Override
    public ExpenseResponseDto getById(Long id){
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException(id));

        return expenseMapper.mapToDto(expense);

    }

    @Override
    public void deleteExpense(Long id){
        expenseRepository.deleteById(id);
    }

    @Override
    public ExpenseResponseDto updateExpense(Long id , ExpenseRequestDto requestDto){

        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));



        Expense updated = expenseRepository.save(expenseMapper.mapToEntity(requestDto));

        return expenseMapper.mapToDto(updated);
    }

    @Override
    public Page<ExpenseResponseDto> getFilteredExpenses(
            ExpenseFilterDto filterDto , int page , int size , String sortBy, String sortDir
    ){

        if(filterDto.getMinAmount() != null && filterDto.getMaxAmount() != null) {
            if (filterDto.getMinAmount().compareTo(filterDto.getMaxAmount()) > 0) {
                throw new IllegalArgumentException("Min Amount cannot be greater than Max Amount");
            }
        }

        Sort sort = sortDir.equalsIgnoreCase("desc")?
                Sort.by(sortBy).descending():
                Sort.by(sortBy).ascending();

        Specification<Expense> spec = ExpenseSpecification.getFilteredExpenses(filterDto);

        Pageable pageable = PageRequest.of(page , size , sort);

        log.debug("Fetching filtered expenses for page {} and size {}", page, size);
        Page<Expense> expensePage = expenseRepository.findAll(spec, pageable);

        return expensePage.map(expenseMapper::mapToDto);
    }
}
