package com.bluering.blueringAssignment.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;
@Setter
@Getter
@Entity
@Table(name = "employee", schema = "assignment", catalog = "")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id@Column(name = "id")
    private int id;
    @Basic@Column(name = "name")
    private String name;
    @Basic@Column(name = "email")
    private String email;
    @Basic@Column(name = "address")
    private String address;
    @Basic@Column(name="department_id")
    private Integer departmentId;
}
