package online.pubudu.springstarter.security.apikey.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import static online.pubudu.springstarter.util.Literals.EXCEPTION_AUTHENTICATION_FAILED;

/**
 * <p>
 *      Custom Exception for API Keys.
 * </p>
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
public class ApiKeyAuthenticationException extends AuthenticationException {

    // Status Code is usually 401
    private int statusCode = HttpStatus.UNAUTHORIZED.value();

    public ApiKeyAuthenticationException() {
        super(EXCEPTION_AUTHENTICATION_FAILED);
    }

    public ApiKeyAuthenticationException(String message) {
            super(message);
    }

    public ApiKeyAuthenticationException(String message, int statusCode) {
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
