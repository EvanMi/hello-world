package hello.world.introspected;

import io.micronaut.core.annotation.Creator;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class Business {

    private final String name;

    private Business(String name) {
        this.name = name;
    }

    @Creator // (1)
    public static Business forName(String name) {
        return new Business(name);
    }

    public String getName() {
        return name;
    }
}