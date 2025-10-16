package com.demo.boot4;

import lombok.AllArgsConstructor;
import org.springframework.core.retry.RetryException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Then, we need to configure the mapping strategy, which can be one of:

    path-based mapping (e.g. /api/v1/hello vs. /api/v2/hello)
    query-parameter-based (e.g. /hello?version=1 vs. /hello?version=2)
    request-header-based (e.g. X-API-Version: 1 vs. X-API-Version: 2)
    mediatype-header-based (e.g. Accept: application/json; version=1 vs. Accept: application/json; version=2)

 */

@AllArgsConstructor
@RestController
@RequestMapping("/hello")
public class HelloWorldController {
    private final RetryService retryService;

    @GetMapping(version = "1.0")
    public String sayHelloV1() {
        return "Hello World";
    }

    @GetMapping(version = "2.0")
    public String sayHelloV2() {
        return "Hi World";
    }

    @GetMapping("concurent")
    public String concurent() {
        return retryService.test();
    }

    @GetMapping(version = "1.0", value = "retry")
    public String retry() {
        return retryService.test1();
    }

    @GetMapping(version = "2.0",  value ="json")
    public String json() {
        return retryService.json();
    }
}