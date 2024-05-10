package com.bluering.blueringAssignment.Repositories;

import com.bluering.blueringAssignment.Entities.ExpenseclaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseClaimRepository extends JpaRepository<ExpenseclaimEntity, Integer> {

}
