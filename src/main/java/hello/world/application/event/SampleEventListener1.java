package hello.world.application.event;

import io.micronaut.context.event.ShutdownEvent;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Singleton;

@Singleton
public class SampleEventListener1 {
    private int invocationCounter = 0;

    @EventListener
    @Async
    public void onSampleEvent(SampleEvent event) {
        invocationCounter++;
    }

    @EventListener
    public void onStartupEvent(StartupEvent event) {
        // startup logic here
    }

    @EventListener
    public void onShutdownEvent(ShutdownEvent event) {
        // shutdown logic here
    }

    public int getInvocationCounter() {
        return invocationCounter;
    }
}
