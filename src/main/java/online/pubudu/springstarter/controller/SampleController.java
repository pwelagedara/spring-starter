package online.pubudu.springstarter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import online.pubudu.springstarter.dto.ErrorDto;
import online.pubudu.springstarter.dto.MessageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static online.pubudu.springstarter.util.Literals.*;

/**
 *<p>
 *     This is a Sample Controller.
 *</p>
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
@RestController
@Api(tags = SAMPLE_CONTROLLER_TAG, description = SAMPLE_CONTROLLER_DESCRIPTION)
public class SampleController {

    /**
     * <p>
     *     This is a public endpoint which can be accessed only by anyone.
     * </p>
     * @return a message
     * @author pubudu welagedara
     * @see <a href="http://pubudu.online">pubudu.online</a>
     */
    @ApiOperation(value = "${sample-controller.tell-something.value}", notes = "${sample-controller.tell-something.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = RESPONSE_OK_VALUE, message = RESPONSE_OK_MESSAGE, response = MessageDto.class)
        }
    )
    @GetMapping("/public/message")
    public MessageDto tellSomething() {
        return new MessageDto("Subscribe to PewDiePie...!!!");
    }

    /**
     * <p>
     *     This is a protected endpoint which can only be accessed by an authenticated/ authorized user.
     * </p>
     * @return a message
     * @author pubudu welagedara
     * @see <a href="http://pubudu.online">pubudu.online</a>
     */
    @ApiOperation(value = "${sample-controller.tell-a-secret.value}", notes = "${sample-controller.tell-a-secret.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = RESPONSE_OK_VALUE, message = RESPONSE_OK_MESSAGE, response = MessageDto.class),
            @ApiResponse(code = RESPONSE_ERROR_VALUE, message = RESPONSE_ERROR_MESSAGE, response = ErrorDto.class)
    }
    )
    @GetMapping("/protected/message")
    public MessageDto tellASecret() {
        return new MessageDto("Unsubscribe from T- Series...!!!");

    }
}
