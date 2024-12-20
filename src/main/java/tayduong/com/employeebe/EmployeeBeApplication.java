package tayduong.com.employeebe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import tayduong.com.employeebe.entities.Account;
import tayduong.com.employeebe.entities.Employee;
import tayduong.com.employeebe.enums.Status;
import tayduong.com.employeebe.repo.AccountRepository;
import tayduong.com.employeebe.repo.EmployeeRepository;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class EmployeeBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeBeApplication.class, args);
    }

    @Bean
    CommandLineRunner init(AccountRepository accountRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            Employee emp = employeeRepository.save(Employee.builder().firstName("tayduong").lastName("tayduong").dateOfBirth(LocalDate.of(2021,10,2)).address("HN").remark("ADASD").build());
            accountRepository.save(Account.builder().account("admin123").password(passwordEncoder.encode("123456")).status(Status.ACTIVE).role("USER").employee(emp).build());
        };
    }

}
