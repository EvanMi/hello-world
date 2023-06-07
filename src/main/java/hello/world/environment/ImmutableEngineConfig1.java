package hello.world.environment;
import io.micronaut.core.bind.annotation.Bindable;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.context.annotation.ConfigurationInject;
import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@ConfigurationProperties("my.engine") // (1)
public class ImmutableEngineConfig1 {
    private final String manufacturer;
    private final int cylinders;
    private final CrankShaft crankShaft;

    @ConfigurationInject // (2)
    public ImmutableEngineConfig1(
            @Bindable(defaultValue = "Ford") @NotBlank String manufacturer, // (3)
            @Min(1L) int cylinders, // (4)
            @NotNull CrankShaft crankShaft) {
        this.manufacturer = manufacturer;
        this.cylinders = cylinders;
        this.crankShaft = crankShaft;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getCylinders() {
        return cylinders;
    }

    public CrankShaft getCrankShaft() {
        return crankShaft;
    }

    @ConfigurationProperties("crank-shaft")
    public static class CrankShaft { // (5)
        private final Double rodLength; // (6)

        @ConfigurationInject
        public CrankShaft(@Nullable Double rodLength) {
            this.rodLength = rodLength;
        }

        public Optional<Double> getRodLength() {
            return Optional.ofNullable(rodLength);
        }
    }
}
