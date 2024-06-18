package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.ExpenseclaimDTO;
import com.bluering.blueringAssignment.DTO.ExpenseclaimentryDTO;

import java.util.List;
import java.util.Map;

public interface ExpenseClaimService {
    void createExpenseClaim(ExpenseclaimDTO expenseClaimDTO);
    List<ExpenseclaimDTO> getExpenseClaims();
    List<ExpenseclaimentryDTO> getEntriesByExpenseClaimId(Integer expenseClaimId);
    void updateExpenseClaim(Integer id, Map<String, Object> expenseClaimDTO);
    void deleteExpenseClaim(Integer id);

    void addExpenseClaimEntry(Integer claimId, ExpenseclaimentryDTO entryDTO);
    void deleteExpenseClaimEntry(Integer claimId, Integer entryId);
}







//package com.bluering.blueringAssignment.Services;
//
//import com.bluering.blueringAssignment.DTO.ExpenseclaimDTO;
//import com.bluering.blueringAssignment.DTO.ExpenseclaimentryDTO;
//
//import java.util.List;
//import java.util.Map;
//
//public interface ExpenseClaimService {
//    void createExpenseClaim(ExpenseclaimDTO expenseClaimDTO);
//    List<ExpenseclaimDTO> getExpenseClaims();
//    List<ExpenseclaimentryDTO> getEntriesByExpenseClaimId(Integer expenseClaimId);
//    void updateExpenseClaim(Integer id, Map<String, Object> expenseClaimDTO);
//    void deleteExpenseClaim(Integer id);
//}
