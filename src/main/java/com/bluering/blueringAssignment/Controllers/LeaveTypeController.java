package com.bluering.blueringAssignment.Controllers;

import com.bluering.blueringAssignment.ApiResponse;
import com.bluering.blueringAssignment.DTO.LeavetypeDTO;
import com.bluering.blueringAssignment.Services.LeaveTypeService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/leaveType")
public class LeaveTypeController {
    private final LeaveTypeService leaveTypeService;

    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createLeaveType(@RequestBody Map<String,Object> leaveTypeDTO) {
        try {
            leaveTypeService.createLeaveType(leaveTypeDTO);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Leave type created successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error creating leave type", e.getMessage()));
        }
    }

    @GetMapping("/")
    public List<LeavetypeDTO> getLeaveTypes() {
        List<LeavetypeDTO> leaveType = leaveTypeService.getLeaveTypes();
        if (leaveType.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No leave types found");
        }
        return leaveType;
    }

    @PatchMapping("/{id}")
    public ApiResponse updateLeaveType(@PathVariable Integer id, @RequestBody Map<String, Object> updateFields) {
        try {
            leaveTypeService.updateLeaveType(id, updateFields);
            return new ApiResponse(true, "Leave type updated successfully", id);
        } catch (ResourceNotFoundException e) {
            return new ApiResponse(false, "Leave type not found with id: " + id, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLeaveType(@PathVariable Integer id) {
        try {
            leaveTypeService.deleteLeaveType(id);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Leave type deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error deleting leave type", e.getMessage()));
        }
    }
}
