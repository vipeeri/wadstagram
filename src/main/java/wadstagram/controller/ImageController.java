package wadstagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/image")
public class ImageController {
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String getImage(@PathVariable Long id) {
        
        return "image";
    }    
}
