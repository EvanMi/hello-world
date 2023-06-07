package hello.world.environment;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.annotation.AccessorsStyle;
import io.micronaut.core.bind.annotation.Bindable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@ConfigurationProperties("my.engine")
@AccessorsStyle(readPrefixes = "read")
public interface EngineConfigAccessors {

    @Bindable(defaultValue = "Ford")
    @NotBlank
    String readManufacturer();

    @Min(1L)
    int readCylinders();

    @NotNull
    CrankShaft readCrankShaft();

    @ConfigurationProperties("crank-shaft")
    @AccessorsStyle(readPrefixes = "read")
    interface CrankShaft {
        Optional<Double> readRodLength();
    }
}
