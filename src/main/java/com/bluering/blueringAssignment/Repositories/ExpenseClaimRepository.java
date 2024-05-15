package com.bluering.blueringAssignment.Repositories;

import com.bluering.blueringAssignment.Entities.ExpenseclaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseClaimRepository extends JpaRepository<ExpenseclaimEntity, Integer> {
    List<ExpenseclaimEntity> findByEmployeeId(Integer employeeId);
}
