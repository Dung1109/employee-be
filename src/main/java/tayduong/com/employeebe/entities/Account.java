package tayduong.com.employeebe.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import tayduong.com.employeebe.enums.Status;

@Getter
@Setter
@Entity
@Table(name = "account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "account", unique = true)
    private String account;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Builder.Default
    private String role = "USER";
}