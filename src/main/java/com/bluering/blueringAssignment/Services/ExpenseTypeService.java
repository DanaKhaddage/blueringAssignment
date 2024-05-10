package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.ExpensetypeDTO;

import java.util.List;
import java.util.Map;

public interface ExpenseTypeService {
    void createExpenseType(Map<String, Object> expenseTypeDTO);
    List<ExpensetypeDTO> getExpenseTypes();
    void updateExpenseType(Integer id, Map<String, Object> expenseTypeDTO);
    void deleteExpenseType(Integer id);
}
