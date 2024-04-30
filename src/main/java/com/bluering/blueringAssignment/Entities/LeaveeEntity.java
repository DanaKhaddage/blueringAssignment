package com.bluering.blueringAssignment.Entities;

import jakarta.persistence.*;
import jdk.vm.ci.meta.Local;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "leavee", schema = "assignment", catalog = "")
public class LeaveeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id@Column(name = "id")
    private Integer id;
    @Basic@Column(name = "from_date")
    private LocalDate fromDate;
    @Basic@Column(name = "to_date")
    private LocalDate toDate;
    @Basic@Column(name = "number_of_days")
    private int numberOfDays;
    @Basic@Column(name = "note")
    private String note;
    @Basic@Column(name="leave_type")
    private Integer leaveTypeId;
}
