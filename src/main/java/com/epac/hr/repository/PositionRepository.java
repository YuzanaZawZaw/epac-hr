package com.epac.hr.repository;

import com.epac.hr.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Optional<Position> findByPositionCode(String positionCode);
    Optional<Position> findByPositionName(String positionName);
    List<Position> findByDepartmentDepartmentId(Integer departmentId);
}
