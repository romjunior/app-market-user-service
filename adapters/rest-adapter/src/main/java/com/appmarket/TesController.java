package com.appmarket;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesController {

    @GetMapping(path = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public Test getTest() {
        return new Test("I'm a test");
    }

}
