package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.LeaveeDTO;
import com.bluering.blueringAssignment.DTO.LeavetypeDTO;

import java.util.List;
import java.util.Map;

public interface LeaveTypeService {
    void createLeaveType(Map<String,Object> leaveTypeDTO);
    void updateLeaveType(Integer id, Map<String,Object>leaveTypeDTO);
    List<LeavetypeDTO> getLeaveTypes();
    void deleteLeaveType(Integer id);
}
