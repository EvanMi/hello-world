package hello.world.environment;

import hello.world.beans.Engine;
import io.micronaut.context.annotation.Value;
import jakarta.inject.Singleton;

@Singleton
public class EnvEngine implements Engine {
    @Value("${my.engine.cylinders:6}")
    protected int cylinders;

    @Override
    public int getCylinders() {
        return cylinders;
    }

    @Override
    public String start() {
        return cylinders + " EnvEngine start !!";
    }
}
