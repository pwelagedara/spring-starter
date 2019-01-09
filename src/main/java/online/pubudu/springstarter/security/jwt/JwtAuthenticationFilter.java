package online.pubudu.springstarter.security.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Authentication Filter.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
public class JwtAuthenticationFilter extends GenericFilterBean {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;

        Optional<String> requestHeader = Optional.ofNullable(httpServletRequest.getHeader("X-Authorization"));

        if(requestHeader.isPresent() && requestHeader.get().startsWith("Bearer ")) {

            JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(requestHeader.get().substring(7));
            SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(jwtAuthenticationToken));
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
