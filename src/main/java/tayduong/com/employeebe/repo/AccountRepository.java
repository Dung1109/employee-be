package tayduong.com.employeebe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tayduong.com.employeebe.entities.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByEmployeeId(Integer id);

    Optional<Account> findByAccount(String account);
}