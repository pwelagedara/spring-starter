package online.pubudu.springstarter.dto;

import io.swagger.annotations.ApiModelProperty;

import static online.pubudu.springstarter.util.Literals.ERROR_DTO_ERROR_EXAMPLE;
import static online.pubudu.springstarter.util.Literals.ERROR_DTO_MESSAGE_EXAMPLE;

/*
* Created by pubudu welagedara on 12/17/18.
* */
public class ErrorDto {

    @ApiModelProperty(value = "${error-dto.error.value}", required = true, example = ERROR_DTO_ERROR_EXAMPLE)
    private String error;

    @ApiModelProperty(value = "${error-dto.message.value}", required = true, example = ERROR_DTO_MESSAGE_EXAMPLE)
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
