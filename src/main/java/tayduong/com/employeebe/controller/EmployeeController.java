package tayduong.com.employeebe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tayduong.com.employeebe.dto.EmployeeDto;
import tayduong.com.employeebe.mapper.EmployeeMapper;
import tayduong.com.employeebe.repo.EmployeeRepository;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository
    ) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/test/employee")
    public ResponseEntity<List<EmployeeDto>> getEmployee() {
        return ResponseEntity.ok(employeeMapper.toDto(employeeRepository.findAll()));
    }

    @GetMapping("/test/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
        return employeeRepository.findByEmployeeId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
