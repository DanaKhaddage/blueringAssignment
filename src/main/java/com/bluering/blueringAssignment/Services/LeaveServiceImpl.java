package com.bluering.blueringAssignment.Services;


import com.bluering.blueringAssignment.DTO.LeaveeDTO;
import com.bluering.blueringAssignment.Entities.LeaveeEntity;
import com.bluering.blueringAssignment.Mappers.LeaveMapper;
import com.bluering.blueringAssignment.Repositories.EmployeeRepository;
import com.bluering.blueringAssignment.Repositories.LeaveRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService{
    @Autowired
    private GeneralService generalService;
    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public void createLeave(Map<String,Object> leaveDTO){
        LeaveeEntity leave=new LeaveeEntity();
        generalService.updateEntity(leaveDTO,leave,LeaveeEntity.class);
        leaveRepository.saveAndFlush(leave);
    }

    public void updateLeave(Integer id, Map<String,Object>leaveDTO){
        LeaveeEntity leave=leaveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave not found with number: " + id));
        generalService.updateEntity(leaveDTO,leave,LeaveeEntity.class);
        leaveRepository.saveAndFlush(leave);
    }

    public List<LeaveeDTO> getLeaves(){
        return leaveRepository.findAll()
                .stream()
                .map(leaveMapper::LeaveEntityToLeaveDTO)
                .collect(Collectors.toList());
    }

    public List<LeaveeDTO> getLeavesByEmployeeAndDateRange(Integer employeeId, LocalDate from, LocalDate to) {
        return leaveRepository.findByEmployeeIdAndFromDateBetween(employeeId, from, to)
                .stream()
                .map(leave -> {
                    LeaveeDTO leaveeDTO = leaveMapper.LeaveEntityToLeaveDTO(leave);
                    leaveeDTO.setEmployeeId(employeeId);
                    return leaveeDTO;
                })
                .collect(Collectors.toList());
    }

    public Page<LeaveeDTO> getLeavesByTypeAndEmployee(Integer employeeId, Integer type, Pageable pageable) {
        return leaveRepository.findByEmployeeIdAndLeaveType(employeeId, type, pageable)
                .map(leaveMapper::LeaveEntityToLeaveDTO);
    }

    public void deleteLeave(Integer id){
        leaveRepository.deleteById(id);
    }
}
