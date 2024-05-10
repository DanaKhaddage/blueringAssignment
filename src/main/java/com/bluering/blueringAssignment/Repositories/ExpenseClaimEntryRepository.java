package com.bluering.blueringAssignment.Repositories;

import com.bluering.blueringAssignment.Entities.ExpenseclaimentryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseClaimEntryRepository extends JpaRepository<ExpenseclaimentryEntity, Integer> {
    @Query(value = "SELECT DISTINCT expenseType FROM ExpenseclaimentryEntity ExpClaimEntry AND EmployeeEntity Emp WHERE ExpClaimEntry.id = ?1",nativeQuery = true)
    List<Integer> getExpensesForEmployee(Integer employeeId);
    List<ExpenseclaimentryEntity> findByExpenseType(Integer expenseType);
}
