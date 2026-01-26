package alfred.projects.investor.Controllers;

import alfred.projects.investor.Model.Session;
import alfred.projects.investor.Persistance.SessionPersistance;
import alfred.projects.investor.Persistance.UserPersistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class LoginController {

    private SessionPersistance sessionPersistance;
    private UserPersistance userPersistance;
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController (SessionPersistance sessionPersistance, UserPersistance userPersistance) {

        this.sessionPersistance = sessionPersistance;
        this.userPersistance = userPersistance;
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> validateLogin (
            @RequestHeader("Username") String username,
            @RequestHeader ("Password") String password) {
        logger.info("This method was called with :{}, {}", username, password);
        boolean isLoginValid = userPersistance.testCredentials(username, password);

        if (!isLoginValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        String sessionId = UUID.randomUUID().toString();
        ResponseCookie sessionCookie = ResponseCookie.from("SESSIONID", sessionId)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(60*60)
                .sameSite("None")
                .build();

        sessionPersistance.addSession(sessionId, Session.from(username));

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, sessionCookie.toString())
                .body(true);

    }
}
