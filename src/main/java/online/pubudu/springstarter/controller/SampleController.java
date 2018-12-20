package online.pubudu.springstarter.controller;

import online.pubudu.springstarter.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by pubudu welagedara on 12/17/18.
 */
@RestController
public class SampleController {

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/public/message")
    public String tellSomething() {
        return jwtUtils.generateToken("hello", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
    }

    @GetMapping("/protected/api-key/message")
    public String tellASecret() {
        return "Unsubscribe from T- Series...!!!";
    }

    @GetMapping("/protected/jwt/message")
    public String tellAnotherSecret() {
        return "Unsubscribe from T- Series...!!!";
    }
}
