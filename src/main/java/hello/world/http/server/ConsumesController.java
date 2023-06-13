package hello.world.http.server;

import com.fasterxml.jackson.core.JsonParseException;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;

@Controller("/consumes")
public class ConsumesController {

    @Post // (1) By default, a controller action consumes request with Content-Type of type application/json.
    public HttpResponse<?> index() {
        return HttpResponse.ok();
    }

    @Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON}) // (2)
    //The @Consumes annotation takes a String[] of supported media types for an incoming request.
    @Post("/multiple")
    public HttpResponse multipleConsumes() {
        return HttpResponse.ok();
    }

    @Post(value = "/member", consumes = MediaType.TEXT_PLAIN) // (3)
    // Content types can also be specified with the consumes member of the method annotation.
    public HttpResponse consumesMember() {
        return HttpResponse.ok();
    }

    @Error
    public HttpResponse<JsonError> jsonError(HttpRequest request, JsonParseException e) { // (1)
        JsonError error = new JsonError("Invalid JSON: " + e.getMessage()) // (2)
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>status(HttpStatus.BAD_REQUEST, "Fix Your JSON")
                .body(error); // (3)
    }

    @Error(status = HttpStatus.NOT_FOUND)
    public HttpResponse<JsonError> notFound(HttpRequest request) { // (1)
        JsonError error = new JsonError("Person Not Found") // (2)
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>notFound()
                .body(error); // (3)
    }

    @Error(global = true) // (1)
    public HttpResponse<JsonError> error(HttpRequest request, Throwable e) {
        JsonError error = new JsonError("Bad Things Happened: " + e.getMessage()) // (2)
                .link(Link.SELF, Link.of(request.getUri()));

        return HttpResponse.<JsonError>serverError()
                .body(error); // (3)
    }

}
