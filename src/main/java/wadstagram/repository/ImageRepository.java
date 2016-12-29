package wadstagram.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wadstagram.domain.Account;
import wadstagram.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
    public List<Image> findByOwner(Account owner);
}
