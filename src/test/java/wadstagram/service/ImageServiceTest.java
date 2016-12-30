package wadstagram.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.WebApplicationContext;
import wadstagram.domain.Comment;
import wadstagram.domain.ImageBytes;
import wadstagram.repository.CommentRepository;

@WithUserDetails("admin")
@ActiveProfiles("testing")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private CommentRepository commentRepository;

    @After
    public void cleanUp() {
        this.commentRepository.deleteAll();
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
        image = this.imageRepository.save(image);
        assertNotNull(this.imageService.getImage(image.getId()));
    }

    @Test
    public void testGetAllImages() {
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
        String description = UUID.randomUUID().toString().substring(0, 6);
        String imageName = UUID.randomUUID().toString().substring(0, 6);
        String content = UUID.randomUUID().toString().substring(0, 6);
        MockMultipartFile multipartFile = new MockMultipartFile("image", imageName, "image/png", content.getBytes());
        Image image = this.imageService.createImage(multipartFile, new Image(), new ImageBytes(), description);
        assertNotNull(image);
    }

    @Test
    public void testGetImageData() throws Exception {
        String description = UUID.randomUUID().toString().substring(0, 6);
        String imageName = UUID.randomUUID().toString().substring(0, 6);
        String content = UUID.randomUUID().toString().substring(0, 6);
        MockMultipartFile multipartFile = new MockMultipartFile("image", imageName, "image/png", content.getBytes());
        Image image = this.imageService.createImage(multipartFile, new Image(), new ImageBytes(), description);
        assertNotNull(this.imageService.getImageData(image.getId()));
    }

    @Test
    public void testDeleteImage() throws Exception {
        String description = UUID.randomUUID().toString().substring(0, 6);
        String imageName = UUID.randomUUID().toString().substring(0, 6);
        String content = UUID.randomUUID().toString().substring(0, 6);
        MockMultipartFile multipartFile = new MockMultipartFile("image", imageName, "image/png", content.getBytes());
        Image image = this.imageService.createImage(multipartFile, new Image(), new ImageBytes(), description);
        this.imageService.deleteImage(image.getId());
        assertNull(this.imageService.getImage(image.getId()));
    }

    @Test
    public void testFindImagesByUser() {
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
        image.setOwner(this.accountRepository.findOne(1L));
        image.setType("image/png");
        this.imageRepository.save(image);
        assertEquals(2, this.imageService.findImagesByUser(this.accountRepository.findOne(1L)).size());
    }

    @Test
    public void testSaveImage() {
        Image image = new Image();
        image.setComments(new ArrayList<>());
        image.setCreatedOn(new Date());
        image.setDescription("test");
        image.setLikers(new ArrayList<>());
        image.setName("test");
        image.setOwner(this.accountRepository.findOne(1L));
        image.setType("image/png");
        image = this.imageRepository.save(image);
        image.setName("changed");
        image = this.imageService.saveImage(image);
        assertEquals("changed", image.getName());
    }

    @Test
    public void testImageAmount() {
        assertEquals(0, this.imageService.getImageAmount());
        Image image = new Image();
        image.setComments(new ArrayList<>());
        image.setCreatedOn(new Date());
        image.setDescription("test");
        image.setLikers(new ArrayList<>());
        image.setName("test");
        image.setOwner(this.accountRepository.findOne(1L));
        image.setType("image/png");
        image = this.imageRepository.save(image);
        assertEquals(1, this.imageService.getImageAmount());
    }

    @Test
    public void addCommentTest() {
        Image image = new Image();
        image.setComments(new ArrayList<>());
        image.setCreatedOn(new Date());
        image.setDescription("test");
        image.setLikers(new ArrayList<>());
        image.setName("test");
        image.setOwner(this.accountRepository.findOne(1L));
        image.setType("image/png");
        image = this.imageRepository.save(image);
        Comment comment = new Comment(image, this.accountRepository.findOne(1L), new Date(), "testcomment");
        this.imageService.addCommentToPicture(image, comment);
        image = this.imageRepository.findOne(image.getId());
        assertEquals(1, image.getComments().size());
    }

    @Test
    public void getCommentsTest() {
        Image image = new Image();
        image.setComments(new ArrayList<>());
        image.setCreatedOn(new Date());
        image.setDescription("test");
        image.setLikers(new ArrayList<>());
        image.setName("test");
        image.setOwner(this.accountRepository.findOne(1L));
        image.setType("image/png");
        image = this.imageRepository.save(image);
        Comment comment = new Comment(image, this.accountRepository.findOne(1L), new Date(), "testcomment");
        this.imageService.addCommentToPicture(image, comment);
        image = this.imageRepository.findOne(image.getId());
        assertNotNull(this.imageService.getComments(image.getId()));
        assertEquals(1, this.imageService.getComments(image.getId()).size());
    }

    @Test
    public void testGetImagePage() {
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
        image.setOwner(this.accountRepository.findOne(1L));
        image.setType("image/png");
        this.imageRepository.save(image);
        assertNotNull(this.imageService.getImagePage(new PageRequest(0, 10, Sort.Direction.DESC, "createdOn")).size());
        assertEquals(2, this.imageService.getImagePage(new PageRequest(0, 10, Sort.Direction.DESC, "createdOn")).size());
    }

    @Test
    public void testHeartAmount() {
        Image image = new Image();
        image.setComments(new ArrayList<>());
        image.setCreatedOn(new Date());
        image.setDescription("test");
        image.setLikers(new ArrayList<>());
        image.setName("test");
        image.setOwner(this.accountRepository.findOne(1L));
        image.setType("image/png");
        image = this.imageRepository.save(image);
        assertNotNull(image.getLikers());
        assertEquals(0, image.getLikers().size());
    }
}
