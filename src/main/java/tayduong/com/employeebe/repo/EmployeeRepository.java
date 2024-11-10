package tayduong.com.employeebe.repo;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tayduong.com.employeebe.dto.EmployeeDto;
import tayduong.com.employeebe.entities.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Cacheable(value = "employee:id")
    @Query("SELECT new tayduong.com.employeebe.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.gender, e.dateOfBirth, e.phone, e.address, e.departmentName, e.remark, a.account, a.status, a.email, a.password) FROM Employee e LEFT JOIN Account a ON e.id = a.employee.id WHERE e.id = :id")
    Optional<EmployeeDto> findByEmployeeId(Integer id);

    @Query("SELECT new tayduong.com.employeebe.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.gender, e.dateOfBirth, e.phone, e.address, e.departmentName, e.remark, null, null, null , null ) FROM Employee e ")
    Page<EmployeeDto> findAllEmployeesWithPagination(Pageable pageable);


    @Query("SELECT new tayduong.com.employeebe.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.gender, e.dateOfBirth, e.phone, e.address, e.departmentName, e.remark, null, null, null , null ) FROM Employee e WHERE " +
            "CASE WHEN :filterBy = 'fullName' THEN LOWER(CONCAT(e.firstName, ' ', e.lastName)) LIKE LOWER(CONCAT('%', :filterValue, '%')) " +
            "     WHEN :filterBy = 'address' THEN LOWER(e.address) LIKE LOWER(CONCAT('%', :filterValue, '%')) " +
            "     WHEN :filterBy = 'department' THEN LOWER(e.departmentName) LIKE LOWER(CONCAT('%', :filterValue, '%')) " +
            "     ELSE 1=1 END")
    Page<EmployeeDto> findEmployeesWithFilter(String filterBy, String filterValue, Pageable pageable);
}