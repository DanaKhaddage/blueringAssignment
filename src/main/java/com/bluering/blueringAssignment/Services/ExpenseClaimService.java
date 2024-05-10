package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.ExpenseclaimDTO;

import java.util.List;
import java.util.Map;

public interface ExpenseClaimService {
    void createExpenseClaim(ExpenseclaimDTO expenseClaimDTO);
    List<ExpenseclaimDTO> getExpenseClaims();
    void updateExpenseClaim(Integer id, Map<String, Object> expenseClaimDTO);
    void deleteExpenseClaim(Integer id);
}
