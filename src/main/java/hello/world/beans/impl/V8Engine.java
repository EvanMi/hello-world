package hello.world.beans.impl;

import hello.world.beans.Engine;
import io.micronaut.context.annotation.Bean;
import jakarta.inject.Singleton;

@Singleton
@Bean(typed = Engine.class)
public class V8Engine implements Engine {
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
