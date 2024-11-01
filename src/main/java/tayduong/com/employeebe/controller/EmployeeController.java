package tayduong.com.employeebe.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tayduong.com.employeebe.dto.EmployeeDto;
import tayduong.com.employeebe.mapper.EmployeeMapper;
import tayduong.com.employeebe.repo.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getEmployee(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<EmployeeDto> pagedResult = employeeRepository.findAllEmployeesWithPagination(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("employees", pagedResult.getContent());
        response.put("currentPage", pagedResult.getNumber());
        response.put("totalItems", pagedResult.getTotalElements());
        response.put("totalPages", pagedResult.getTotalPages());
        response.put("currentSort", sortBy);

        return ResponseEntity.ok(response);
    }
}
