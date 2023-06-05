package hello.world.application.event;

import io.micronaut.context.event.ApplicationEventPublisher;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class SampleEventEmitterBean {

    @Inject
    ApplicationEventPublisher<SampleEvent> eventPublisher;

    public void publishSampleEvent() {
        eventPublisher.publishEvent(new SampleEvent());
    }

}
