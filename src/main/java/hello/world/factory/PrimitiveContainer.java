package hello.world.factory;

import jakarta.inject.Named;
import jakarta.inject.Singleton;

@Singleton
public class PrimitiveContainer {
    private final int primitive1;

    public PrimitiveContainer(@Named("primitive1") int primitive1) {
        this.primitive1 = primitive1;
    }

    public int getPrimitive1() {
        return primitive1;
    }
}
