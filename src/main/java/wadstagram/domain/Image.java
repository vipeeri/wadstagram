package wadstagram.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Image extends AbstractPersistable<Long> {

    @OneToMany(mappedBy = "image")
    private List<Like> likes;
    
    private String name;
    
    private String type;
    
    private Long length;
    
    @OneToMany(mappedBy = "image")
    private List<Comment> comments;
    
    @Lob
    private byte[] data;
}
