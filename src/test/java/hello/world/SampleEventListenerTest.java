package hello.world;

import hello.world.application.event.SampleEventEmitterBean;
import hello.world.application.event.SampleEventListener;
import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SampleEventListenerTest {

    @Test
    public void testEventListenerIsNotified() {
        try (ApplicationContext context = ApplicationContext.run()) {
            SampleEventEmitterBean emitter = context.getBean(SampleEventEmitterBean.class);
            SampleEventListener listener = context.getBean(SampleEventListener.class);
            assertEquals(0, listener.getInvocationCounter());
            emitter.publishSampleEvent();
            assertEquals(1, listener.getInvocationCounter());
        }
    }
}
