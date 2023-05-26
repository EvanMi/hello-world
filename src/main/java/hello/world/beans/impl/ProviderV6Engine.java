package hello.world.beans.impl;

import hello.world.beans.GenericEngine;
import jakarta.inject.Singleton;

@Singleton
public class ProviderV6Engine implements GenericEngine<V6Provider> {  // (1)
    @Override
    public V6Provider getCylinderProvider() {
        return new V6Provider();
    }
}
