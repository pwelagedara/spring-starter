package online.pubudu.springstarter.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS Filter.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Add Cross Origin Header
        response.addHeader("Access-Control-Allow-Origin", "*");

        filterChain.doFilter(request, response);
    }
}
