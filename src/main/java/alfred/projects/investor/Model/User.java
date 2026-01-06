package alfred.projects.investor.Model;

public class User {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static User of(String name) {
        User user = new User();
        user.setUsername(name);
        return user;
    }
}
