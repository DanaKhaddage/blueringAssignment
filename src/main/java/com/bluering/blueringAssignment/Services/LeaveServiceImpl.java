package com.bluering.blueringAssignment.Services;


import com.bluering.blueringAssignment.DTO.LeaveeDTO;
import com.bluering.blueringAssignment.DTO.PaginationRequest;
import com.bluering.blueringAssignment.Entities.EmployeeEntity;
import com.bluering.blueringAssignment.Entities.LeaveeEntity;
import com.bluering.blueringAssignment.Entities.LeavetypeEntity;
import com.bluering.blueringAssignment.Mappers.LeaveMapper;
import com.bluering.blueringAssignment.Repositories.EmployeeRepository;
import com.bluering.blueringAssignment.Repositories.LeaveRepository;
import com.bluering.blueringAssignment.Repositories.LeaveTypeRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    public void createLeave(Map<String,Object> leaveDTO){
        LeaveeEntity leave=new LeaveeEntity();
        generalService.updateEntity(leaveDTO,leave,LeaveeEntity.class);

        // Fetch leave type name
        Integer leaveTypeId = (Integer) leaveDTO.get("leaveType");
        String leaveTypeName = ""; // Fetch leave type name from repository based on leaveTypeId
        // Example: (assuming you have a repository method to fetch leave type name)
        // String leaveTypeName = leaveTypeRepository.findById(leaveTypeId).get().getName();
        leave.setLeaveTypeName(leaveTypeName);

        // Fetch employee name
        Integer employeeId = (Integer) leaveDTO.get("employeeId");
        String employeeName = ""; // Fetch employee name from repository based on employeeId
        // Example: (assuming you have a repository method to fetch employee name)
        // String employeeName = employeeRepository.findById(employeeId).get().getName();
        leave.setEmployeeName(employeeName);

        leaveRepository.saveAndFlush(leave);
//        LeaveeEntity leave=new LeaveeEntity();
//        generalService.updateEntity(leaveDTO,leave,LeaveeEntity.class);
//        leaveRepository.saveAndFlush(leave);
    }

    public void updateLeave(Integer id, Map<String,Object>leaveDTO){
        LeaveeEntity leave=leaveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave not found with number: " + id));
        generalService.updateEntity(leaveDTO,leave,LeaveeEntity.class);
        leaveRepository.saveAndFlush(leave);
    }

    public List<LeaveeDTO> getLeaves(){
        return leaveRepository.findAll()
                .stream()
                .map(leave -> {
                    LeaveeDTO leaveDTO = leaveMapper.LeaveEntityToLeaveDTO(leave);
                    // Fetch and set leave type name
                    String leaveTypeName = ""; // Fetch from repository based on leave.getLeaveType()
                    if (leave.getLeaveType() != null) {
                        leaveTypeName = leaveTypeRepository.findById(leave.getLeaveType())
                                .map(LeavetypeEntity::getName)
                                .orElse("");
                    }
                    leaveDTO.setLeaveTypeName(leaveTypeName);

                    // Fetch and set employee name
                    String employeeName = ""; // Fetch from repository based on leave.getEmployeeId()
                    if (leave.getEmployeeId() != null) {
                        employeeName = employeeRepository.findById(leave.getEmployeeId())
                                .map(EmployeeEntity::getName)
                                .orElse("");
                    }
                    leaveDTO.setEmployeeName(employeeName);

                    return leaveDTO;
                })
                .collect(Collectors.toList());
//        return leaveRepository.findAll()
//                .stream()
//                .map(leaveMapper::LeaveEntityToLeaveDTO)
//                .collect(Collectors.toList());
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

//    public Page<LeaveeDTO> getLeavesByTypeAndEmployee(Integer employeeId, Integer type, Pageable pageable) {
//        return leaveRepository.findByEmployeeIdAndLeaveType(employeeId, type, pageable)
//                .map(leaveMapper::LeaveEntityToLeaveDTO);
//    }


    public PaginationRequest getLeavesByTypeAndEmployee(Integer leaveType, Integer employeeId, int page, int size) {
        Page<LeaveeEntity> leavePage;

        if (leaveType != null && employeeId != null) {
            leavePage = leaveRepository.findByLeaveTypeAndEmployeeId(leaveType, employeeId, PageRequest.of(page, size));
        } else if (leaveType != null) {
            leavePage = leaveRepository.findByLeaveType(leaveType, PageRequest.of(page, size));
        } else if (employeeId != null) {
            leavePage = leaveRepository.findByEmployeeId(employeeId, PageRequest.of(page, size));
        } else {
            leavePage = leaveRepository.findAll(PageRequest.of(page, size));
        }

        List<LeaveeDTO> leaveeDTOs = leavePage.getContent().stream()
                .map(leave -> new LeaveeDTO(/* map fields from LeaveeEntity to LeaveeDTO */))
                .collect(Collectors.toList());

        return new PaginationRequest(leaveeDTOs, leavePage.getTotalElements(), leavePage.getNumber(), leavePage.getSize());
    }
//    public PaginationRequest getLeavesByTypeAndEmployee(Integer leaveTypeId, Integer employeeId, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<LeaveeEntity> leavePage;
//
//        if (leaveTypeId == null && employeeId == null) {
//            // Fetch all leaves if no filters are applied
//            leavePage = leaveRepository.findAll(pageable);
//        } else if (leaveTypeId != null && employeeId == null) {
//            // Fetch leaves by leave type
//            leavePage = leaveRepository.findByLeaveType(leaveTypeId, pageable);
//        } else if (leaveTypeId == null && employeeId != null) {
//            // Fetch leaves by employee
//            leavePage = leaveRepository.findByEmployeeId(employeeId, pageable);
//        } else {
//            // Fetch leaves by both leave type and employee
//            leavePage = leaveRepository.findByLeaveTypeAndEmployeeId(leaveTypeId, employeeId, pageable);
//        }
//
//        PaginationRequest response = new PaginationRequest();
//        List<LeaveeDTO> leaveDTOs = leavePage.getContent().stream()
//                .map(leaveMapper::LeaveEntityToLeaveDTO)
//                .collect(Collectors.toList());
//        response.setItems(leaveDTOs);
//        response.setTotalItems(leavePage.getTotalElements());
//
//        return response;
//    }

    public void deleteLeave(Integer id){
        leaveRepository.deleteById(id);
    }
}
