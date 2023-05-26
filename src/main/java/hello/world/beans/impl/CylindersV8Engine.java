package hello.world.beans.impl;

import hello.world.beans.Engine;
import jakarta.inject.Singleton;

@Singleton
@Cylinders(value = 8, description = "8-cylinder V8 engine")
public class CylindersV8Engine implements Engine {
    private int cylinders = 8;

    @Override
    public int getCylinders() {
        return cylinders;
    }

    @Override
    public String start() {
        return "Starting V8";
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }
}
