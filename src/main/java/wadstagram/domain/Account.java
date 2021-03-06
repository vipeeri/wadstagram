package wadstagram.domain;

import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String status;

    @OneToMany(mappedBy = "owner")
    private Set<Image> images;

    @OneToMany(mappedBy = "sender")
    private List<Comment> comments;

    @ManyToMany(mappedBy = "likers")
    private List<Image> likes;

    public Set<Image> getImages() {
        return images;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public Account(String username, String password, String status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = "USER";
    }

    public Account() {
        this.status = "USER";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Account.class) {
            return false;
        }
        Account comparedTo = (Account) obj;

        return this.getUsername().equals(comparedTo.getUsername());
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getStatus() {
        return this.status;
    }

    public List<Image> getLikes() {
        return likes;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
