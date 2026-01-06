package alfred.projects.investor.Controllers;

import alfred.projects.investor.Model.User;
import alfred.projects.investor.Persistance.UserPersistance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserPersistance userPersistance;

    public UserController (UserPersistance userPersistance) {
        this.userPersistance = userPersistance;
    }

    @PostMapping("/user")
    public String addUser(@RequestBody User user) {
        userPersistance.addUser(user);
        return "Username added!";
    }

    @GetMapping("/user")
    public String getUser (@RequestBody User user){
        if (userPersistance.userExists(user)) {
            return "User exists!";
        } else {
            return "User does not exist!";
        }

    }
}
