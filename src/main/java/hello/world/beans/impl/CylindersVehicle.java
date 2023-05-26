package hello.world.beans.impl;

import hello.world.beans.Engine;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class CylindersVehicle {
    private final Engine engine;

    @Inject
    public CylindersVehicle(@Cylinders(8) Engine engine) {
        this.engine = engine;
    }

    public String start() {
        return engine.start();
    }
}
