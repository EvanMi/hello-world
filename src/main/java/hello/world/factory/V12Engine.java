package hello.world.factory;

import hello.world.beans.Engine;

public class V12Engine implements Engine {
    private final int cylinders = 12;
    private final CrankShaft crankShaft;

    public V12Engine(CrankShaft crankShaft) {
        this.crankShaft = crankShaft;
    }


    @Override
    public int getCylinders() {
        return cylinders;
    }

    @Override
    public String start() {
        return "Starting V12";
    }
}
