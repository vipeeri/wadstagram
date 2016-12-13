package wadstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wadstagram.domain.AccountStatus;

@Repository
public interface AccountStatusRepository extends JpaRepository<AccountStatus, Long> {
    
    public AccountStatus findByStatus(String status);
}
