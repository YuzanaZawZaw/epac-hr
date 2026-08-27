package com.epac.hr.controller;

import com.epac.hr.entity.Company;
import com.epac.hr.entity.Department;
import com.epac.hr.entity.Position;
import com.epac.hr.entity.EmployeeEmployment;
import com.epac.hr.entity.Employee;
import com.epac.hr.repository.CompanyRepository;
import com.epac.hr.repository.DepartmentRepository;
import com.epac.hr.repository.PositionRepository;
import com.epac.hr.repository.EmployeeRepository;
import com.epac.hr.service.EmployeeEmploymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee-employment")
public class EmployeeEmploymentRestController {

    private final CompanyRepository companyRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final EmployeeRepository employeeRepository;
    private final EmployeeEmploymentService employmentService;

    public EmployeeEmploymentRestController(CompanyRepository companyRepository,
                                            DepartmentRepository departmentRepository,
                                            PositionRepository positionRepository,
                                            EmployeeRepository employeeRepository,
                                            EmployeeEmploymentService employmentService) {
        this.companyRepository = companyRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.employeeRepository = employeeRepository;
        this.employmentService = employmentService;
    }

    @GetMapping("/companies")
    public List<Company> listCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/companies/{companyId}/departments")
    public ResponseEntity<List<Department>> listDepartments(@PathVariable Integer companyId) {
        List<Department> list = departmentRepository.findByCompanyIdCompanyId(companyId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/departments/{departmentId}/positions")
    public ResponseEntity<List<Position>> listPositions(@PathVariable Integer departmentId) {
        List<Position> list = positionRepository.findByDepartmentDepartmentId(departmentId);
        return ResponseEntity.ok(list);
    }

    @GetMapping(params = "employeeId")
    public ResponseEntity<List<EmployeeEmployment>> listByEmployee(@RequestParam String employeeId) {
        // employeeId may be code or numeric id; try to resolve by code or id
        Optional<Employee> empOpt = Optional.empty();
        try {
            Integer id = Integer.valueOf(employeeId);
            empOpt = employeeRepository.findById(id);
        } catch (NumberFormatException ignored) {}
        if (empOpt.isEmpty()) {
            empOpt = employeeRepository.findByEmployeeCode(employeeId);
        }
        if (empOpt.isEmpty()) return ResponseEntity.ok(List.of());
        List<EmployeeEmployment> list = employmentService.findByEmployee(empOpt.get().getEmployeeId());
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<EmployeeEmployment> create(@RequestBody EmployeeEmployment e) {
        // Expect employee.employee.employeeId or employeeId present
        EmployeeEmployment saved = employmentService.save(e);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEmployment> update(@PathVariable Integer id, @RequestBody EmployeeEmployment e) {
        e.setEmploymentId(id);
        EmployeeEmployment saved = employmentService.save(e);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        employmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
