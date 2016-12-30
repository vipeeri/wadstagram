package wadstagram.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wadstagram.domain.Account;
import wadstagram.domain.Comment;
import wadstagram.domain.Image;
import wadstagram.domain.ImageBytes;
import wadstagram.repository.CommentRepository;
import wadstagram.repository.ImageBytesRepository;
import wadstagram.repository.ImageRepository;

@Service
public final class ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageBytesRepository imageBytesRepository;

    @Autowired
    AccountService accountService;
    
    @Autowired
    CommentRepository commentRepository;
    
    public Image getImage(Long id) {
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
    
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }
    
    public int getImageAmount() {
        return this.getAllImages().size();
    }
    
    public Page<Image> getImagePage(Pageable pageable) {
        return this.imageRepository.findAll(pageable);
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
}
