package wadstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wadstagram.domain.Heart;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    
}
