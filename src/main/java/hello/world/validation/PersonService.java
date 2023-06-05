package hello.world.validation;

import jakarta.inject.Singleton;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Singleton
public class PersonService {
    public void sayHello(@NotBlank String name) {
        System.out.println("Hello " + name);
    }


    public void sayHello(@Valid Person person) {
        System.out.println("Hello " + person.getName());
    }
}
