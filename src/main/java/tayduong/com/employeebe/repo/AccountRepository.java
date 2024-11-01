package tayduong.com.employeebe.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tayduong.com.employeebe.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}