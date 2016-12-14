package wadstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import wadstagram.service.AccountService;

@Controller
public class DefaultController {

    @Autowired
    AccountService accountService;

    @RequestMapping("*")
    public String defaultGet() {
        return "redirect:/";
    }

    @RequestMapping("/")
    public String root(Model model) {
        model.addAttribute("account", accountService.findUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "index";
    }
}
