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
@Table(name = "expenseclaim", schema = "assignment", catalog = "")
public class ExpenseclaimEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id@Column(name = "id")
    private int id;
    @Basic@Column(name = "date")
    private LocalDate date;
    @Basic@Column(name = "description")
    private String description;
    @Basic@Column(name = "total")
    private Double total;
    @Basic@Column(name = "status")
    private String status;
    @Basic@Column(name="employee_id")
    private Integer employeeId;
}
