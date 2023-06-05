package hello.world.environment;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.Environment;
import io.micronaut.context.env.PropertySource;
import io.micronaut.core.util.CollectionUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExternalizedPropertyTest {


    @Test
    public void testExternalizedProperty() {
        ApplicationContext applicationContext = ApplicationContext.run(
                PropertySource.of(
                        "test",
                        CollectionUtils.mapOf(
                                "micronaut.server.host", "foo",
                                "micronaut.server.port", 8080
                        )
                ),
                "test", "android");
        Environment environment = applicationContext.getEnvironment();

        assertEquals(
                "foo",
                environment.getProperty("micronaut.server.host", String.class).orElse("localhost")
        );
    }
}
