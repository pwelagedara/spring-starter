package online.pubudu.springstarter.security.jwt.login;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
public class LoginDto {

    private String username;
    private String password;

    public LoginDto() {
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