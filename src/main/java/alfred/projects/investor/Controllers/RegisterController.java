package alfred.projects.investor.Controllers;

import alfred.projects.investor.Persistance.UserPersistance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private UserPersistance userPersistance;
    public RegisterController(UserPersistance userPersistance) {
        this.userPersistance = userPersistance;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestHeader String username, @RequestHeader String password) {
        Boolean userAlreadyExists = userPersistance.addUser(username, password);
        if (userAlreadyExists) {
            return ResponseEntity.status(409).body("User already exists");
        } else {
            return ResponseEntity.ok().build();
        }

    }
}
