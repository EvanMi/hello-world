package hello.world.http.server;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller("/produces")
public class ProducesController {

    @Get // (1)
    public HttpResponse<?> index() {
        return HttpResponse.ok().body("{\"msg\":\"This is JSON\"}");
    }

    @Produces(MediaType.TEXT_HTML)
    @Get("/html") // (2)
    public String html() {
        return "<html><title><h1>HTML</h1></title><body></body></html>";
    }

    @Get(value = "/xml", produces = MediaType.TEXT_XML) // (3)
    public String xml() {
        return "<html><title><h1>XML</h1></title><body></body></html>";
    }
}