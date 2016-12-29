package wadstagram.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wadstagram.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    
    @Cacheable("accounts")
    public Account findByUsername(String username);
}
