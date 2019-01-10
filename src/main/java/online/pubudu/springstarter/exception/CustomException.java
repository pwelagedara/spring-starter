package online.pubudu.springstarter.exception;

import org.springframework.http.HttpStatus;

/**
 * This is a Custom Exception. The status code is 400 in most cases.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
public class CustomException extends Exception {

    /**
     * Status Code is usually 400
     * @author pubudu welagedara
     */
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
