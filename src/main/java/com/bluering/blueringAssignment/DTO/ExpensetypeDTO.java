package com.bluering.blueringAssignment.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ExpensetypeDTO {
    private Integer id;
    private String name;
    List<ExpenseclaimentryDTO> expenseClaimEntries;
}
