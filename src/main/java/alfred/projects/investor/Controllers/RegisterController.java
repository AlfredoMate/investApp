package alfred.projects.investor.Controllers;

import alfred.projects.investor.Persistance.UserPersistance;
import alfred.projects.investor.Service.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private RegisterService registerService;
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestHeader String username, @RequestHeader String password) {
        registerService.registerLogic(username, password);
        return ResponseEntity.ok().build();

    }
}
