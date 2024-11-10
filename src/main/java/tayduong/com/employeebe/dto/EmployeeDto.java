package tayduong.com.employeebe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import tayduong.com.employeebe.entities.Employee;
import tayduong.com.employeebe.enums.Gender;
import tayduong.com.employeebe.enums.Status;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Employee}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    Integer id;
    @NotNull
    String firstName;
    String lastName;
    Gender gender;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
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