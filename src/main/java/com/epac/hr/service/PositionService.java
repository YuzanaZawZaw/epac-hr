package com.epac.hr.service;

import com.epac.hr.entity.Position;
import com.epac.hr.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PositionService {
    
    @Autowired
    private PositionRepository positionRepository;
    
    public Position savePosition(Position position) {
        Objects.requireNonNull(position, "position must not be null");
        Position saved = positionRepository.save(position);
        return Objects.requireNonNull(saved, "saved position must not be null");
    }
    
    public Optional<Position> getPositionById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(positionRepository, "positionRepository must not be null");
        return positionRepository.findById(id);
    }
    
    public List<Position> getAllPositions() {
        Objects.requireNonNull(positionRepository, "positionRepository must not be null");
        return positionRepository.findAll();
    }
    
    public Optional<Position> getPositionByCode(String code) {
        Objects.requireNonNull(code, "code must not be null");
        return positionRepository.findByPositionCode(code);
    }
    
    public Optional<Position> getPositionByName(String name) {
        Objects.requireNonNull(name, "name must not be null");
        return positionRepository.findByPositionName(name);
    }
    
    public List<Position> getPositionsByDepartment(Integer departmentId) {
        Objects.requireNonNull(departmentId, "departmentId must not be null");
        return positionRepository.findByDepartmentDepartmentId(departmentId);
    }
    
    public Position updatePosition(Integer id, Position position) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(position, "position must not be null");
        Objects.requireNonNull(positionRepository, "positionRepository must not be null");
        Optional<Position> existing = positionRepository.findById(id);
        if (existing.isPresent()) {
            Position pos = existing.get();
            pos.setPositionName(position.getPositionName());
            pos.setPositionCode(position.getPositionCode());
            pos.setDepartment(position.getDepartment());
            return positionRepository.save(pos);
        }
        return null;
    }
    
    public void deletePosition(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(positionRepository, "positionRepository must not be null");
        positionRepository.deleteById(id);
    }
}
