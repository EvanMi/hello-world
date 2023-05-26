package hello.world.factory;

import hello.world.beans.Engine;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;

@Factory
public class EngineFactory {

    @Singleton
    Engine v12Engine(CrankShaft crankShaft) {
        return new V12Engine(crankShaft);
    }
}
