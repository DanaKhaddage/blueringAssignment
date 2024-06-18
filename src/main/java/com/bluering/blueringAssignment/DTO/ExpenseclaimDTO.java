package com.bluering.blueringAssignment.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ExpenseclaimDTO {
    private Integer id;
    private LocalDate date;
    private String description;
    private double total;
    private String status;
    private Integer employeeId;
    private List<ExpenseclaimentryDTO> expenseClaimEntry=new ArrayList<>();

    // Method to recalculate the total
    public void recalculateTotal() {
        this.total = expenseClaimEntry.stream()
                .mapToDouble(ExpenseclaimentryDTO::getTotal)
                .sum();
    }

    // Add method to add an entry
    public void addEntry(ExpenseclaimentryDTO entry) {
        expenseClaimEntry.add(entry);
        recalculateTotal();
    }

    // Add method to remove an entry
    public void removeEntry(ExpenseclaimentryDTO entry) {
        expenseClaimEntry.remove(entry);
        recalculateTotal();
    }
}
