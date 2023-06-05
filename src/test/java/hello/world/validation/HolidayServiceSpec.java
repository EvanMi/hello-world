package hello.world.validation;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;
import javax.validation.ConstraintViolation;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest
public class HolidayServiceSpec {

    @Inject
    HolidayService holidayService;

    @Test
    void testCustomValidator() {
        final ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () ->
                        holidayService.startHoliday("Fred", "junk") // (1)
                );

        assertEquals("startHoliday.duration: invalid duration (junk), additional custom message", exception.getMessage()); // (2)
    }


    @Test
    void testCustomAndDefaultValidator() {
        final ConstraintViolationException exception =
                assertThrows(ConstraintViolationException.class, () ->
                        holidayService.startHoliday( "fromDurationJunk", "toDurationJunk", "")
                );

        String notBlankValidated = exception.getConstraintViolations()
                .stream()
                .filter(constraintViolation -> Objects.equals(constraintViolation.getPropertyPath().toString(), "startHoliday.person"))
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .get();

        String fromDurationPatternValidated = exception.getConstraintViolations()
                .stream()
                .filter(constraintViolation -> Objects.equals(constraintViolation.getPropertyPath().toString(), "startHoliday.fromDuration"))
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .get();

        String toDurationPatternValidated = exception.getConstraintViolations()
                .stream()
                .filter(constraintViolation -> Objects.equals(constraintViolation.getPropertyPath().toString(), "startHoliday.toDuration"))
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .get();

        assertEquals("must not be blank", notBlankValidated);
        assertEquals("invalid duration (fromDurationJunk), additional custom message", fromDurationPatternValidated);
        assertEquals("invalid duration (toDurationJunk), additional custom message", toDurationPatternValidated);
    }
}
