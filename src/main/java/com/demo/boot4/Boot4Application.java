package com.demo.boot4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;

/*
https://spring.io/blog/2025/09/09/core-spring-resilience-features
https://www.baeldung.com/spring-boot-4-spring-framework-7
https://docs.spring.io/spring-boot/reference/actuator/observability.html

Because of the wide adoption in the industry at the moment, Java 17 is still the minimum requirement.
Java 21 and Java 25 are strongly recommended to take advantage of new JVM features like virtual threads.
We can find an official statement in the Spring Blog (https://spring.io/blog/2024/10/01/from-spring-framework-6-2-to-7-0#comment-6561729052)

Deprecations and Removals
    With modernization comes cleanup:
        javax.* packages are gone — only Jakarta EE 11 is supported.
        Jackson 2.x support is dropped; Spring 7 expects Jackson 3.x.
        Spring JCL (logging bridge) is removed in favor of Apache Commons Logging.
        JUnit 4 support is deprecated — use JUnit 5 exclusively.
    If we still rely on these older APIs, migration should be part of our upgrade plan.
 */

@SpringBootApplication
@EnableResilientMethods
// on ne retire pas les "public" "static" ici
public class Boot4Application {
    public static void main(String[] args) {
        SpringApplication.run(Boot4Application.class, args);
    }
}
