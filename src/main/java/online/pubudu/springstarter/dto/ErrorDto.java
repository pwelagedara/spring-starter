package online.pubudu.springstarter.dto;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
public class ErrorDto {

    private String error;

    private String message;

    public ErrorDto(String error, String message) {
        super();
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
