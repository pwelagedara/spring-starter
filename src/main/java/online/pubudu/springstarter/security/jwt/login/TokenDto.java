package online.pubudu.springstarter.security.jwt.login;

import io.swagger.annotations.ApiModelProperty;

import static online.pubudu.springstarter.util.Literals.TOKEN_DTO_TOKEN_EXAMPLE;

/**
 * <p>
 *      Token Domain Transfer Object for <b>/auth/login</b>. This is the Response.
 * </p>
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
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
