package com.bluering.blueringAssignment.Repositories;

import com.bluering.blueringAssignment.Entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository  extends JpaRepository<EmployeeEntity, Integer> {

}
