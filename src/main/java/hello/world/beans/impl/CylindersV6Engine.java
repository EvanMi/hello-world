package hello.world.beans.impl;

import hello.world.beans.Engine;
import jakarta.inject.Singleton;

@Singleton
@Cylinders(value = 6, description = "6-cylinder V6 engine")
public class CylindersV6Engine implements Engine {
    @Override
    public int getCylinders() {
        return 6;
    }

    @Override
    public String start() {
        return "Starting V6";
    }
}
