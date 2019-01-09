package online.pubudu.springstarter.security.jwt.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import online.pubudu.springstarter.dto.ErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static online.pubudu.springstarter.util.Literals.EXCEPTION_AUTHENTICATION_FAILED;

/**
 * Failure handler for <b>/auth/login</b>.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
@Component
public class JwtLoginFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        String message = EXCEPTION_AUTHENTICATION_FAILED;
        httpServletResponse.setStatus(httpStatus.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ErrorDto error = new ErrorDto(httpStatus.name(), message);
        objectMapper.writeValue(httpServletResponse.getWriter(), error);
    }
}
