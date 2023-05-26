package hello.world.beans;

import io.micronaut.context.BeanProvider;
import io.micronaut.context.annotation.Any;
import jakarta.inject.Singleton;

@Singleton
public class AnyProviderEngineHolder {
    final BeanProvider<Engine> engineBeanProvider;

    public AnyProviderEngineHolder(@Any BeanProvider<Engine> engineBeanProvider) {
        this.engineBeanProvider = engineBeanProvider;
    }


    public void startALl() {
        engineBeanProvider.stream().map(Engine::start).forEach(System.out::println);
    }
}
