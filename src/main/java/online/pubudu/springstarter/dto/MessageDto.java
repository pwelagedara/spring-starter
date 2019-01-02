package online.pubudu.springstarter.dto;

import io.swagger.annotations.ApiModelProperty;

import static online.pubudu.springstarter.util.Literals.MESSAGE_DTO_MESSAGE_EXAMPLE;

/*
* Created by pubudu welagedara on 12/17/18.
* */
public class MessageDto {

    @ApiModelProperty(value = "${message-dto.message.value}", required = true, example = MESSAGE_DTO_MESSAGE_EXAMPLE)
    private String message;

    public MessageDto(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
