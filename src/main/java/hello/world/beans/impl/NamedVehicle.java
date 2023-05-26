package hello.world.beans.impl;

import hello.world.beans.Engine;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
public class NamedVehicle {
    private final Engine engine;

    @Inject
    public NamedVehicle(@Named("v6") Engine engine) {
        this.engine = engine;
    }

    public String start() {
        return engine.start();
    }
}
