package wadstagram.domain;

import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class AccountStatus extends AbstractPersistable<Long> {

    @NotEmpty
    private String status;

    public AccountStatus() {
    }
    
    public AccountStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        AccountStatus compared = (AccountStatus) obj;
        return this.status.equals(compared.getStatus());
    }
}
