package com.bluering.blueringAssignment.Controllers;

import com.bluering.blueringAssignment.ApiResponse;
import com.bluering.blueringAssignment.DTO.LeaveRequestDTO;
import com.bluering.blueringAssignment.DTO.LeaveeDTO;
import com.bluering.blueringAssignment.Services.LeaveService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {
    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createLeave(@RequestBody Map<String,Object> leaveDTO) {
        try {
            leaveService.createLeave(leaveDTO);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Leave created successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error creating leave", e.getMessage()));
        }
    }

    @GetMapping("/")
    public List<LeaveeDTO> getLeaves() {
        List<LeaveeDTO> leaves = leaveService.getLeaves();
        if (leaves.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No leaves found");
        }
        return leaves;
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveeDTO>> getLeavesByEmployeeAndDateRange(
            @PathVariable Integer employeeId,
            @RequestBody LeaveeDTO requestDTO) {
        List<LeaveeDTO> leaves = leaveService.getLeavesByEmployeeAndDateRange(employeeId, requestDTO.getFromDate(), requestDTO.getToDate());
        return ResponseEntity.ok(leaves);
    }
//    public ResponseEntity<List<LeaveeDTO>> getLeavesByEmployeeAndDateRange(
//            @PathVariable Integer employeeId,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
//        List<LeaveeDTO> leaves = leaveService.getLeavesByEmployeeAndDateRange(employeeId, from, to);
//        return ResponseEntity.ok(leaves);
//    }

    @GetMapping("/employee/{employeeId}/leaves")
    public ResponseEntity<Page<LeaveeDTO>> getLeavesByTypeAndEmployee(
            @PathVariable Integer employeeId,
            @RequestBody LeaveRequestDTO requestDTO) {
        Page<LeaveeDTO> leavesPage = leaveService.getLeavesByTypeAndEmployee(employeeId, requestDTO.getLeaveType(), PageRequest.of(requestDTO.getPage(), requestDTO.getSize()));
        return ResponseEntity.ok(leavesPage);
    }
//    public ResponseEntity<Page<LeaveeDTO>> getLeavesByTypeAndEmployee(
//            @PathVariable Integer employeeId,
//            @RequestParam("type") int type,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        Page<LeaveeDTO> leavesPage = leaveService.getLeavesByTypeAndEmployee(employeeId, type, PageRequest.of(page, size));
//        return ResponseEntity.ok(leavesPage);
//    }

    @PatchMapping("/{id}")
    public ApiResponse updateLeave(@PathVariable Integer id, @RequestBody Map<String, Object> updateFields) {
        try {
            leaveService.updateLeave(id, updateFields);
            return new ApiResponse(true, "Leave updated successfully", id);
        } catch (Exception e) {
            return new ApiResponse(false, "Leave not found with id: " + id, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteLeave(@PathVariable Integer id) {
        try {
            leaveService.deleteLeave(id);
            return ResponseEntity
                    .ok(new ApiResponse(true, "Leave deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Error deleting leave", e.getMessage()));
        }
    }
}
