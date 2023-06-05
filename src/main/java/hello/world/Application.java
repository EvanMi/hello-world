package hello.world;

import hello.world.annnotation.meta.BaseSqlRepository;
import hello.world.annnotation.meta.BookRepository;
import hello.world.beans.AnyEngineHolder;
import hello.world.beans.AnyProviderEngineHolder;
import hello.world.beans.impl.CylindersVehicle;
import hello.world.beans.impl.NamedVehicle;
import hello.world.beans.impl.ProviderVehicle;
import hello.world.beans.impl.QualifyVehicle;
import hello.world.beans.impl.Vehicle;
import hello.world.environment.EnvEngine;
import hello.world.factory.PrimitiveContainer;
import hello.world.introspected.May;
import hello.world.introspected.Person;
import hello.world.introspected.PersonPlus;
import hello.world.introspected.User;
import hello.world.scopes.WeatherService;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanProperty;
import io.micronaut.core.beans.BeanWrapper;
import io.micronaut.inject.qualifiers.Qualifiers;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.context.scope.refresh.RefreshEvent;

public class Application {

    public static void main(String[] args) {
        ApplicationContext run = Micronaut.run(Application.class, args);
        testEnvEngine(run);
    }

    public static void testEnvEngine(ApplicationContext run) {
        EnvEngine bean = run.getBean(EnvEngine.class);
        System.out.println(bean.start());
    }

    public static void testBookRepository(ApplicationContext run) {
        BaseSqlRepository bean = run.getBean(BaseSqlRepository.class, Qualifiers.byName("bookbook"));
        System.out.println(bean);
    }

    public static void testPersonBeanWrapper() {
        final BeanWrapper<Person> wrapper = BeanWrapper.getWrapper(new Person("Fred")); // (1)

        wrapper.setProperty("age", "20"); // (2)
        int newAge = wrapper.getRequiredProperty("age", int.class); // (3)

        System.out.println("Person's age now " + newAge);
    }

    public static void testIntrospectedMay() {
        BeanIntrospection<May> introspection = BeanIntrospection.getIntrospection(May.class);
        BeanProperty<May, String> nameProperty = introspection.getRequiredProperty("name", String.class);
        System.out.println(nameProperty.get(May.MAY1));

    }

    public static void testIntrospectedUser() {
        final BeanIntrospection<User> introspection = BeanIntrospection.getIntrospection(User.class);
        User user = introspection.instantiate("John");
        System.out.println(user.name + "-" + user.age);

        BeanProperty<User, Integer> ageProperty = introspection.getRequiredProperty("age", int.class);
        ageProperty.set(user, 19);
        System.out.println(user.age);
    }

    public static void testIntrospectedPersonPlus() {
        final BeanIntrospection<PersonPlus> introspection = BeanIntrospection.getIntrospection(PersonPlus.class); // (1)
        PersonPlus person = introspection.instantiate("John", 12); // (2)
        System.out.println("Hello " + person.name());

        final BeanProperty<PersonPlus, String> property = introspection.getRequiredProperty("name", String.class); // (3)
        property.set(person, "Fred"); // (4)
        String name = property.get(person); // (5)
        System.out.println("Hello " + name);
    }

    public static void testIntrospectedPerson() {
        final BeanIntrospection<Person> introspection = BeanIntrospection.getIntrospection(Person.class); // (1)
        Person person = introspection.instantiate("John"); // (2)
        System.out.println("Hello " + person.getName());

        final BeanProperty<Person, String> property = introspection.getRequiredProperty("name", String.class); // (3)
        property.set(person, "Fred"); // (4)
        String name = property.get(person); // (5)
        System.out.println("Hello " + name);
    }


    public static void testPrimitiveContainer(ApplicationContext run) {
        PrimitiveContainer bean = run.getBean(PrimitiveContainer.class);
        System.out.println(bean.getPrimitive1());

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