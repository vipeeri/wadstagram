package wadstagram.controller;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadstagram.domain.Account;
import wadstagram.service.AccountService;
import wadstagram.service.ImageService;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ImageService imageService;

    @PostConstruct
    public void init() {
        this.accountService.createAccount("user", "user", "USER");
        this.accountService.createAccount("admin", "admin", "ADMIN");
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public String accountInfo(@PathVariable Long id, Model model) {
        Account user = accountService.getUserById(id);
        model.addAttribute("account", accountService.getUserById(id));
        model.addAttribute("images", imageService.findImagesByUser(user));
        return "profile";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerGet(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@Valid @ModelAttribute("account") Account account, BindingResult bindingResult) {
        if (accountService.getUserByName(account.getUsername()) != null || bindingResult.hasErrors()) {
            return "redirect:/register?error";
        }
        accountService.createAccount(account.getUsername(), account.getPassword(), "USER");
        return "redirect:/login";
    }
}
