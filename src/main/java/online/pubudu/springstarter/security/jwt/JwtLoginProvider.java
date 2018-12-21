package online.pubudu.springstarter.security.jwt;

import online.pubudu.springstarter.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import static online.pubudu.springstarter.util.Constants.EXCEPTION_UNAUTHORIZED;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
@Component
public class JwtLoginProvider implements AuthenticationProvider {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken)authentication;

        String username = (String)usernamePasswordAuthenticationToken.getPrincipal();
        String password = (String)usernamePasswordAuthenticationToken.getCredentials();

        // TODO: 12/21/18 Validate Username and Password
        if(username.equals("admin") && password.equals("admin")) {
            UsernamePasswordAuthenticationToken newUsernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password, AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER"));
            return newUsernamePasswordAuthenticationToken;
        }

        // To go to Failure Handler. Throwing an Exception will take you to GlobalFilterExceptionHandler bypassing the Failure Handler
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}