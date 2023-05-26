package hello.world;

import hello.world.beans.Engine;
import hello.world.beans.impl.Vehicle;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class VehicleMockTest {

    @Requires(beans = VehicleMockTest.class)
    @Bean
    @Replaces(Engine.class)
    Engine mockEngine = new Engine() {
        @Override
        public int getCylinders() {
            return 0;
        }

        @Override
        public String start() {
            return "mock start";
        }
    };

    @Inject
    Vehicle vehicle;


    @Test
    public void testStartEngine() {
        final String result = vehicle.start();
        assertEquals("mock start", result);
    }
}
