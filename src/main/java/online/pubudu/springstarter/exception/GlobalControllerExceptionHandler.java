package online.pubudu.springstarter.exception;

import online.pubudu.springstarter.dto.ErrorDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorDto> handleSmartBankException(CustomException e, HttpServletRequest request) {

        // Handle the exception here. The status code is usually 400
        return constructResponse(e.getStatusCode(), e.getMessage());

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> handleRuntimeException(RuntimeException e, HttpServletRequest request) {

        // Handle the exception here. The status code is always 500
        int errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        return constructResponse(errorCode, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e, HttpServletRequest request) {

        // Handle the exception here. The status code is always 500
        int errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
        return constructResponse(errorCode, e.getMessage());
    }

    private ResponseEntity<ErrorDto> constructResponse(int errorCode, String message) {
        HttpStatus status = HttpStatus.valueOf(errorCode);
        ErrorDto error = new ErrorDto(status.name(), message);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<ErrorDto>(error, headers, status);
    }
}
