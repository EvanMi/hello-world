package hello.world.beans.impl;

import hello.world.beans.Engine;
import jakarta.inject.Singleton;

@Singleton
public class V6Engine implements Engine {
    @Override
    public int getCylinders() {
        return 6;
    }

    @Override
    public String start() {
        return "Starting V6";
    }
}
