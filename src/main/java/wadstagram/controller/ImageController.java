package wadstagram.controller;

import java.io.IOException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wadstagram.domain.Account;
import wadstagram.domain.Comment;
import wadstagram.domain.Image;
import wadstagram.domain.ImageBytes;
import wadstagram.service.AccountService;
import wadstagram.service.ImageService;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{id}/comment", method = RequestMethod.DELETE)
    public String deleteImage(@PathVariable Long id) {
        
        return "redirect:/image/" + id;
    }

    @RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
    public String postComment(@PathVariable Long id, @RequestParam String comment) {
        Account sender = accountService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Image image = imageService.getImage(id);
        Comment newComment = new Comment(image, sender, new Date(), comment);
        imageService.addCommentToPicture(image, newComment);
        return "redirect:/image/" + id;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getImage(@PathVariable Long id, Model model) {
        model.addAttribute("image", imageService.getImage(id));
        model.addAttribute("hearts", imageService.getHeartAmount(id));
        model.addAttribute("comments", imageService.getComments(id));
        return "image";
    }

    @ResponseBody
    @RequestMapping(value = "{id}/data", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getImageData(@PathVariable Long id) {
        Image image = imageService.getImage(id);
        if (image == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(image.getLength());
        headers.setContentType(MediaType.parseMediaType(image.getType()));
        return new ResponseEntity<byte[]>(image.getBytes().get(), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/like", method = RequestMethod.GET)
    public String likeImage(@PathVariable Long id) {
        Image image = imageService.getImage(id);
        Account liker = accountService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (!image.getLikers().contains(liker)) {
            image.getLikers().add(liker);
        } else {
            image.getLikers().remove(liker);
        }
        imageService.saveImage(image);
        return "redirect:/image/" + id;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addImage(@RequestParam("image") MultipartFile received, @RequestParam String description) throws IOException {
        if (!received.getContentType().contains("image")) {
            return "redirect:/";
        }
        Image image = imageService.createImage(received, new Image(), new ImageBytes(), description);
        return "redirect:/image/" + image.getId();
    }
}
