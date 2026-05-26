package com.epac.hr.service;

import com.epac.hr.entity.Position;
import com.epac.hr.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    
    @Autowired
    private PositionRepository positionRepository;
    
    public Position savePosition(Position position) {
        return positionRepository.save(position);
    }
    
    public Optional<Position> getPositionById(Integer id) {
        return positionRepository.findById(id);
    }
    
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }
    
    public Optional<Position> getPositionByCode(String code) {
        return positionRepository.findByPositionCode(code);
    }
    
    public Optional<Position> getPositionByName(String name) {
        return positionRepository.findByPositionName(name);
    }
    
    public List<Position> getPositionsByDepartment(Integer departmentId) {
        return positionRepository.findByDepartmentDepartmentId(departmentId);
    }
    
    public Position updatePosition(Integer id, Position position) {
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
        positionRepository.deleteById(id);
    }
}
