package com.bluering.blueringAssignment.Controllers;

import com.bluering.blueringAssignment.ApiResponse;
import com.bluering.blueringAssignment.DTO.ExpenseclaimDTO;
import com.bluering.blueringAssignment.Services.ExpenseClaimService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenseclaim")
public class ExpenseClaimController {
    private final ExpenseClaimService expenseClaimService;

    public ExpenseClaimController(ExpenseClaimService expenseClaimService) {
        this.expenseClaimService = expenseClaimService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createExpenseClaim(@RequestBody ExpenseclaimDTO expenseClaimDTO) {
        try {
            expenseClaimService.createExpenseClaim(expenseClaimDTO);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Expense claim created successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error creating expense claim", e.getMessage()));
        }
    }

    @GetMapping("/")
    public List<ExpenseclaimDTO> getExpenseClaims() {
        List<ExpenseclaimDTO> expenseClaims = expenseClaimService.getExpenseClaims();
        if (expenseClaims.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No expense claims found");
        }
        return expenseClaims;
    }

    @PatchMapping("/{id}")
    public ApiResponse updateExpenseClaim(@PathVariable Integer id, @RequestBody Map<String, Object> updateFields) {
        try {
            expenseClaimService.updateExpenseClaim(id, updateFields);
            return new ApiResponse(true, "Expense claim updated successfully", id);
        } catch (ResourceNotFoundException e) {
            return new ApiResponse(false, "Expense claim not found with id: " + id, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteExpenseClaim(@PathVariable Integer id) {
        try {
            expenseClaimService.deleteExpenseClaim(id);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Expense claim deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error deleting expense claim", e.getMessage()));
        }
    }
}