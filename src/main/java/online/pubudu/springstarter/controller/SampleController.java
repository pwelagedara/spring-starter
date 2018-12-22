package online.pubudu.springstarter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* Created by pubudu welagedara on 12/17/18.
* */
@RestController
public class SampleController {

    @GetMapping("/public/message")
    public String tellSomething() {
        return "Subscribe to PewDiePie...!!!";
    }

    @GetMapping("/protected/message")
    public String tellASecret() {
        return "Unsubscribe from T- Series...!!!";
    }

}
