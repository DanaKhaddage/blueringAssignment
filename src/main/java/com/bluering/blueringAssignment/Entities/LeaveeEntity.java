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
    @Basic@Column(name = "fromDate")
    private LocalDate fromDate;
    @Basic@Column(name = "toDate")
    private LocalDate toDate;
    @Basic@Column(name = "numberOfDays")
    private Integer numberOfDays;
    @Basic@Column(name = "note")
    private String note;
    @Basic@Column(name="employeeId")
    private Integer employeeId;
    @Basic@Column(name="leaveType")
    private Integer leaveType;
}
