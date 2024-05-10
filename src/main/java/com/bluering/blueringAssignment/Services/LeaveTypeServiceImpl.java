package com.bluering.blueringAssignment.Services;

import com.bluering.blueringAssignment.DTO.LeavetypeDTO;
import com.bluering.blueringAssignment.Entities.LeavetypeEntity;
import com.bluering.blueringAssignment.Mappers.LeaveTypeMapper;
import com.bluering.blueringAssignment.Repositories.LeaveTypeRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService{
    @Autowired
    private GeneralService generalService;
    @Autowired
    private LeaveTypeMapper leaveTypeMapper;
    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    public void createLeaveType(Map<String,Object> leaveTypeDTO){
        LeavetypeEntity leaveType=new LeavetypeEntity();
        generalService.updateEntity(leaveTypeDTO,leaveType,LeavetypeEntity.class);
        leaveTypeRepository.saveAndFlush(leaveType);
    }

    public void updateLeaveType(Integer id, Map<String,Object>leaveTypeDTO){
        LeavetypeEntity leaveType=leaveTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave type not found with number: " + id));
        generalService.updateEntity(leaveTypeDTO,leaveType,LeavetypeEntity.class);
        leaveTypeRepository.saveAndFlush(leaveType);
    }

    public List<LeavetypeDTO> getLeaveTypes(){
        return leaveTypeRepository.findAll()
                .stream()
                .map(leaveTypeMapper::LeaveTypeEntityToLeaveTypeDTO)
                .collect(Collectors.toList());
    }

    public void deleteLeaveType(Integer id){
        leaveTypeRepository.deleteById(id);
    }

}
