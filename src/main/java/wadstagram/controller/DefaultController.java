package wadstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wadstagram.service.AccountService;
import wadstagram.service.ImageService;

@Controller
public class DefaultController {

    @Autowired
    AccountService accountService;
    
    @Autowired
    ImageService imageService;

    @RequestMapping("*")
    public String defaultResponse() {
        return "redirect:/";
    }

    @RequestMapping("/")
    public String root(Model model) {
        model.addAttribute("account", accountService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        model.addAttribute("images", imageService.getAllImages());
        return "index";
    }
}
