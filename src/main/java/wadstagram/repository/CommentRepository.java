package wadstagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wadstagram.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
