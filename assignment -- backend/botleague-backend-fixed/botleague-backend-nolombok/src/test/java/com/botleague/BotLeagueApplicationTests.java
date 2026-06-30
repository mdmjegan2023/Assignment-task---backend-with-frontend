package com.botleague;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Smoke test – verifies the Spring context loads successfully.
 * Requires the embedded MongoDB provided by flapdoodle on the test classpath.
 */
@SpringBootTest
@ActiveProfiles("test")
class BotLeagueApplicationTests {

    @Test
    void contextLoads() {
        // If the context fails to start, this test will fail automatically.
    }
}
