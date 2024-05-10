package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.LeaveeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface LeaveService {
    void createLeave(Map<String,Object> leaveDTO);
    void updateLeave(Integer id, Map<String,Object>leaveDTO);
    List<LeaveeDTO> getLeavesByEmployeeAndDateRange(Integer employeeId, LocalDate from, LocalDate to);
    Page<LeaveeDTO> getLeavesByTypeAndEmployee(Integer employeeId, String type, Pageable pageable);
    List<LeaveeDTO> getLeaves();
    void deleteLeave(Integer id);
}