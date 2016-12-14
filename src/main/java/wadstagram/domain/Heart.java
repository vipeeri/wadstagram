package wadstagram.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Heart extends AbstractPersistable<Long> {
    
    @ManyToOne
    private Image image;
    
    @OneToOne
    private Account giver;
}
