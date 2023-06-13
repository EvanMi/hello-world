package hello.world.aop;

import io.micronaut.context.ApplicationContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NotNullExampleTest {


    @Test
    public void testNotNull() {
        try (ApplicationContext applicationContext = ApplicationContext.run()) {
            NotNullExample exampleBean = applicationContext.getBean(NotNullExample.class);
            IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                exampleBean.doWork(null);
            });
            Assertions.assertEquals(illegalArgumentException.getMessage(), "Null parameter [taskName] not allowed");
        }
    }
}
