package com.bluering.blueringAssignment.Controllers;

import com.bluering.blueringAssignment.ApiResponse;
import com.bluering.blueringAssignment.DTO.DepartmentDTO;
import com.bluering.blueringAssignment.DTO.EmployeeDTO;
import com.bluering.blueringAssignment.Entities.EmployeeEntity;
import com.bluering.blueringAssignment.Mappers.EmployeeMapper;
import com.bluering.blueringAssignment.Repositories.DepartmentRepository;
import com.bluering.blueringAssignment.Repositories.EmployeeRepository;
import com.bluering.blueringAssignment.Services.DepartmentService;
import com.bluering.blueringAssignment.Services.EmployeeService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository, EmployeeMapper employeeMapper)
    {
        this.employeeService = employeeService;
        this.employeeMapper=employeeMapper;
        this.employeeRepository=employeeRepository;
    }

    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees() {
        List<EmployeeDTO> employees = employeeService.getEmployees();
        if (employees.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employees found");
        }
        return employees;
    }

    @PostMapping("/employees")
    public ResponseEntity<ApiResponse> createEmployee(@RequestBody Map<String,Object> employeeDTO) {
        try {
            // Ensure that departmentId is an Integer
            if (employeeDTO.containsKey("departmentId") && employeeDTO.get("departmentId") instanceof String) {
                try {
                    employeeDTO.put("departmentId", Integer.parseInt((String) employeeDTO.get("departmentId")));
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ApiResponse(false, "Invalid departmentId format", e.getMessage()));
                }
            }
            employeeService.createEmployee(employeeDTO);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Employee created successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error creating employee", e.getMessage()));
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        try {
            EmployeeDTO employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping("/byDepartment/{departmentId}")
    public ResponseEntity<?> getEmployeesByDepartment(@PathVariable Integer departmentId) {
        try {
            List<EmployeeDTO> employees = employeeService.getEmployeesByDepartment(departmentId);
            if (employees.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse(false, "No employees found for department ID",departmentId));
            }
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error retrieving employees by department ID", e.getMessage()));
        }
    }



    @DeleteMapping("employees/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Employee deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error deleting employee", e.getMessage()));
        }
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<ApiResponse> updateEmployee(@PathVariable Integer id, @RequestBody Map<String, Object> updateFields) {
        try {
            // Ensure that departmentId is an Integer
            if (updateFields.containsKey("departmentId") && updateFields.get("departmentId") instanceof String) {
                try {
                    updateFields.put("departmentId", Integer.parseInt((String) updateFields.get("departmentId")));
                } catch (NumberFormatException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ApiResponse(false, "Invalid departmentId format", e.getMessage()));
                }
            }

            employeeService.updateEmployee(id, updateFields);
            return ResponseEntity.ok(new ApiResponse(true, "Employee updated successfully", id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(false, "Employee not found with id: " + id, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error updating employee", e.getMessage()));
        }
    }
}
