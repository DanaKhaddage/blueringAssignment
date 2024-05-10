package com.bluering.blueringAssignment.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "expenseclaimentry", schema = "assignment", catalog = "")
public class ExpenseclaimentryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id@Column(name = "id")
    private int id;
    @Basic@Column(name = "date")
    private LocalDate date;
    @Basic@Column(name = "description")
    private String description;
    @Basic@Column(name = "total")
    private double total;
    @Basic@Column(name="expense_type_id")
    private Integer expenseType;
    @Basic@Column(name="expense_claim_id")
    private Integer expenseClaim;
}
