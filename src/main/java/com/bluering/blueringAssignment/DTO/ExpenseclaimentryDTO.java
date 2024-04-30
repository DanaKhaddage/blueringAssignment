package com.bluering.blueringAssignment.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExpenseclaimentryDTO {
    private Integer id;
    private Date date;
    private String description;
    private BigDecimal total;
    private Integer expenseClaim;
    private Integer expenseType;
}
