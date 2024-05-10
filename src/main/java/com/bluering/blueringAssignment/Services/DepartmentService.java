package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.DepartmentDTO;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    void createDepartment(Map<String, Object> departmentDTO);
    List<DepartmentDTO> getDepartments();
    void updateDepartment(Integer id, Map<String, Object> departmentDTO);
    void deleteDepartment(Integer id);
}
