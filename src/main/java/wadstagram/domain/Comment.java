package wadstagram.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Comment extends AbstractPersistable<Long> {
    
    private Account sender;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedOn;
}
