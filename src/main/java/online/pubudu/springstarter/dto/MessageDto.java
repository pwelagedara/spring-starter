package online.pubudu.springstarter.dto;

import io.swagger.annotations.ApiModelProperty;

import static online.pubudu.springstarter.util.Literals.MESSAGE_DTO_MESSAGE_EXAMPLE;

/**
 * Message Domain Transfer Object.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
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
