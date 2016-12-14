package wadstagram.domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class ImageBytes extends AbstractPersistable<Long> {

    @OneToOne(mappedBy = "bytes")
    private Image image;

    @Lob
    private byte[] content;

    public byte[] get() {
        return content;
    }

    public Image getImage() {
        return image;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
