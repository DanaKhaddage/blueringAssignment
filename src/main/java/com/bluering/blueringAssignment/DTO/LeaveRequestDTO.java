package com.bluering.blueringAssignment.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class LeaveRequestDTO {
    private Integer employeeId;
    private Integer leaveType;
    private Integer page;
    private Integer size;
}
