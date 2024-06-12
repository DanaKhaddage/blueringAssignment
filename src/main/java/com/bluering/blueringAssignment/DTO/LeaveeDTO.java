package com.bluering.blueringAssignment.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveeDTO {
    private Integer id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer numberOfDays;
    private String note;
    private Integer leaveType;
    private String leaveTypeName;
    private Integer employeeId;
    private String employeeName;
}
