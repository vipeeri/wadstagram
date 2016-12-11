package wadstagram.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Account extends AbstractPersistable<Long> {
    
    private String username;
    
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
}
