package online.pubudu.springstarter.security.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import static online.pubudu.springstarter.util.Literals.EXCEPTION_AUTHENTICATION_FAILED;

/*
* Created by pubudu welagedara on 12/17/18.
* */
public class JwtAuthenticationException extends AuthenticationException {

    // Status Code is usually 400
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
