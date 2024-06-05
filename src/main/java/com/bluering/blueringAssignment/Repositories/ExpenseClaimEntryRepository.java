package com.bluering.blueringAssignment.Repositories;

import com.bluering.blueringAssignment.Entities.ExpenseclaimentryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseClaimEntryRepository extends JpaRepository<ExpenseclaimentryEntity, Integer> {
    @Query(value = "SELECT DISTINCT ExpClaimEntry.expense_type_id FROM ExpenseClaimEntry ExpClaimEntry JOIN ExpenseClaim ExpClaim ON ExpClaimEntry.expense_claim_id = ExpClaim.id JOIN Employee Emp ON ExpClaim.employee_id = Emp.id  WHERE Emp.id = ?1",nativeQuery = true)
    //@Query(value = "SELECT DISTINCT expenseType FROM ExpenseclaimentryEntity ExpClaimEntry AND EmployeeEntity Emp WHERE ExpClaimEntry.id = ?1",nativeQuery = true)
    List<Integer> getExpensesForEmployee(Integer employeeId);
    List<ExpenseclaimentryEntity> findByExpenseType(Integer expenseType);
}
