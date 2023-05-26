package hello.world.beans;

import io.micronaut.context.annotation.Any;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class AnyEngineHolder {
    private final Engine engine;

    @Inject
    public AnyEngineHolder(@Any Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }
}
