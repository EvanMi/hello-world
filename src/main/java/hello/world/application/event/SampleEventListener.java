package hello.world.application.event;

import io.micronaut.context.event.ApplicationEventListener;
import jakarta.inject.Singleton;

@Singleton
public class SampleEventListener implements ApplicationEventListener<SampleEvent> {
    private int invocationCounter = 0;

    @Override
    public void onApplicationEvent(SampleEvent event) {
        invocationCounter++;
    }

    public int getInvocationCounter() {
        return invocationCounter;
    }
}

