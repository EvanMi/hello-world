package hello.world.environment;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.PropertySource;
import io.micronaut.inject.qualifiers.Qualifiers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataSourceConfigurationTest {
    private static ApplicationContext ctx;

    @BeforeAll
    public static void setupCtx() {
        ctx = ApplicationContext.run(PropertySource.of(
                "test",
                Map.of(
                        "test.datasource.one.url", "jdbc:mysql://localhost/one",
                        "test.datasource.two.url", "jdbc:mysql://localhost/two")
        ));
    }

    @Test
    public void testDataSourceConfiguration() throws URISyntaxException {
        Collection<DataSourceConfiguration> beansOfType = ctx.getBeansOfType(DataSourceConfiguration.class);
        assertEquals(2, beansOfType.size()); // (1)

        DataSourceConfiguration firstConfig = ctx.getBean(
                DataSourceConfiguration.class,
                Qualifiers.byName("one") // (2)
        );

        assertEquals(
                new URI("jdbc:mysql://localhost/one"),
                firstConfig.getUrl()
        );
    }

    @AfterAll
    public static void teardownCtx() {
        if(ctx != null) {
            ctx.stop();
        }
    }
}
