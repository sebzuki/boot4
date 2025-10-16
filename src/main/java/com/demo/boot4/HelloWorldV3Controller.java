package com.demo.boot4;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/hello", version = "3")
public class HelloWorldV3Controller {
    private final ObservationRegistry observationRegistry;

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {
        return Observation.createNotStarted("doSomething", this.observationRegistry)
                .lowCardinalityKeyValue("locale", "en-US")
                .highCardinalityKeyValue("userId", "42")
                .observe(() -> {
                    return "Hey World";
                });
    }
}