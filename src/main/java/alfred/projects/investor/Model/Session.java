package alfred.projects.investor.Model;

public class Session {

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private Session(String userName) {
        this.userName = userName;
    }

    public static Session from (String userName) {
        return new Session(userName);
    }

}
