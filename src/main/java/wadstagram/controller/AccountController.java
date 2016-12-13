package wadstagram.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wadstagram.domain.Account;
import wadstagram.repository.AccountRepository;
import wadstagram.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    AccountRepository accountRepository;
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String accountInfo() {

        return "account";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute Account account, BindingResult bindingResult) {
        if(accountRepository.findByUsername(account.getUsername()) != null || bindingResult.hasErrors()) {
            return "/login";
        }
        accountService.createAccount(account.getUsername(), account.getPassword(), "USER");
        return "redirect:/login";
    }
}
