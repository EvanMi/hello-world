package hello.world.aop;

import io.micronaut.aop.*;
import io.micronaut.context.annotation.Prototype;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@AroundConstruct // (1)
@InterceptorBinding(kind = InterceptorKind.POST_CONSTRUCT) // (2)
@InterceptorBinding(kind = InterceptorKind.PRE_DESTROY) // (3)
@Prototype // (4)
public @interface ProductBean {
}
