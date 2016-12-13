package wadstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
