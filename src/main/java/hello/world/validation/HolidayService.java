package hello.world.validation;

import jakarta.inject.Singleton;

import javax.validation.constraints.NotBlank;
import java.time.Duration;

@Singleton
public class HolidayService {

    public String startHoliday(@NotBlank String person,
                               @DurationPattern String duration) {
        final Duration d = Duration.parse(duration);
        return "Person " + person + " is off on holiday for " + d.toMinutes() + " minutes";
    }

    public String startHoliday(@DurationPattern String fromDuration, @DurationPattern String toDuration, @NotBlank String person
    ) {
        final Duration d = Duration.parse(fromDuration);
        final Duration e = Duration.parse(toDuration);
        return "Person " + person + " is off on holiday from " + d + " to " + e;
    }
}
