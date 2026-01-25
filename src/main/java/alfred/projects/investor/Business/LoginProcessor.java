package alfred.projects.investor.Business;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginProcessor {

    String username;
    String password;



    public boolean isLoginValid () {

        String username = this.getUsername();
        String password = this.getPassword();
        return password.equals("1234");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
