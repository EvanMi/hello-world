package hello.world.annnotation.meta;

import io.micronaut.context.annotation.AliasFor;

public @interface Client {

    /**
     * @return The URL or service ID of the remote service
     */
    @AliasFor(member = "id")
    String value() default "";

    /**
     * @return The ID of the client
     */
    @AliasFor(member = "value")
    String id() default "";
}
