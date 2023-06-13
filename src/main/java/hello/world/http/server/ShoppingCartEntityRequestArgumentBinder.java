package hello.world.http.server;

import io.micronaut.core.convert.ArgumentConversionContext;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.bind.binders.TypedRequestArgumentBinder;
import io.micronaut.http.cookie.Cookie;
import io.micronaut.jackson.serialize.JacksonObjectSerializer;

import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class ShoppingCartEntityRequestArgumentBinder implements TypedRequestArgumentBinder<ShoppingCartEntity> {

    private final JacksonObjectSerializer objectSerializer;

    public ShoppingCartEntityRequestArgumentBinder(JacksonObjectSerializer objectSerializer) {
        this.objectSerializer = objectSerializer;
    }

    @Override
    public BindingResult<ShoppingCartEntity> bind(ArgumentConversionContext<ShoppingCartEntity> context,
                                            HttpRequest<?> source) { //(1)

        Cookie cookie = source.getCookies().get("shoppingCart");
        if (cookie == null) {
            return Optional::empty;
        }

        return () -> objectSerializer.deserialize( //(2)
                cookie.getValue().getBytes(),
                ShoppingCartEntity.class);
    }

    @Override
    public Argument<ShoppingCartEntity> argumentType() {
        return Argument.of(ShoppingCartEntity.class); //(3)
    }
}
