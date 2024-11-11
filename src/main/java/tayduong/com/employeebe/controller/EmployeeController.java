package tayduong.com.employeebe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tayduong.com.employeebe.dto.AccountCredentials;
import tayduong.com.employeebe.dto.EmployeeDto;
import tayduong.com.employeebe.entities.Account;
import tayduong.com.employeebe.entities.Employee;
import tayduong.com.employeebe.entities.Image;
import tayduong.com.employeebe.mapper.EmployeeMapper;
import tayduong.com.employeebe.repo.AccountRepository;
import tayduong.com.employeebe.repo.EmployeeRepository;
import tayduong.com.employeebe.service.EmployeeService;
import tayduong.com.employeebe.service.ImageService;
import tayduong.com.employeebe.service.JwtService;
import tayduong.com.employeebe.service.SecurityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/employee")
@Slf4j
//@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    //    private final ImageService imageService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeService employeeService;
    private final ImageService imageService;
    private final SecurityService securityService;


    public EmployeeController(EmployeeMapper employeeMapper, EmployeeRepository employeeRepository, AccountRepository accountRepository, /*ImageService imageService,*/ AuthenticationManager authenticationManager, JwtService jwtService, PasswordEncoder passwordEncoder, EmployeeService employeeService, ImageService imageService, SecurityService securityService) {
        this.employeeMapper = employeeMapper;
        this.employeeRepository = employeeRepository;
        this.accountRepository = accountRepository;
//        this.imageService = imageService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.employeeService = employeeService;
        this.imageService = imageService;
        this.securityService = securityService;
    }

    @GetMapping("/test/employee")
    public ResponseEntity<List<EmployeeDto>> getEmployee() {
        return ResponseEntity.ok(employeeMapper.toDto(employeeRepository.findAll()));
    }

    @GetMapping("/{id}")
//    @PostAuthorize("@securityService.isEmployeeOwner(#id)")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer id) {
        System.out.println("Attempting to access employee with ID: " + id);
        System.out.println("Security check result: " + securityService.isEmployeeOwner(id));
        return employeeRepository.findByEmployeeId(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getEmployee(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "") String filterBy,
            @RequestParam(defaultValue = "") String filterValue) {
        try {
            Map<String, Object> response = employeeService.getEmployeeData(
                    pageNo, pageSize, sortBy, filterBy, filterValue);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error fetching employees", e);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Failed to fetch employees: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeRepository.save(employeeMapper.toEntity(employeeDto));
        Account account = accountRepository.save(Account.builder().account(employeeDto.getAccount()).email(employeeDto.getEmail()).password(passwordEncoder.encode(employeeDto.getPassword())).status(employeeDto.getStatus()).employee(employee).role("USER").build());
        employeeService.clearEmployeeCache();
        return ResponseEntity.ok(employeeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeMapper.toEntity(employeeDto);
        employee.setId(id);

        Account account = accountRepository.findByEmployeeId(id);

        account.setAccount(employeeDto.getAccount());
        account.setEmail(employeeDto.getEmail());
        account.setPassword(employeeDto.getPassword());
        account.setStatus(employeeDto.getStatus());

        accountRepository.save(account);
        Employee save = employeeRepository.save(employee);

        return ResponseEntity.ok(employeeMapper.toDto(save));
    }

    @PostMapping("/image/upload")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        Image image = imageService.uploadImage(file, userId);
        return ResponseEntity.ok(image);
    }

    @GetMapping("/image/{userId}")
    public ResponseEntity<List<Image>> getUserImages(@PathVariable String userId) {
        List<Image> images = imageService.getUserImages(userId);
        return ResponseEntity.ok(images);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountCredentials accountCredentials) {
        UsernamePasswordAuthenticationToken creds = new UsernamePasswordAuthenticationToken(accountCredentials.account(), accountCredentials.password());
        Authentication auth = authenticationManager.authenticate(creds);
        // Generate token
        String jwts = jwtService.getToken(auth.getName());
        // Build response with the generated token
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer" + jwts).header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization").build();
    }
}
