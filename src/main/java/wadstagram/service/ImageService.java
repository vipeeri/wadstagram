package wadstagram.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wadstagram.domain.Image;
import wadstagram.domain.ImageBytes;
import wadstagram.repository.ImageBytesRepository;
import wadstagram.repository.ImageRepository;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    ImageBytesRepository imageBytesRepository;

    public Image getImage(Long id) {
        return imageRepository.findOne(id);
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Image createImage(MultipartFile received, Image image, ImageBytes content) throws IOException {
        image = imageRepository.save(image);
        content = imageBytesRepository.save(content);
        image.setName(received.getOriginalFilename());
        image.setLength(received.getSize());
        image.setType(received.getContentType());
        image.setBytes(content);
        content.setContent(received.getBytes());
        content.setImage(image);
        imageBytesRepository.save(content);
        return imageRepository.save(image);
    }

    public byte[] getImageData(Long id) {
        return this.getImage(id).getBytes().get();
    }
}
