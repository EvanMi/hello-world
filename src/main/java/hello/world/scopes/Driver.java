package hello.world.scopes;

import io.micronaut.context.annotation.DefaultScope;
import io.micronaut.context.annotation.Requires;

import jakarta.inject.Singleton;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Requires(classes = WeatherService.class)
@Singleton // 无法被覆盖
// @DefaultScope(Singleton.class) //可以被覆盖
@Documented
@Retention(RUNTIME)
public @interface Driver {
}
