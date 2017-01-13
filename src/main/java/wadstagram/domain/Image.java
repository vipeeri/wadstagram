package wadstagram.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotEmpty;
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

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Account> likers;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @Temporal(TemporalType.DATE)
    private Date createdOn;

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    @NotEmpty
    private String type;

    private Long length;

    @OneToOne(fetch = FetchType.LAZY)
    private ImageBytes bytes;

    @OneToMany(mappedBy = "image", fetch = FetchType.EAGER)
    private List<Comment> comments;

    public List<Comment> getComments() {
        if (this.comments != null) {
            return this.comments;
        } else {
            return new ArrayList<>();
        }
    }

    public Long getLength() {
        return length;
    }

    public Set<Account> getLikers() {
        if (this.likers != null) {
            return this.likers;
        } else {
            return new HashSet<>();
        }
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

    public void setLikers(Set<Account> likers) {
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
