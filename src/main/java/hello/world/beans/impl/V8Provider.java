package hello.world.beans.impl;

import hello.world.beans.CylinderProvider;

public class V8Provider implements CylinderProvider {
    @Override
    public int getCylinders() {
        return 8;
    }
}
