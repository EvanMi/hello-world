package hello.world.introspected;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class Vehicle {

    private final String make;
    private final String model;
    private final int axles;

    public Vehicle(String make, String model) {
        this(make, model, 2);
    }

    @Creator // (1)
    public Vehicle(String make, String model, int axles) {
        this.make = make;
        this.model = model;
        this.axles = axles;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getAxles() {
        return axles;
    }
}
