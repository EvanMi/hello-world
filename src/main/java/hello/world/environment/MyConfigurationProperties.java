package hello.world.environment;

import io.micronaut.context.annotation.ConfigurationProperties;

import java.time.LocalDate;

@ConfigurationProperties(MyConfigurationProperties.PREFIX)
public class MyConfigurationProperties {

    public static final String PREFIX = "myapp";

    protected LocalDate updatedAt;

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
}
