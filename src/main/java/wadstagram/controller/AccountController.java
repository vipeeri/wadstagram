package wadstagram.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import wadstagram.domain.Account;
import wadstagram.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public String accountInfo(@PathVariable Long id, Model model) {
        model.addAttribute("account", accountService.getUserById(id));
        return "account";
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
           System.out.println(bindingResult.toString());
            return "register";
        }
        accountService.createAccount(account.getUsername(), account.getPassword(), "USER");
        return "redirect:/login";
    }
}
