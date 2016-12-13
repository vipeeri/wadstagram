package wadstagram.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Image extends AbstractPersistable<Long> {
    
    private String name;
    
    private String type;
    
    private Long length;
    
    @Lob
    private byte[] data;
}
