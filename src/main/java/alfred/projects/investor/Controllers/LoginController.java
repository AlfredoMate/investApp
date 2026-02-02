package alfred.projects.investor.Controllers;


import alfred.projects.investor.Service.LoginService;
import alfred.projects.investor.Service.SessionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    private LoginService loginService;
    private SessionService sessionService;

    public LoginController (LoginService loginService, SessionService sessionService) {

        this.loginService = loginService;
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> validateLogin (
            @RequestHeader("Username") String username,
            @RequestHeader ("Password") String password) {

        loginService.login(username, password);
        String sessionId = sessionService.createSession(username);
        ResponseCookie sessionCookie = sessionService.createCookie(sessionId);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, sessionCookie.toString())
                .body(true);
    }
}
