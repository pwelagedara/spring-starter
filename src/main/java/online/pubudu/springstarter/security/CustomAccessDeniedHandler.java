package online.pubudu.springstarter.security;

import online.pubudu.springstarter.dto.ErrorDto;
import online.pubudu.springstarter.util.FilterUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // Respond with status 403
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        ErrorDto error = new ErrorDto(httpStatus.name(), accessDeniedException.getMessage());
        response.getWriter().write(FilterUtils.convertObjectToJson(error));
    }
}
