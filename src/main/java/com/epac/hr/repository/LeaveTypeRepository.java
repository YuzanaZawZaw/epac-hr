package com.epac.hr.repository;

import com.epac.hr.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
    Optional<LeaveType> findByLeaveCode(String leaveCode);
    Optional<LeaveType> findByLeaveTypeName(String leaveTypeName);
}
