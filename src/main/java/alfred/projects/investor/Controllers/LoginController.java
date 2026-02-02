package alfred.projects.investor.Controllers;


import alfred.projects.investor.Service.LoginService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LoginController {

    private LoginService loginService;

    public LoginController (LoginService loginService) {

        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> validateLogin (
            @RequestHeader("Username") String username,
            @RequestHeader ("Password") String password) {

        String sessionId = UUID.randomUUID().toString();
       loginService.login(username, password, sessionId);

        ResponseCookie sessionCookie = ResponseCookie.from("SESSIONID", sessionId)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60*60)
                .sameSite("None")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, sessionCookie.toString())
                .body(true);
    }
}
