package com.bluering.blueringAssignment.Mappers;

import com.bluering.blueringAssignment.DTO.DepartmentDTO;
import com.bluering.blueringAssignment.Entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DepartmentMapper {
    DepartmentMapper INSTANCE= Mappers.getMapper(DepartmentMapper.class);
    DepartmentDTO DepartmentEntityToDepartmentDTO(DepartmentEntity department);
    DepartmentEntity DepartmentDTOToDepartmentEntity(DepartmentDTO departmentDTO);
}
