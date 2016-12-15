package wadstagram.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class AccountStatus extends AbstractPersistable<Long> {

    @NotEmpty
    private String name;
    
    @OneToMany(mappedBy = "status")
    private List<Account> accounts;

    public AccountStatus() {
    }
    
    public AccountStatus(String status) {
        this.name = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        AccountStatus compared = (AccountStatus) obj;
        return this.name.equals(compared.getName());
    }
}
