package wadstagram.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wadstagram.domain.AccountStatus;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long> {
    
    @Cacheable(value = "accountStatuses")
    public AccountStatus findByName(String name);
}
