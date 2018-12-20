package online.pubudu.springstarter.exception;

import io.jsonwebtoken.JwtException;
import online.pubudu.springstarter.dto.ErrorDto;
import online.pubudu.springstarter.util.FilterUtils;
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

import static online.pubudu.springstarter.util.Constants.*;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalFilterExceptionHandler extends OncePerRequestFilter {

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
            } else if(e instanceof JwtException) {

                httpStatus = HttpStatus.UNAUTHORIZED;
                message = EXCEPTION_JWT_INVALID;
            } else {
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                message = e.getMessage();
            }

            response.setStatus(httpStatus.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            ErrorDto error = new ErrorDto(httpStatus.name(), message);
            response.getWriter().write(FilterUtils.convertObjectToJson(error));

        }
    }
}
