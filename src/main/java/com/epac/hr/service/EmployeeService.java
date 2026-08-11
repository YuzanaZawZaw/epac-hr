package com.epac.hr.service;

import com.epac.hr.entity.Employee;
//import com.epac.hr.entity.Employee.EmployeeStatus;
import com.epac.hr.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Objects;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public Employee saveEmployee(Employee employee) {
        Objects.requireNonNull(employee, "Employee cannot be null");
        return employeeRepository.save(employee);
    }
    
    public Optional<Employee> getEmployeeById(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        return employeeRepository.findById(id);
    }
    
    public List<Employee> getAllEmployees() {
        Objects.requireNonNull(employeeRepository, "employeeRepository must not be null");
        return employeeRepository.findAll();
    }
    
    public Optional<Employee> getEmployeeByCode(String code) {
        Objects.requireNonNull(code, "code must not be null");
        return employeeRepository.findByEmployeeCode(code);
    }
    
    public Optional<Employee> getEmployeeByEmail(String email) {
        Objects.requireNonNull(email, "email must not be null");
        return employeeRepository.findByEmail(email);
    }
    
    // public List<Employee> getEmployeesByStatus(EmployeeStatus status) {
    //     Objects.requireNonNull(status, "status must not be null");
    //     return employeeRepository.findByEmployeeStatus(status);
    // }
    
    // public List<Employee> getEmployeesByDepartment(Integer departmentId) {
    //     Objects.requireNonNull(departmentId, "departmentId must not be null");
    //     return employeeRepository.findByDepartmentDepartmentId(departmentId);
    // }
    
    public List<Employee> searchEmployeeByName(String name) {
        Objects.requireNonNull(name, "name must not be null");
        return employeeRepository.searchByFullName(name);
    }
    
    // public List<Employee> getEmployeesByStatusAndDepartment(EmployeeStatus status, Integer departmentId) {
    //     Objects.requireNonNull(status, "status must not be null");
    //     Objects.requireNonNull(departmentId, "departmentId must not be null");
    //     return employeeRepository.findByStatusAndDepartment(status, departmentId);
    // }
    
    public Employee updateEmployee(Integer id, Employee employee) {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(employee, "employee must not be null");
        Optional<Employee> existing = employeeRepository.findById(id);
        if (existing.isPresent()) {
            Employee emp = existing.get();
            emp.setFirstName(employee.getFirstName());
            emp.setLastName(employee.getLastName());
            emp.setFullName(employee.getFullName());
            emp.setEmail(employee.getEmail());
            emp.setPhoneNo(employee.getPhoneNo());
            emp.setHomeAddress(employee.getHomeAddress());
            //emp.setEmployeeStatus(employee.getEmployeeStatus());
            emp.setHourlyRate(employee.getHourlyRate());
            return employeeRepository.save(emp);
        }
        return null;
    }
    
    public void deleteEmployee(Integer id) {
        Objects.requireNonNull(id, "id must not be null");
        employeeRepository.deleteById(id);
    }
    
    public long getTotalEmployees() {
        Objects.requireNonNull(employeeRepository, "employeeRepository must not be null");
        return employeeRepository.count();
    }
    
    // public long getActiveEmployeesCount() {
    //     Objects.requireNonNull(employeeRepository, "employeeRepository must not be null");
    //     return employeeRepository.findByEmployeeStatus(EmployeeStatus.ACTIVE).size();
    // }
}
