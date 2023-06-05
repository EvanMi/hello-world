package hello.world.validation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface TimeOff {
    @DurationPattern
    String duration();
}
