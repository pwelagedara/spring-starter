package online.pubudu.springstarter.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * <p>
 *      Custom Authentication Provider
 * </p>
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken)authentication;

        String token = (String)jwtAuthenticationToken.getCredentials();
        String subject = jwtUtils.getSubject(token);

        // TODO: 12/20/18 Validate the subject here using the database

        // Validate the token here
        jwtUtils.validateToken(token, subject);

        JwtAuthenticationToken newJwtAuthenticationToken = new JwtAuthenticationToken(subject, token, jwtUtils.getScopes(token));
        newJwtAuthenticationToken.setAuthenticated(true);
        return  newJwtAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(JwtAuthenticationToken.class);
    }
}
