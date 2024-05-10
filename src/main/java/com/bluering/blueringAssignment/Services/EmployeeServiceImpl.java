package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.EmployeeDTO;
import com.bluering.blueringAssignment.Entities.DepartmentEntity;
import com.bluering.blueringAssignment.Entities.EmployeeEntity;
import com.bluering.blueringAssignment.Mappers.EmployeeMapper;
import com.bluering.blueringAssignment.Repositories.DepartmentRepository;
import com.bluering.blueringAssignment.Repositories.EmployeeRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private GeneralService generalService;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    public void createEmployee(Map<String,Object> employeeDTO){
        EmployeeEntity employee=new EmployeeEntity();
        generalService.updateEntity(employeeDTO,employee,EmployeeEntity.class);
        employeeRepository.saveAndFlush(employee);
    }


    public void updateEmployee(Integer id, Map<String,Object>employeeDTO){
        EmployeeEntity employee=employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with number: " + id));
        generalService.updateEntity(employeeDTO,employee,EmployeeEntity.class);
        employeeRepository.saveAndFlush(employee);
    }

    public List<EmployeeDTO> getEmployeesByDepartment(Integer departmentId){
        return employeeRepository.findAll()
                .stream()
                .map(employee->{
                    EmployeeDTO employeeDTO=employeeMapper.EmployeeEntityToEmployeeDTO(employee);
                    employeeDTO.setDepartmentName(departmentRepository.findById(employeeDTO.getDepartmentId()).get().getName());
                    return employeeDTO;
                })
                .filter(employee -> employee.getDepartmentId().equals(departmentId))
                .collect(Collectors.toList());
    }

    public void deleteEmployee(Integer id){
        employeeRepository.deleteById(id);
    }
}
