package com.bluering.blueringAssignment.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;

@Setter
@Getter
@Entity
@Table(name = "expenseclaimentry", schema = "assignment", catalog = "")
public class ExpenseclaimentryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id@Column(name = "id")
    private Integer id;
    @Basic@Column(name = "date")
    private Date date;
    @Basic@Column(name = "description")
    private String description;
    @Basic@Column(name = "total")
    private BigDecimal total;
    @Basic@Column(name="expense_type_id")
    private Integer expenseTypeId;
    @Basic@Column(name="expense_claim_id")
    private Integer expenseClaimId;
}
