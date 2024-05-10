package com.bluering.blueringAssignment.Mappers;

import com.bluering.blueringAssignment.DTO.EmployeeDTO;
import com.bluering.blueringAssignment.Entities.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EmployeeMapper {
    EmployeeMapper INSTANCE= Mappers.getMapper(EmployeeMapper.class);
    EmployeeDTO EmployeeEntityToEmployeeDTO(EmployeeEntity employeeEntity);
    EmployeeEntity EmployeeDTOToEmployeeEntity(EmployeeDTO employeeDTO);
}
