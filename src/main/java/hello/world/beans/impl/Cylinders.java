package hello.world.beans.impl;

import io.micronaut.context.annotation.NonBinding;
import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Cylinders {

    int value();

    @NonBinding
    String description() default "";
}
