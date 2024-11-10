package tayduong.com.employeebe.entities;

import jakarta.persistence.*;
import lombok.*;
import tayduong.com.employeebe.enums.Gender;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Employee implements Serializable {

    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "remark", nullable = false)
    private String remark;

}
