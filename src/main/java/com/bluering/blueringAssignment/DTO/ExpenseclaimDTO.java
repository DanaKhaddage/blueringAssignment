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
    private BigDecimal total;
    private String status;
    private List<ExpenseclaimentryDTO> expenseClaimEntry;
}
