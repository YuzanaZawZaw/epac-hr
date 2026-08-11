package com.epac.hr.service;

import com.epac.hr.entity.Department;
import com.epac.hr.entity.Position;
import com.epac.hr.repository.DepartmentRepository;
import com.epac.hr.repository.PositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;

    public PositionService(PositionRepository positionRepository, DepartmentRepository departmentRepository) {
        this.positionRepository = positionRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Position> findByDepartment(Integer departmentId) {
        return positionRepository.findByDepartmentDepartmentId(departmentId);
    }

    @Transactional
    public Position createForDepartment(Integer departmentId, Position position) {
        Department dept = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + departmentId));
        position.setDepartment(dept);
        return positionRepository.save(position);
    }

    @Transactional
    public Position update(Integer id, Position dto) {
        Position existing = positionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Position not found: " + id));
        existing.setPositionName(dto.getPositionName());
        existing.setPositionCode(dto.getPositionCode());
        if (dto.getDepartment() != null) existing.setDepartment(dto.getDepartment());
        return positionRepository.save(existing);
    }

    @Transactional
    public void delete(Integer id) {
        positionRepository.deleteById(id);
    }
}
