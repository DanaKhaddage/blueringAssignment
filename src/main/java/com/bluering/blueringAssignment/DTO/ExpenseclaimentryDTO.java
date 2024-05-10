package com.bluering.blueringAssignment.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
public class ExpenseclaimentryDTO {
    private Integer id;
    private LocalDate date;
    private String description;
    private double total;
    private Integer expenseClaim;
    private Integer expenseType;
}
