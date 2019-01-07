package online.pubudu.springstarter.security.jwt.login;

import io.swagger.annotations.ApiModelProperty;

import static online.pubudu.springstarter.util.Literals.LOGIN_DTO_PASSWORD_EXAMPLE;
import static online.pubudu.springstarter.util.Literals.LOGIN_DTO_USERNAME_EXAMPLE;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
public class LoginDto {

    @ApiModelProperty(value = "${login-dto.username.value}", required = true, example = LOGIN_DTO_USERNAME_EXAMPLE)
    private String username;

    @ApiModelProperty(value = "${login-dto.password.value}", required = true, example = LOGIN_DTO_PASSWORD_EXAMPLE)
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
