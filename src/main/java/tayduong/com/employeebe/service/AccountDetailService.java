package tayduong.com.employeebe.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tayduong.com.employeebe.repo.AccountRepository;

@Service
public class AccountDetailService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AccountDetailService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByAccount(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}
