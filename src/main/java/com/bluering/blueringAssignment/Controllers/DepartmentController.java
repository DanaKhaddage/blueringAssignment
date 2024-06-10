package com.bluering.blueringAssignment.Controllers;

import com.bluering.blueringAssignment.ApiResponse;
import com.bluering.blueringAssignment.DTO.DepartmentDTO;
import com.bluering.blueringAssignment.Services.DepartmentService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
@CrossOrigin(origins = "*")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createDepartment(@RequestBody Map<String,Object> departmentDTO) {
            try {
                departmentService.createDepartment(departmentDTO);
                return ResponseEntity
                        .ok(new ApiResponse(true, "Department created successfully"));
            } catch (Exception e) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse(false, "Error creating department", e.getMessage()));
            }
    }

    @GetMapping("/")
    public List<DepartmentDTO> getDepartments() {
        List<DepartmentDTO> departments = departmentService.getDepartments();
        if (departments.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No departments found");
        }
        return departments;
    }

    @PatchMapping("/{id}")
    public ApiResponse updateDepartment(@PathVariable Integer id, @RequestBody Map<String, Object> updateFields) {
        try {
            departmentService.updateDepartment(id, updateFields);
            return new ApiResponse(true, "Department updated successfully", id);
        } catch (ResourceNotFoundException e) {
            return new ApiResponse(false, "Department not found with id: " + id, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDepartment(@PathVariable Integer id) {
        try {
            departmentService.deleteDepartment(id);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Department deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error deleting department", e.getMessage()));
        }
    }
}
