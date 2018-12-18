package online.pubudu.springstarter.exception;

import online.pubudu.springstarter.dto.ErrorDto;
import online.pubudu.springstarter.util.FilterUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

/**
 * Created by pubudu welagedara on 12/17/18.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalFilterExceptionHandler extends OncePerRequestFilter {

    private static final Log LOG = LogFactory.getLog(GlobalFilterExceptionHandler.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        LOG.info("In GlobalFilterExceptionHandler...");
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {

            LOG.info("Caught Exception in GlobalFilterExceptionHandler...");

            if (e instanceof CustomException) {
                CustomException customException = (CustomException) e;
                response.setStatus(customException.getStatusCode());
                HttpStatus status = HttpStatus.valueOf(customException.getStatusCode());
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                ErrorDto error = new ErrorDto(status.name(), customException.getMessage());
                response.getWriter().write(FilterUtils.convertObjectToJson(error));
            } else {

                // Respond with status 500
                HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

                response.setStatus(httpStatus.value());
                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                ErrorDto error = new ErrorDto(httpStatus.name(), e.getMessage());
                response.getWriter().write(FilterUtils.convertObjectToJson(error));
            }

        }
    }
}
