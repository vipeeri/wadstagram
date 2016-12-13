package wadstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wadstagram.domain.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    
}
