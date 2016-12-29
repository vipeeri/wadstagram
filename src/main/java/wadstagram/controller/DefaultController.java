package wadstagram.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping("/oops")
    public String handleError(Model model) {
        model.addAttribute("account", accountService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "oops";
    }

    @RequestMapping("/")
    public String root(Model model, @RequestParam(required = false) Long page) {
        model.addAttribute("account", accountService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        if(page == null || page.intValue() < 1) {
            return "redirect:/?page=1";
        }
        int amount = page.intValue();
        double imagesAmount = (double) this.imageService.getImageAmount();
        ArrayList<Integer> pages = new ArrayList<>();
        int i = 1;
        while(imagesAmount / (i*10) > 1) {
            pages.add(i);
            i++;
        }
        pages.add(i);
        model.addAttribute("images", imageService.getImagePage(new PageRequest((amount - 1) * 10, amount * 10, Sort.Direction.DESC, "id")));
        model.addAttribute("pages", pages);
        return "index";
    }
}
