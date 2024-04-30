package com.bluering.blueringAssignment.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class LeaveeDTO {
    private Integer id;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer numberOfDays;
    private String note;
    private Integer leaveTypeId;
}
