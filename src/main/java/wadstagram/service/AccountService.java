package wadstagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wadstagram.domain.Account;
import wadstagram.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(String username, String password, String auth) {
        return accountRepository.save(new Account(username, passwordEncoder.encode(password), auth));
    }

    public Account getUserByName(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account getUserById(Long id) {
        return accountRepository.findOne(id);
    }
}
