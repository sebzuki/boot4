package com.demo.boot4;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.retry.RetryException;
import org.springframework.core.retry.RetryPolicy;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;

import java.time.Duration;
import java.time.Instant;

@Log4j2
@Service
public class RetryService {
    private int attempt = 0;

    @ConcurrencyLimit(2)
    public String test() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("ConcurrencyLimit !"+ Instant.now());
        return "ça y est !";
    }

    // By default, the method invocation will be retried for any exception thrown:
    // with at most 3 retry attempts after an initial failure, and a delay of 1 second between attempts.
    @Retryable
    public String test1() {
        attemp();
        return "ça y est !";
    }

    @Retryable(
            includes = IllegalArgumentException.class,
            maxAttempts = 5, // string aussi pour les param
            delay = 1000,
            maxDelay = 5000
    )
    public String test2() {
        attemp();
        return "ça y est !";
    }

    // ajout d'un RetryListener ppossible pour gérer plus finement les erreurs
    public String test3() throws RetryException {
        var retryPolicy = RetryPolicy.builder()
                .includes(IllegalArgumentException.class)
                .maxAttempts(5)
                .delay(Duration.ofMillis(1000))
                .maxDelay(Duration.ofSeconds(5))
                .build();

        var retryTemplate = new RetryTemplate(retryPolicy);
        return retryTemplate.execute(() -> {
            attemp();
            return "ça y est !";
        });
    }

    public String json() {
        JsonMapper mapper = new JsonMapper();
//        return mapper.writer().withDefaultPrettyPrinter().writeValueAsString(new Person("Seb", "IDF", 37));
        return mapper.writer().withDefaultPrettyPrinter().writeValueAsString(new Person().setNom("Seb").setAge(37).setZone("IDF"));
    }

    private void attemp() {
        attempt++;
        log.info("Tentative numéro " + attempt);
        if (attempt < 4) {
            throw new IllegalArgumentException("Erreur temporaire");
        }
    }
}
