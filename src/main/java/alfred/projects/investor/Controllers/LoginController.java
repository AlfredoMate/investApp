package alfred.projects.investor.Controllers;

import alfred.projects.investor.Business.LoginProcessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private LoginProcessor loginProcessor;

    public LoginController (LoginProcessor loginProcessor) {
        this.loginProcessor = loginProcessor;
    }

    @PostMapping("/login")
    public boolean validateLogin (
            @RequestHeader("Username") String username,
            @RequestHeader ("Password") String password) {
        loginProcessor.setUsername(username);
        loginProcessor.setPassword(password);
        return loginProcessor.isLoginValid();

    }
}
