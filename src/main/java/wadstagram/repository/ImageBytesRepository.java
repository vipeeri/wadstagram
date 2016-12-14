package wadstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wadstagram.domain.ImageBytes;

@Repository
public interface ImageBytesRepository extends JpaRepository<ImageBytes, Long> {
    
}
