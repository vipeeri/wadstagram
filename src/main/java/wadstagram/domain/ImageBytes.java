package wadstagram.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class ImageBytes extends AbstractPersistable<Long> {

    @Lob
    private byte[] content;

    public byte[] get() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
