package wadstagram.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Image extends AbstractPersistable<Long> {

    @OneToMany(mappedBy = "image")
    private List<Heart> hearts;
    
    private String name;
    
    private String type;
    
    private Long length;
    
    @OneToOne
    private ImageBytes bytes;
    
    @OneToMany(mappedBy = "image")
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments != null ? comments : new ArrayList<>();
    }

    public Long getLength() {
        return length;
    }

    public List<Heart> getHearts() {
        return hearts != null ? hearts : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public ImageBytes getBytes() {
        return bytes;
    }

    public void setBytes(ImageBytes bytes) {
        this.bytes = bytes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setHearts(List<Heart> hearts) {
        this.hearts = hearts;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
