package hello.world.aop;

import io.micronaut.aop.*;
import io.micronaut.core.annotation.Nullable;
import jakarta.inject.Singleton;

@Singleton
@InterceptorBean(Stub.class) // (1)
public class StubIntroduction implements MethodInterceptor<Object, Object> { // (2)

    @Nullable
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        return context.getValue( // (3)
                Stub.class,
                context.getReturnType().getType()
        ).orElse(null); // (4)
    }
}
