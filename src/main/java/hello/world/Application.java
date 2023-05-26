package hello.world;

import hello.world.beans.AnyEngineHolder;
import hello.world.beans.AnyProviderEngineHolder;
import hello.world.beans.impl.CylindersVehicle;
import hello.world.beans.impl.NamedVehicle;
import hello.world.beans.impl.ProviderVehicle;
import hello.world.beans.impl.QualifyVehicle;
import hello.world.beans.impl.Vehicle;
import hello.world.scopes.WeatherService;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.context.scope.refresh.RefreshEvent;

public class Application {

    public static void main(String[] args) {
        ApplicationContext run = Micronaut.run(Application.class, args);
        testWeatherService(run);
    }


    public static void testWeatherService(ApplicationContext run) {
        WeatherService bean = run.getBean(WeatherService.class);
        System.out.println(bean.latestForecast());
        System.out.println(bean.latestForecast());
        run.publishEvent(new RefreshEvent());
        System.out.println(bean.latestForecast());
    }


    public static void pMain(String[] args) {
        Micronaut.build(args)
                //Setting eager init to true initializes all singletons
                .eagerInitSingletons(true)
                /*
                 * @ConfigurationReader beans such as @EachProperty or @ConfigurationProperties are singleton beans.
                 * To eagerly init configuration but keep other @Singleton-scoped bean creation lazy,
                 * use eagerInitConfiguration
                 */
                .eagerInitConfiguration(true)
                .mainClass(Application.class)
                .start();
    }
}