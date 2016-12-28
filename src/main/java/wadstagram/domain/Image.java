package wadstagram.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Image extends AbstractPersistable<Long> {

    @ManyToOne
    private Account owner;

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Account getOwner() {
        return owner;
    }
    
    @OneToMany
    private List<Account> likers;
    
    private String name;
    
    private String description;
    
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

    public List<Account> getLikers() {
        return likers != null ? likers : new ArrayList<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setBytes(ImageBytes bytes) {
        this.bytes = bytes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setLikers(List<Account> likers) {
        this.likers = likers;
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
