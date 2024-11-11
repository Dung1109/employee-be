package tayduong.com.employeebe.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tayduong.com.employeebe.enums.Status;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account implements UserDetails  {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == Status.ACTIVE;
    }
}