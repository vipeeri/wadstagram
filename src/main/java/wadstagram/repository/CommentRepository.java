package wadstagram.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wadstagram.domain.Comment;
import wadstagram.domain.Image;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    public List<Comment> findByImage(Image image);
}
