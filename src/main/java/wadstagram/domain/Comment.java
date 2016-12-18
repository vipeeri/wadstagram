package wadstagram.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Comment extends AbstractPersistable<Long> {

    @ManyToOne
    private Image image;
    
    private Account sender;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedOn;
    
    private String content;

    public String getContent() {
        return content;
    }

    public Comment() {
    }

    public Image getImage() {
        return image;
    }

    public Comment(Image image, Account sender, Date postedOn, String content) {
        this.image = image;
        this.sender = sender;
        this.postedOn = postedOn;
        this.content = content;
    }
}
