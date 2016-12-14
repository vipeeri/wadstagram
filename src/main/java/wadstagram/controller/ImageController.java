package wadstagram.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import wadstagram.domain.Image;
import wadstagram.domain.ImageBytes;
import wadstagram.service.ImageService;

@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getImage(@PathVariable Long id, Model model) {
        model.addAttribute("image", imageService.getImage(id));
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
        //return "redirect:/image/" + image.getId();
        return "redirect:/";
    }
}