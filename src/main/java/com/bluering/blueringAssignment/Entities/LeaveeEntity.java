package com.bluering.blueringAssignment.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "leavee", schema = "assignment", catalog = "")
public class LeaveeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id@Column(name = "id")
    private Integer id;
    @Basic@Column(name = "from_Date")
    private LocalDate fromDate;
    @Basic@Column(name = "to_Date")
    private LocalDate toDate;
    @Basic@Column(name = "number_of_days")
    private Integer numberOfDays;
    @Basic@Column(name = "note")
    private String note;
    @Basic@Column(name="employee_id")
    private Integer employeeId;
    @Basic@Column(name="leave_type")
    private Integer leaveType;

    // Additional fields for leave type name and employee name
    @Transient // Marked as transient to indicate it's not a persistent field
    private String leaveTypeName;

    @Transient // Marked as transient to indicate it's not a persistent field
    private String employeeName;
}
