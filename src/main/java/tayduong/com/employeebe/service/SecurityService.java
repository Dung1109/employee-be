package tayduong.com.employeebe.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tayduong.com.employeebe.entities.Account;
import tayduong.com.employeebe.entities.Employee;

@Service
@Slf4j
public class SecurityService {

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

        Object principal = authentication.getClass();
        log.error("Principal: {}", principal);
        log.error("Principal class: {}", principal.toString());

        if (!(principal instanceof Account)) {
            log.error("Principal is not an Account instance");
            return false;
        }

        Account account = (Account) principal;
        log.error("Checking access for user: {}", account.getUsername());
        log.error("User role: {}", account.getRole());
        log.error("User's employee: {}", account.getEmployee());

        if (account.getRole().equals("ADMIN")) {
            log.error("User is admin, granting access");
            return true;
        }

        Employee employeeOfAccount = account.getEmployee();
        boolean hasAccess = employeeOfAccount != null &&
                employeeOfAccount.getId().equals(employeeId);

        log.error("Access granted: {}", hasAccess);
        return hasAccess;
    }
}
