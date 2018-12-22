package online.pubudu.springstarter.security.apikey;

import online.pubudu.springstarter.security.apikey.exception.ApiKeyAuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/*
* Created by pubudu welagedara on 12/17/18.
* */
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
            // Returning null here will throw a ProviderNotFoundException
            throw new ApiKeyAuthenticationException();
        }
        return  apiKeyAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ApiKeyAuthenticationToken.class);
    }
}
