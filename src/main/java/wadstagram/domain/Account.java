package wadstagram.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.crypto.password.PasswordEncoder;
import wadstagram.service.AccountStatusService;

@Entity
public class Account extends AbstractPersistable<Long> {
    
    private String username;
    
    private String password;
    
    @OneToMany(fetch=FetchType.EAGER)
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
        return statuses != null ? statuses: new ArrayList<>();
    }
}
