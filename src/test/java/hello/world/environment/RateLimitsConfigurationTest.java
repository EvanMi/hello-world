package hello.world.environment;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.PropertySource;
import io.micronaut.inject.qualifiers.Qualifiers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateLimitsConfigurationTest {
    private static ApplicationContext ctx;

    @BeforeAll
    public static void setupCtx() {

        ctx = ApplicationContext.run(PropertySource.of(
                "test",
                Map.of(
                        "ratelimits", List.of(
                                Map.of("period", "22s", "limit", "22"),
                                Map.of("period", "33s", "limit", "33")
                        ))
        ));
    }

    @Test
    public void testDataSourceConfiguration() throws URISyntaxException {
        Collection<RateLimitsConfiguration> beansOfType = ctx.getBeansOfType(RateLimitsConfiguration.class);
        assertEquals(2, beansOfType.size()); // (1)

        RateLimitsConfiguration firstConfig = ctx.getBean(
                RateLimitsConfiguration.class,
                Qualifiers.byName("1") // (2)
        );

        assertEquals(
                Duration.ofSeconds(33),
                firstConfig.getPeriod()
        );
    }

    @AfterAll
    public static void teardownCtx() {
        if(ctx != null) {
            ctx.stop();
        }
    }
}
