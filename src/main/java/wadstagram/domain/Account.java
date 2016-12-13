package wadstagram.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<AccountStatus> statuses;

    public Account(String username, String password, List<AccountStatus> statuses) {
        this.username = username;
        this.password = password;
        this.statuses = statuses;
    }

    public Account() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<AccountStatus> getStatuses() {
        return statuses != null ? statuses : new ArrayList<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatuses(List<AccountStatus> statuses) {
        this.statuses = statuses;
    }
}
