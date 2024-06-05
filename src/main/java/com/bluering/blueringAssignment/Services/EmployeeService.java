package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.EmployeeDTO;

import java.util.List;
import java.util.Map;

public interface EmployeeService{
    void createEmployee(Map<String, Object> employeeDTO);
    void updateEmployee(Integer id, Map<String, Object> employeeDTO);
    List<EmployeeDTO> getEmployeesByDepartment(Integer departmentId);
    List<EmployeeDTO> getEmployees();
    void deleteEmployee(Integer id);
    EmployeeDTO getEmployeeById(Integer id);
}
