package online.pubudu.springstarter.security.apikey;

import online.pubudu.springstarter.exception.CustomException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static online.pubudu.springstarter.util.Constants.EXCEPTION_UNAUTHORIZED;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
@Component
public class ApiKeyAuthenticationProvider implements AuthenticationProvider {

    @Value("${security.api-key}")
    private String apiKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        ApiKeyAuthenticationToken apiKeyAuthenticationToken = (ApiKeyAuthenticationToken)authentication;
        if(apiKeyAuthenticationToken.getCredentials().equals(apiKey)) {
            apiKeyAuthenticationToken.setAuthenticated(true);
        } else {
            throw new CustomException( EXCEPTION_UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());
        }
        return  apiKeyAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ApiKeyAuthenticationToken.class);
    }
}
