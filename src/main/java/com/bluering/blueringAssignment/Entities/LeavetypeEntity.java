package com.bluering.blueringAssignment.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "leavetype", schema = "assignment", catalog = "")
public class LeavetypeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id@Column(name = "id")
    private Integer id;
    @Basic@Column(name = "name")
    private String name;
}
