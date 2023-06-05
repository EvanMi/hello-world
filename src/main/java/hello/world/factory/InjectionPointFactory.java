package hello.world.factory;

import hello.world.beans.Engine;
import hello.world.beans.impl.Cylinders;
import hello.world.beans.impl.V6Engine;
import hello.world.beans.impl.V8Engine;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.inject.InjectionPoint;

@Factory
public class InjectionPointFactory {

    @Prototype
    Engine v8Engine(InjectionPoint<?> injectionPoint) {
        final int cylinders = injectionPoint
                .getAnnotationMetadata()
                .intValue(Cylinders.class)
                .orElse(8);

        switch (cylinders) {
            case 6 : return new V6Engine();
            case 8 : return new V8Engine();
            default: throw new IllegalArgumentException("Unsupported number of cylinders specified: " + cylinders);
        }
    }
}
