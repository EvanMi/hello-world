package hello.world.beans.impl;

import hello.world.beans.GenericEngine;
import jakarta.inject.Singleton;

@Singleton
public class ProviderV8Engine implements GenericEngine<V8Provider> {  // (1)
    @Override
    public V8Provider getCylinderProvider() {
        return new V8Provider();
    }
}
