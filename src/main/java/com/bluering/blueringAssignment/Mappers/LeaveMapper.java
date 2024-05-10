package com.bluering.blueringAssignment.Mappers;

import com.bluering.blueringAssignment.DTO.LeaveeDTO;
import com.bluering.blueringAssignment.Entities.LeaveeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LeaveMapper {
    LeaveMapper INSTANCE= Mappers.getMapper(LeaveMapper.class);
    LeaveeDTO LeaveEntityToLeaveDTO(LeaveeEntity leaveEntity);
    LeaveeEntity LeaveDTOToLeaveEntity(LeaveeDTO leaveDTO);
}
