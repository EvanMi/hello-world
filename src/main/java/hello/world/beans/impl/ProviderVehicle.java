package hello.world.beans.impl;

import hello.world.beans.Engine;
import hello.world.beans.GenericEngine;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
public class ProviderVehicle {
    private final GenericEngine<?> engine;

    @Inject
    public ProviderVehicle(GenericEngine<V8Provider> engine) {
        this.engine = engine;
    }

    public String start() {
        return engine.start();
    }
}
