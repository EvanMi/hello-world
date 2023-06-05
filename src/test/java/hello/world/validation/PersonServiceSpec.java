package hello.world.validation;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.validation.validator.Validator;
import org.junit.jupiter.api.Test;

import jakarta.inject.Inject;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
class PersonServiceSpec {

    @Inject PersonService personService;

    @Inject
    Validator validator;

    @Test
    void testThatNameIsValidated() {
        final ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () ->
                        personService.sayHello("") // (1)
                );

        assertEquals("sayHello.name: must not be blank", exception.getMessage()); // (2)
    }

    @Test
    void testThatPersonIsValidWithValidator() {
        Person person = new Person();
        person.setName("");
        person.setAge(10);

        final Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);  // (1)

        assertEquals(2, constraintViolations.size()); // (2)
    }

    @Test
    void testThatPersonIsValid() {
        Person person = new Person();
        person.setName("");
        person.setAge(10);

        final ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () ->
                        personService.sayHello(person) // (1)
                );

        assertEquals(2, exception.getConstraintViolations().size()); // (2)
    }
}
