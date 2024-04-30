package com.bluering.blueringAssignment.DTO;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeDTO {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private Integer departmentId;
    private String departmentName;
    List<ExpensetypeDTO> expenseTypeDTOList;
}
