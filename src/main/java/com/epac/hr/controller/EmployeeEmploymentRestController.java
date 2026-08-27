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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // DTO returned to the UI to avoid lazy-loading / circular reference issues
    public static class EmployeeEmploymentDto {
        public Integer employmentId;
        public String companyName;
        public String departmentName;
        public String positionName;
        public String startDate;
        public String endDate;
        public String status;
    }

    // compact request payload for creating employment
    public static class EmploymentRequest {
        public String employeeCode; // or employeeId as string
        public Integer positionId;
        public String startDate; // ISO yyyy-MM-dd
        public String endDate;   // optional
        public String status;    // ACTIVE | TERMINATED | RESIGNED
    }

    @GetMapping(params = "employeeId")
    public ResponseEntity<List<EmployeeEmploymentDto>> listByEmployee(@RequestParam String employeeId) {
        // resolve employee
        Optional<Employee> empOpt = Optional.empty();
        try {
            Integer id = Integer.valueOf(employeeId);
            empOpt = employeeRepository.findById(id);
        } catch (NumberFormatException ignored) {}
        if (empOpt.isEmpty()) {
            empOpt = employeeRepository.findByEmployeeCode(employeeId);
        }
        if (empOpt.isEmpty()) return ResponseEntity.ok(List.of());
        Integer empPk = empOpt.get().getEmployeeId();
        List<EmployeeEmployment> list = employmentService.findByEmployee(empPk);
        List<EmployeeEmploymentDto> dtos = list.stream().map(e -> {
            EmployeeEmploymentDto d = new EmployeeEmploymentDto();
            d.employmentId = e.getEmploymentId();
            d.startDate = e.getStartDate() != null ? e.getStartDate().toString() : null;
            d.endDate = e.getEndDate() != null ? e.getEndDate().toString() : null;
            d.status = e.getStatus() != null ? e.getStatus().name() : null;
            if (e.getPosition() != null) {
                d.positionName = e.getPosition().getPositionName();
                if (e.getPosition().getDepartment() != null) d.departmentName = e.getPosition().getDepartment().getDepartmentName();
                if (e.getPosition().getDepartment() != null && e.getPosition().getDepartment().getCompany() != null)
                    d.companyName = e.getPosition().getDepartment().getCompany().getCompanyName();
            }
            return d;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<EmployeeEmploymentDto> create(@RequestBody EmploymentRequest req) {
        if (req == null || (req.employeeCode == null && req.positionId == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid payload");
        }

        // resolve employee by code or numeric id
        Optional<Employee> empOpt = Optional.empty();
        try {
            Integer id = Integer.valueOf(req.employeeCode);
            empOpt = employeeRepository.findById(id);
        } catch (Exception ignored) {}
        if (empOpt.isEmpty()) empOpt = employeeRepository.findByEmployeeCode(req.employeeCode);
        if (empOpt.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee not found: " + req.employeeCode);

        Employee emp = empOpt.get();

        // resolve position
        Position pos = positionRepository.findById(req.positionId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Position not found: " + req.positionId));

        EmployeeEmployment ee = new EmployeeEmployment();
        ee.setEmployee(emp);
        ee.setPosition(pos);
        ee.setStartDate(req.startDate != null ? LocalDate.parse(req.startDate) : LocalDate.now());
        ee.setEndDate(req.endDate != null && !req.endDate.isBlank() ? LocalDate.parse(req.endDate) : null);
        try {
            ee.setStatus(req.status != null ? EmployeeEmployment.EmploymentStatus.valueOf(req.status) : EmployeeEmployment.EmploymentStatus.ACTIVE);
        } catch (Exception ex) {
            ee.setStatus(EmployeeEmployment.EmploymentStatus.ACTIVE);
        }

        EmployeeEmployment saved = employmentService.save(ee);
        EmployeeEmploymentDto d = new EmployeeEmploymentDto();
        d.employmentId = saved.getEmploymentId();
        d.startDate = saved.getStartDate() != null ? saved.getStartDate().toString() : null;
        d.endDate = saved.getEndDate() != null ? saved.getEndDate().toString() : null;
        d.status = saved.getStatus() != null ? saved.getStatus().name() : null;
        if (saved.getPosition() != null) {
            d.positionName = saved.getPosition().getPositionName();
            if (saved.getPosition().getDepartment() != null) d.departmentName = saved.getPosition().getDepartment().getDepartmentName();
            if (saved.getPosition().getDepartment() != null && saved.getPosition().getDepartment().getCompany() != null)
                d.companyName = saved.getPosition().getDepartment().getCompany().getCompanyName();
        }
        return ResponseEntity.ok(d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        employmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
