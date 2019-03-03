package hw8.entities;

public class User {

    private String login;
    private String password;
    private String userName;

    private User(String login, String password, String userName) {
        this.login = login;
        this.password = password;
        this.userName = userName;
    }

    public static User PITER_CHAILOVSKII = new User("epam", "1234", "PITER CHAILOVSKII");

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

}
