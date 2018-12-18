package online.pubudu.springstarter.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
public class CustomException extends RuntimeException {

    // Status Code is usually 400
    private int statusCode = HttpStatus.BAD_REQUEST.value();

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, int statusCode) {
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
