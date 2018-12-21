package online.pubudu.springstarter.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import online.pubudu.springstarter.exception.CustomException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static online.pubudu.springstarter.util.Constants.EXCEPTION_UNAUTHORIZED;

public class JwtLoginFilter  extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper;

    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler, ObjectMapper objectMapper) {
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
        setAuthenticationFailureHandler(authenticationFailureHandler);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (!HttpMethod.POST.name().equals(httpServletRequest.getMethod())){
            throw new CustomException(EXCEPTION_UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());
        }

        LoginDto loginDto = objectMapper.readValue(httpServletRequest.getInputStream(), LoginDto.class);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }
}
