package wadstagram.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wadstagram.domain.Account;
import wadstagram.domain.AccountStatus;
import wadstagram.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountStatusService accountStatusService;

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(String username, String password, String auth) {
        return accountRepository.save(new Account(username, passwordEncoder.encode(password), accountStatusService.getStatus(auth)));
    }

    public Account getUserByName(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account getUserById(Long id) {
        return accountRepository.findOne(id);
    }
}
