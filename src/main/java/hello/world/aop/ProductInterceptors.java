package hello.world.aop;

import io.micronaut.aop.ConstructorInterceptor;
import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.context.annotation.Factory;

@Factory
public class ProductInterceptors {

    private final ProductService productService;

    public ProductInterceptors(ProductService productService) {
        this.productService = productService;
    }

    @InterceptorBean(ProductBean.class)
    public ConstructorInterceptor<Product> aroundConstruct() { // (1)
        return context -> {
            final Object[] parameterValues = context.getParameterValues(); // (2)
            final Object parameterValue = parameterValues[0];
            if (parameterValue == null || parameterValues[0].toString().isEmpty()) {
                throw new IllegalArgumentException("Invalid product name");
            }
            String productName = parameterValues[0].toString().toUpperCase();
            parameterValues[0] = productName;
            final Product product = context.proceed(); // (3)
            productService.addProduct(product);
            return product;
        };
    }


    @InterceptorBean(ProductBean.class) // (1)
    MethodInterceptor<Product, Object> aroundInvoke() {
        return context -> {
            final Product product = context.getTarget();
            switch (context.getKind()) {
                case POST_CONSTRUCT: // (2)
                    product.setActive(true);
                    return context.proceed();
                case PRE_DESTROY: // (3)
                    productService.removeProduct(product);
                    return context.proceed();
                default:
                    return context.proceed();
            }
        };
    }
}
