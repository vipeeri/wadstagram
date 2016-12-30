package wadstagram.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("production")
@RestController
public final class ControlErrorrer implements ErrorController {
    //Catches any unhandled errors
    @RequestMapping(value = "/error")
    public String error(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oops");
        return "";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
