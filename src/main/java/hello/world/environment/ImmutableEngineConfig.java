package hello.world.environment;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.bind.annotation.Bindable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@ConfigurationProperties("my.engine")
public interface ImmutableEngineConfig {
    @Bindable(defaultValue = "Ford") // (2)
    @NotBlank // (3)
    String getManufacturer();

    @Min(1L)
    int getCylinders();

    @NotNull
    CrankShaft getCrankShaft(); // (4)

    @ConfigurationProperties("crank-shaft")
    interface CrankShaft { // (5)
        Optional<Double> getRodLength(); // (6)
    }
}
