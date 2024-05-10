package com.bluering.blueringAssignment.Mappers;

import com.bluering.blueringAssignment.DTO.LeavetypeDTO;
import com.bluering.blueringAssignment.Entities.LeavetypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LeaveTypeMapper {
    LeaveTypeMapper INSTANCE= Mappers.getMapper(LeaveTypeMapper.class);
    LeavetypeDTO LeaveTypeEntityToLeaveTypeDTO(LeavetypeEntity leavetypeEntity);
    LeavetypeEntity LeaveTypeDTOToLeaveTypeEntity(LeavetypeDTO leavetypeDTO);
}


