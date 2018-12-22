package online.pubudu.springstarter.security.jwt.login;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
public class TokenDto {

    private String token;

    public TokenDto() {
    }

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
