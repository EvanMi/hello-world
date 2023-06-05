package hello.world.validation;

import io.micronaut.context.annotation.Factory;
import io.micronaut.validation.validator.constraints.ConstraintValidator;

import jakarta.inject.Singleton;

@Factory
public class MyValidatorFactory {

    @Singleton
    ConstraintValidator<DurationPattern, CharSequence> durationPatternValidator() {
        return (value, annotationMetadata, context) -> {
            context.messageTemplate("invalid duration ({validatedValue}), additional custom message"); // (1)
            return value == null || value.toString().matches("^PT?[\\d]+[SMHD]{1}$");
        };
    }
}
