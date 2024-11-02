package tayduong.com.employeebe.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import tayduong.com.employeebe.entities.Employee;
import tayduong.com.employeebe.enums.Gender;
import tayduong.com.employeebe.enums.Status;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Employee}
 */
@Value
public class EmployeeDto implements Serializable {
    @NotNull
    Integer id;
    @NotNull
    String firstName;
    String lastName;
    Gender gender;
    LocalDate dateOfBirth;
    String phone;
    String address;
    String departmentName;
    String remark;
    String account;
    Status status;

    @Email
    String email;
    String password;
}