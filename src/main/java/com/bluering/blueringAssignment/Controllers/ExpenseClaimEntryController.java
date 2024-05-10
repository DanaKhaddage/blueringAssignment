package com.bluering.blueringAssignment.Controllers;

import com.bluering.blueringAssignment.ApiResponse;
import com.bluering.blueringAssignment.DTO.EmployeeDTO;
import com.bluering.blueringAssignment.DTO.ExpenseclaimentryDTO;
import com.bluering.blueringAssignment.Services.EmployeeService;
import com.bluering.blueringAssignment.Services.ExpenseClaimEntryService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ECE")
public class ExpenseClaimEntryController {
    private final EmployeeService employeeService;
    private final ExpenseClaimEntryService expenseClaimEntryService;

    public ExpenseClaimEntryController(EmployeeService employeeService,ExpenseClaimEntryService expenseClaimEntryService) {
        this.employeeService=employeeService;
        this.expenseClaimEntryService = expenseClaimEntryService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createExpenseClaimEntry(@RequestBody Map<String,Object> expenseClaimEntryDTO) {
        try {
            expenseClaimEntryService.createExpenseClaimEntry(expenseClaimEntryDTO);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Expense claim entry created successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error creating expense claim entry", e.getMessage()));
        }
    }

    @GetMapping("/")
    public List<ExpenseclaimentryDTO> getExpenseClaimEntries() {
        List<ExpenseclaimentryDTO> ece = expenseClaimEntryService.getExpenseClaimEntries();
        if (ece.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No expense claim entries found");
        }
        return ece;
    }

    @GetMapping("/get/{id}")
    public List<?>getExpenseClaimsPerEmployeePerType(@PathVariable Integer id){
        return expenseClaimEntryService.getExpenseClaimsPerEmployeePerType(id);
    }

    @PatchMapping("/{id}")
    public ApiResponse updateExpenseClaimEntry(@PathVariable Integer id, @RequestBody Map<String, Object> updateFields) {
        try {
            expenseClaimEntryService.updateExpenseClaimEntry(id, updateFields);
            return new ApiResponse(true, "Expense Claim Entry updated successfully", id);
        } catch (ResourceNotFoundException e) {
            return new ApiResponse(false, "Expense Claim Entry not found with id: " + id, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteExpenseClaimEntry(@PathVariable Integer id) {
        try {
            expenseClaimEntryService.deleteExpenseClaimEntry(id);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Expense Claim Entry deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error deleting expense claim entry", e.getMessage()));
        }
    }
}
