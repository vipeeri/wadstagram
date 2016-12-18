package wadstagram.controller;

import java.io.IOException;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @Transactional
    @RequestMapping(value = "/{id}/comment", method = RequestMethod.POST)
    public String postComment(@PathVariable Long id, @RequestParam String comment) {
        Account sender = accountService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName());
        Image image = imageService.getImage(id);
        image.getComments().add(new Comment(image, sender, new Date(), comment));
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
    @RequestMapping(value = "{id}/data", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageData(@PathVariable Long id) {
        return imageService.getImageData(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addImage(@RequestParam("image") MultipartFile received) throws IOException {
        if (!received.getContentType().equals("image/jpeg")) {
            return "redirect:/";
        }

        Image image = imageService.createImage(received, new Image(), new ImageBytes());
        return "redirect:/image/" + image.getId();
    }
}
