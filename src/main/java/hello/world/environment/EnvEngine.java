package hello.world.environment;

import hello.world.beans.Engine;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.convert.format.MapFormat;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.net.URL;
import java.util.Map;

@Singleton
public class EnvEngine implements Engine {
    @Value("${my.engine.cylinders:6}")
    protected int cylinders;

    @Value("10")
    int number;

    @Value("http://${my.host}:${my.port}")
    URL url;

    @Property(name = "my.engine.color")
    protected String color;


    @Property(name = "my.jpa.default")
    @MapFormat(transformation = MapFormat.MapTransformation.FLAT)
    protected Map<String, String> jpaProperties;

    private String manufacturer;

    public String getManufacturer() {
        return manufacturer;
    }

    @Inject
    public void setManufacturer(@Property(name = "my.engine.manufacturer") String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public int getCylinders() {
        return cylinders;
    }

    @Override
    public String start() {
        return "number: " + number + "cylinders: " + cylinders + " EnvEngine start !! , URL: " + url + ", manufacturer: " + manufacturer
                + ", color: " + color + ", properties: " + jpaProperties;
    }
}
