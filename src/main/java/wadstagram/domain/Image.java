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

    private Long fileSize;

    private Long thumbnailSize;
    
    @OneToOne(fetch = FetchType.LAZY)
    private ImageBytes imageData;

    @OneToOne(fetch = FetchType.LAZY)
    private ImageBytes thumbnailData;

    @OneToMany(mappedBy = "image", fetch = FetchType.EAGER)
    private List<Comment> comments;

    public List<Comment> getComments() {
        if (this.comments != null) {
            return this.comments;
        } else {
            return new ArrayList<>();
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setLikers(Set<Account> likers) {
        this.likers = likers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public void setImageData(ImageBytes imageData) {
        this.imageData = imageData;
    }

    public ImageBytes getImageData() {
        return imageData;
    }

    public ImageBytes getThumbnailData() {
        return thumbnailData;
    }

    public void setThumbnailData(ImageBytes thumbnailData) {
        this.thumbnailData = thumbnailData;
    }

    public Long getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(Long thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }
}
