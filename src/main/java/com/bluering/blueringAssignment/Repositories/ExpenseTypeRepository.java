package com.bluering.blueringAssignment.Repositories;

import com.bluering.blueringAssignment.Entities.ExpensetypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseTypeRepository extends JpaRepository<ExpensetypeEntity, Integer> {

}
