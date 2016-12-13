package wadstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wadstagram.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
}
