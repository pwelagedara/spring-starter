package online.pubudu.springstarter.security.jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Custom Authentication Token.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private String subject;
    private String token;

    public JwtAuthenticationToken(String token) {
        super(AuthorityUtils.NO_AUTHORITIES);
        this.token = token;
    }

    public JwtAuthenticationToken(String subject, String token, List<String> authorities) {
        // TODO: 12/21/18 Write the below line in a better way
        super(authorities.stream().map(a -> AuthorityUtils.commaSeparatedStringToAuthorityList(a).get(0)).collect(Collectors.toList()));
        this.subject = subject;
        this.token = token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.subject;
    }
}
