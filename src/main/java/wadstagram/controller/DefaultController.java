package wadstagram.controller;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadstagram.domain.Account;

@Controller
public class DefaultController {

    @RequestMapping("*")
    public String defaultGet() {
        return "redirect:/";
    }

    @RequestMapping("/")
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }
}
