package com.bluering.blueringAssignment.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ExpenseclaimDTO {
    private Integer id;
    private LocalDate date;
    private String description;
    private double total;
    private String status;
    private Integer employeeId;
    private List<ExpenseclaimentryDTO> expenseClaimEntry;
}
