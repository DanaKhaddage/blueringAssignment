package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.DepartmentDTO;
import com.bluering.blueringAssignment.Entities.DepartmentEntity;
import com.bluering.blueringAssignment.Mappers.DepartmentMapper;
import com.bluering.blueringAssignment.Repositories.DepartmentRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private GeneralService generalService;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

    public void createDepartment(Map<String,Object> departmentDTO){
        DepartmentEntity department=new DepartmentEntity();
        generalService.updateEntity(departmentDTO,department,DepartmentEntity.class);
        departmentRepository.saveAndFlush(department);
    }

    public void deleteDepartment(Integer id){
        departmentRepository.deleteById(id);
    }

    public List<DepartmentDTO> getDepartments(){
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::DepartmentEntityToDepartmentDTO)
                .collect(Collectors.toList());
    }

    public void updateDepartment(Integer id,Map<String,Object> departmentDTO){
        DepartmentEntity department=departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with number: " + id));;
        generalService.updateEntity(departmentDTO,department,DepartmentEntity.class);
        departmentRepository.saveAndFlush(department);
    }
}
