package hello.world.environment;

import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.ConversionService;
import io.micronaut.core.convert.TypeConverter;

import jakarta.inject.Singleton;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@Singleton
public class MapToLocalDateConverter implements TypeConverter<Map<?, ?>, LocalDate> { // (1)

    private final ConversionService<?>  conversionService;

    public MapToLocalDateConverter(ConversionService<?> conversionService) { // (2)
        this.conversionService = conversionService;
    }

    @Override
    public Optional<LocalDate> convert(Map<?, ?> propertyMap, Class<LocalDate> targetType, ConversionContext context) {
        Optional<Integer> day = conversionService.convert(propertyMap.get("day"), Integer.class);
        Optional<Integer> month = conversionService.convert(propertyMap.get("month"), Integer.class);
        Optional<Integer> year = conversionService.convert(propertyMap.get("year"), Integer.class);
        if (day.isPresent() && month.isPresent() && year.isPresent()) {
            try {
                return Optional.of(LocalDate.of(year.get(), month.get(), day.get())); // (3)
            } catch (DateTimeException e) {
                context.reject(propertyMap, e); // (4)
                return Optional.empty();
            }
        }

        return Optional.empty();
    }
}