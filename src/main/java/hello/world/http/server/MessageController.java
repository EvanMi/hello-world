package hello.world.http.server;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import io.micronaut.core.async.annotation.SingleResult;

@Controller("/receive")
public class MessageController {

    @Post(value = "/echo-publisher", consumes = MediaType.TEXT_PLAIN) // (1)
    @SingleResult
    Publisher<HttpResponse<String>> echoFlow(@Body Publisher<String> text) { //(2)
        return Flux.from(text)
                .collect(StringBuffer::new, StringBuffer::append) // (3)
                .map(buffer -> HttpResponse.ok(buffer.toString()));
    }

}
