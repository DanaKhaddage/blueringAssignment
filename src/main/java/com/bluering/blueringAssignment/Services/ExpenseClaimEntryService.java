package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.DepartmentDTO;
import com.bluering.blueringAssignment.DTO.EmployeeDTO;
import com.bluering.blueringAssignment.DTO.ExpenseclaimentryDTO;

import java.util.List;
import java.util.Map;

public interface ExpenseClaimEntryService {
    void createExpenseClaimEntry(Map<String, Object> expenseClaimEntryDTO);
    List<ExpenseclaimentryDTO> getExpenseClaimEntries();
    List<EmployeeDTO> getExpenseClaimsPerEmployeePerType(Integer id);
    void updateExpenseClaimEntry(Integer id, Map<String, Object> expenseClaimEntryDTO);
    void deleteExpenseClaimEntry(Integer id);
}
