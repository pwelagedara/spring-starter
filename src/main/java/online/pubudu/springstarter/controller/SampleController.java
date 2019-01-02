package online.pubudu.springstarter.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import online.pubudu.springstarter.dto.ErrorDto;
import online.pubudu.springstarter.dto.MessageDto;
import online.pubudu.springstarter.security.jwt.login.LoginDto;
import online.pubudu.springstarter.security.jwt.login.TokenDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static online.pubudu.springstarter.util.Literals.*;

/*
* Created by pubudu welagedara on 12/17/18.
* */
@RestController
@Api(tags = SAMPLE_CONTROLLER_TAG, description = SAMPLE_CONTROLLER_DESCRIPTION)
public class SampleController {

    @ApiOperation(value = "${sample-controller.tell-something.value}", notes = "${sample-controller.tell-something.notes}")
    @ApiResponses(value = {
            @ApiResponse(code = RESPONSE_OK_VALUE, message = RESPONSE_OK_MESSAGE, response = MessageDto.class)
        }
    )
    @GetMapping("/public/message")
    public MessageDto tellSomething() {
        return new MessageDto("Subscribe to PewDiePie...!!!");
    }

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
