package tayduong.com.employeebe.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tayduong.com.employeebe.dto.EmployeeDto;
import tayduong.com.employeebe.entities.Account;
import tayduong.com.employeebe.entities.Employee;
import tayduong.com.employeebe.repo.EmployeeRepository;

import java.util.Optional;

@Service
@Slf4j
public class SecurityService {
    private final EmployeeRepository employeeRepository;

    public SecurityService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

//    public boolean isEmployeeOwner(Integer employeeId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication.getPrincipal() instanceof Account)) {
//            return false;
//        }
//
//        Account account = (Account) authentication.getPrincipal();
//        return account.getEmployee() != null &&
//                account.getEmployee().getId().equals(employeeId);
//    }

    public boolean isEmployeeOwner(Integer employeeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.error("Checking access for employeeId: {}", employeeId);

        if (authentication == null) {
            log.error("Authentication is null");
            return false;
        }

        if (!authentication.isAuthenticated()) {
            log.error("User is not authenticated");
            return false;
        }

        Object principal = authentication.getPrincipal();
        log.debug("Principal: {}", principal);

        Optional<EmployeeDto> emp = employeeRepository.findByEmployeeId(employeeId);
        log.debug("Principal class true?: {}", principal.toString().equals(emp.get().getAccount()));

//        Account account = (Account) principal;
//        log.error("Checking access for user: {}", account.getUsername());
//        log.error("User role: {}", account.getRole());
//        log.error("User's employee: {}", account.getEmployee());

//        if (account.getRole().equals("ADMIN")) {
//            log.error("User is admin, granting access");
//            return true;
//        }

        if (principal.toString().equals(emp.get().getAccount())) {
            log.debug("User is owner of the employee, granting access");
            return true;
        }

        return false;
    }
}
