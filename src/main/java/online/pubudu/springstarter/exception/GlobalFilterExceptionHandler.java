package online.pubudu.springstarter.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import online.pubudu.springstarter.dto.ErrorDto;
import online.pubudu.springstarter.security.apikey.exception.ApiKeyAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static online.pubudu.springstarter.util.Literals.EXCEPTION_AUTHENTICATION_FAILED;

/**
 * An Exception Handler to handle Exceptions in the Filters.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalFilterExceptionHandler extends OncePerRequestFilter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {

            HttpStatus httpStatus;
            String message;

            if (e instanceof CustomException) {

                CustomException customException = (CustomException) e;
                httpStatus = HttpStatus.valueOf(customException.getStatusCode());
                message = e.getMessage();
            } else if(e instanceof ApiKeyAuthenticationException) {

                ApiKeyAuthenticationException apiKeyAuthenticationException = (ApiKeyAuthenticationException) e;
                httpStatus = HttpStatus.valueOf(apiKeyAuthenticationException.getStatusCode());
                message = e.getMessage();
            } else if(e instanceof JwtException) {

                httpStatus = HttpStatus.UNAUTHORIZED;
                message = EXCEPTION_AUTHENTICATION_FAILED;
            } else {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                message = e.getMessage();
            }

            response.setStatus(httpStatus.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            ErrorDto error = new ErrorDto(httpStatus.name(), message);
            objectMapper.writeValue(response.getWriter(), error);

        }
    }
}
