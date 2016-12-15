package wadstagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadstagram.domain.AccountStatus;
import wadstagram.repository.AccountStatusRepository;

@Service
public class AccountStatusService {
    
    @Autowired
    AccountStatusRepository accountStatusRepository;
    
    public AccountStatus getStatus(String status) {
        AccountStatus found = accountStatusRepository.findByName(status);
        return found != null ? found : accountStatusRepository.save(new AccountStatus(status));
    }    
}
