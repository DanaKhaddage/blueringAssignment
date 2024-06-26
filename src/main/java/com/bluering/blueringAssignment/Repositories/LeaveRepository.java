package com.bluering.blueringAssignment.Repositories;

import com.bluering.blueringAssignment.Entities.LeaveeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveeEntity, Integer> {
      //Page<LeaveeEntity> findByEmployeeId(Integer employeeId,Pageable pageable);
      //Page<LeaveeEntity> findByLeaveType(Integer type,Pageable pageable);
      Page<LeaveeEntity> findAll(Pageable pageable);
      Page<LeaveeEntity> findByLeaveType(Integer leaveType, Pageable pageable);
      Page<LeaveeEntity> findByEmployeeId(Integer employeeId, Pageable pageable);
      Page<LeaveeEntity> findByLeaveTypeAndEmployeeId(Integer leaveTypeId, Integer employeeId, Pageable pageable);
      List<LeaveeEntity> findByEmployeeIdAndFromDateBetween(Integer employeeId, LocalDate from, LocalDate to);
}
