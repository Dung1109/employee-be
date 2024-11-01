package tayduong.com.employeebe.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tayduong.com.employeebe.dto.EmployeeDto;
import tayduong.com.employeebe.entities.Employee;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT new tayduong.com.employeebe.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.gender, e.dateOfBirth, e.phone, e.address, e.departmentName, e.remark, a.account, a.status) FROM Employee e JOIN Account a ON e.id = a.employee.id WHERE e.id = :id")
    Optional<EmployeeDto> findByEmployeeId(Integer id);

    @Query("SELECT new tayduong.com.employeebe.dto.EmployeeDto(e.id, e.firstName, e.lastName, e.gender, e.dateOfBirth, e.phone, e.address, e.departmentName, e.remark, a.account, a.status) FROM Employee e JOIN Account a ON e.id = a.employee.id")
    Page<EmployeeDto> findAllEmployeesWithPagination(Pageable pageable);
}