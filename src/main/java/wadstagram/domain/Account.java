package wadstagram.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @ManyToOne
    private AccountStatus status;

    public Account(String username, String password, AccountStatus status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public Account() {
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public AccountStatus getStatus() {
        return this.status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatuses(AccountStatus status) {
        this.status = status;
    }
}
