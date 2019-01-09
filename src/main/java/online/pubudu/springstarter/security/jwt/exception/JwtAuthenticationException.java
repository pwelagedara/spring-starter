package online.pubudu.springstarter.security.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import static online.pubudu.springstarter.util.Literals.EXCEPTION_AUTHENTICATION_FAILED;

/**
 * Custom Exception for JWTs.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
public class JwtAuthenticationException extends AuthenticationException {

    // Status Code is usually 401
    private int statusCode = HttpStatus.UNAUTHORIZED.value();

    public JwtAuthenticationException() {
        super(EXCEPTION_AUTHENTICATION_FAILED);
    }

    public JwtAuthenticationException(String message) {
            super(message);
    }

    public JwtAuthenticationException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

}
