package wadstagram.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wadstagram.service.AccountService;
import wadstagram.service.ImageService;

@Controller
public final class DefaultController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ImageService imageService;

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
        if (page == null || page.intValue() < 1) {
            return "redirect:/?page=1";
        }
        model.addAttribute("account", accountService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        int amount = page.intValue();
        double imagesAmount = (double) this.imageService.getImageAmount();
        ArrayList<Integer> pages = new ArrayList<>();
        for (int i = 1;; i++) {
            if (imagesAmount / (10 * i) <= 1) {
                pages.add(i);
                break;
            } else {
                pages.add(i);
            }
        }
        model.addAttribute("images", imageService.getImagePage(new PageRequest(amount - 1, 12, Sort.Direction.DESC, "createdOn")).getContent());
        model.addAttribute("pages", pages);
        return "index";
    }
}
