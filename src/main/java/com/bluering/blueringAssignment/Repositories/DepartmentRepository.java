package com.bluering.blueringAssignment.Repositories;

import com.bluering.blueringAssignment.Entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository  extends JpaRepository<DepartmentEntity, Integer> {

}
