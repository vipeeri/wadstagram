package wadstagram.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import static org.testng.Assert.assertNull;

public class CommentTest {

    private Comment comment;

    @Before
    public void setUp() {
        comment = new Comment(new Image(), new Account("test", "test"), new Date(), "test");
    }

    @Test
    public void testGetContent() {
        assertEquals(comment.getContent(), "test");
    }

    @Test
    public void testGetPostedOn() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        assertEquals(dateFormat.format(comment.getPostedOn()), dateFormat.format(date));
    }

    @Test
    public void testGetSender() {
        assertEquals(comment.getSender().getUsername(), "test");
    }

    @Test
    public void testGetImage() {
        assertNotNull(comment.getImage());
    }

}
