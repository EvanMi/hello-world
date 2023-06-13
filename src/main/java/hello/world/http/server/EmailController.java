package hello.world.http.server;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collections;

@Validated // (1)
@Controller("/email")
public class EmailController {

    @Get("/send")
    public HttpResponse<?> send(@NotBlank String recipient, // (2)
                                @NotBlank String subject) { // (2)
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }


    @Post("/send1")
    public HttpResponse send(@Body @Valid Email email) { // (2)
        return HttpResponse.ok(Collections.singletonMap("msg", "OK"));
    }
}
