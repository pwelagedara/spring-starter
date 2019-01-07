package online.pubudu.springstarter.security.jwt.login;

import io.swagger.annotations.ApiModelProperty;

import static online.pubudu.springstarter.util.Literals.TOKEN_DTO_TOKEN_EXAMPLE;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
public class TokenDto {

    @ApiModelProperty(value = "${token-dto.token.value}", required = true, example = TOKEN_DTO_TOKEN_EXAMPLE)
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
