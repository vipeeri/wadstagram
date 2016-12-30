package wadstagram.service;

import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wadstagram.domain.Image;
import wadstagram.repository.AccountRepository;
import wadstagram.repository.ImageRepository;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ImageRepository imageRepository;

    @After
    public void cleanUp() {
        this.imageRepository.deleteAll();
    }
    
    @Test
    public void testGetImage() {
        Image image = new Image();
        image.setComments(new ArrayList<>());
        image.setCreatedOn(new Date());
        image.setDescription("test");
        image.setLikers(new ArrayList<>());
        image.setName("test");
        image.setOwner(this.accountRepository.findOne(1L));
        image.setType("image/png");
        this.imageRepository.save(image);
        assertNotNull(this.imageService.getImage(1L));
    }

    @Test
    public void testGetAllImages() {
        assertTrue(this.imageService.getAllImages().isEmpty());
        Image image = new Image();
        image.setComments(new ArrayList<>());
        image.setCreatedOn(new Date());
        image.setDescription("test");
        image.setLikers(new ArrayList<>());
        image.setName("test");
        image.setOwner(this.accountRepository.findOne(1L));
        image.setType("image/png");
        this.imageRepository.save(image);
        image = new Image();
        image.setComments(new ArrayList<>());
        image.setCreatedOn(new Date());
        image.setDescription("testDescription");
        image.setLikers(new ArrayList<>());
        image.setName("testname");
        image.setOwner(this.accountRepository.findOne(2L));
        image.setType("image/png");
        this.imageRepository.save(image);
        assertEquals(2, this.imageService.getAllImages().size());

    }

    @Test
    public void testCreateImage() throws Exception {

    }

    @Test
    public void testGetImageData() {

    }
}

    /*public Image getImage(Long id) {
        return imageRepository.findOne(id);
    }
    
    public List<Image> findImagesByUser(Account owner) {
        return imageRepository.findByOwner(owner);
    }
    
    public void deleteImage(Long id) {
        Image deleted = this.imageRepository.findOne(id);
        List<Comment> comments = commentRepository.findByImage(deleted);
        for(Comment comment : comments) {
            this.commentRepository.delete(comment);
        }
        this.imageRepository.delete(deleted);
    }
    
    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
    
    public int getImageAmount() {
        return this.getAllImages().size();
    }
    
    public List<Image> getImagePage(Pageable pageable) {
        return this.imageRepository.findAll(pageable).getContent();
    }

    public Image createImage(MultipartFile received, Image image, ImageBytes content, String description) throws IOException {
        Account owner = accountService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        image.setName(received.getOriginalFilename());
        image.setLength(received.getSize());
        image.setType(received.getContentType());
        content.setContent(received.getBytes());
        image.setBytes(imageBytesRepository.save(content));
        image.setCreatedOn(new Date());
        image.setOwner(owner);
        image.setDescription(description);
        return imageRepository.save(image);
    }

    public byte[] getImageData(Long id) {
        return this.getImage(id).getBytes().get();
    }
    
    public int getHeartAmount(Long id) {
        return this.getImage(id).getLikers().size();
    }
    
    public List<Comment> getComments(Long id) {
        return this.getImage(id).getComments();
    }
    
    public void addCommentToPicture(Image image, Comment comment) {
        comment = commentRepository.save(comment);
        image.getComments().add(comment);
        imageRepository.save(image);
    }
*/
