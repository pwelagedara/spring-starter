package online.pubudu.springstarter.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pubudu on 01/02/19.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 2)
public class PreflightFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Preflight Checks
        if (request.getHeader("Access-Control-Request-Method") != null && HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, X-Api-Key, X-Authorization");
            response.addHeader("Access-Control-Max-Age", "5");
            // Terminate the request from here since we do not need OPTIONS calls to hit the Security Filter Chain
            return;
        }

        filterChain.doFilter(request, response);
    }
}
