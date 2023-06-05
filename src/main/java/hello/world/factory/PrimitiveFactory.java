package hello.world.factory;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Named;

@Factory
public class PrimitiveFactory {

    @Bean
    @Named("primitive1")
    final int primitive1 = 1;

    @Bean
    @Named("Primitive2")
    final int primitive2 = 2;
}
