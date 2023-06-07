package hello.world.environment;

import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

public class MyConfigurationTest {
    private static ApplicationContext ctx;

    @BeforeAll
    public static void setupCtx() {
        ctx = ApplicationContext.run(
                new LinkedHashMap<String, Object>() {{
                    put("myapp.updatedAt", // (1)
                            new LinkedHashMap<String, Integer>() {{
                                put("day", 28);
                                put("month", 10);
                                put("year", 1982);
                            }}
                    );
                }}
        );
    }


    @Test
    public void testMyConfigurationProperties() {
        MyConfigurationProperties bean = ctx.getBean(MyConfigurationProperties.class);
        System.out.println("the date is ---- : " + bean.getUpdatedAt());
    }

    @AfterAll
    public static void teardownCtx() {
        if(ctx != null) {
            ctx.stop();
        }
    }
}
