package com.bluering.blueringAssignment.Controllers;

import com.bluering.blueringAssignment.ApiResponse;
import com.bluering.blueringAssignment.DTO.ExpensetypeDTO;
import com.bluering.blueringAssignment.Services.ExpenseTypeService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenseType")
public class ExpenseTypeController {
    private final ExpenseTypeService expenseTypeService;

    public ExpenseTypeController(ExpenseTypeService expenseTypeService) {
        this.expenseTypeService = expenseTypeService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createExpenseType(@RequestBody Map<String, Object> expenseTypeDTO) {
        try {
            expenseTypeService.createExpenseType(expenseTypeDTO);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Expense type created successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error creating expense type", e.getMessage()));
        }
    }

    @GetMapping("/")
    public List<ExpensetypeDTO> getExpenseTypes() {
        List<ExpensetypeDTO> expenseTypes = expenseTypeService.getExpenseTypes();
        if (expenseTypes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No expense types found");
        }
        return expenseTypes;
    }

    @PatchMapping("/{id}")
    public ApiResponse updateExpenseType(@PathVariable Integer id, @RequestBody Map<String, Object> updateFields) {
        try {
            expenseTypeService.updateExpenseType(id, updateFields);
            return new ApiResponse(true, "Expense type updated successfully", id);
        } catch (ResourceNotFoundException e) {
            return new ApiResponse(false, "Expense type not found with id: " + id, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteExpenseType(@PathVariable Integer id) {
        try {
            expenseTypeService.deleteExpenseType(id);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Expense type deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error deleting expense type", e.getMessage()));
        }
    }
}
