package com.epac.hr.service;

import com.epac.hr.entity.Employee;
import com.epac.hr.entity.Employee.EmployeeStatus;
import com.epac.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }
    
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    
    public Optional<Employee> getEmployeeByCode(String code) {
        return employeeRepository.findByEmployeeCode(code);
    }
    
    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    
    public List<Employee> getEmployeesByStatus(EmployeeStatus status) {
        return employeeRepository.findByEmployeeStatus(status);
    }
    
    public List<Employee> getEmployeesByDepartment(Integer departmentId) {
        return employeeRepository.findByDepartmentDepartmentId(departmentId);
    }
    
    public List<Employee> searchEmployeeByName(String name) {
        return employeeRepository.searchByFullName(name);
    }
    
    public List<Employee> getEmployeesByStatusAndDepartment(EmployeeStatus status, Integer departmentId) {
        return employeeRepository.findByStatusAndDepartment(status, departmentId);
    }
    
    public Employee updateEmployee(Integer id, Employee employee) {
        Optional<Employee> existing = employeeRepository.findById(id);
        if (existing.isPresent()) {
            Employee emp = existing.get();
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setFullName(employee.getFullName());
            emp.setEmail(employee.getEmail());
            emp.setPhoneNo(employee.getPhoneNo());
            emp.setHomeAddress(employee.getHomeAddress());
            emp.setEmployeeStatus(employee.getEmployeeStatus());
            emp.setHourlyRate(employee.getHourlyRate());
            return employeeRepository.save(emp);
        }
        return null;
    }
    
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
    
    public long getTotalEmployees() {
        return employeeRepository.count();
    }
    
    public long getActiveEmployeesCount() {
        return employeeRepository.findByEmployeeStatus(EmployeeStatus.ACTIVE).size();
    }
}
