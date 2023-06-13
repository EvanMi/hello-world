package hello.world;

import hello.world.entity.PaginationCommand;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Status;
import io.micronaut.http.context.ServerRequestContext;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Controller("/hello")
public class HelloWorldController {

    @Get(produces = MediaType.TEXT_PLAIN)
    public String index() {
        return "Hello World";
    }


    /**
     * 这个方法请求的时候 需要指定 ?page=1&rows=2，就能够有效的把属性进行绑定了
     * @param paginationCommand 要绑定从参数
     * @return {@link HttpStatus}
     */
    @Get("/bookmarks/list{?paginationCommand*}")
    public HttpStatus list(@Valid @Nullable PaginationCommand paginationCommand) {
        System.out.println(paginationCommand);
        return HttpStatus.OK;
    }


    @Get("/hello") // (1)
    public HttpResponse<String> hello(HttpRequest<?> request) {
        String name = request.getParameters()
                .getFirst("name")
                .orElse("Nobody"); // (2)

        return HttpResponse.ok("Hello " + name + "!!")
                .header("X-My-Header", "Foo"); // (3)
    }


    @Get("/hello-static") // (1)
    public HttpResponse<String> helloStatic() {
        HttpRequest<?> request = ServerRequestContext.currentRequest() // (1)
                .orElseThrow(() -> new RuntimeException("No request present"));
        String name = request.getParameters()
                .getFirst("name")
                .orElse("Nobody");

        return HttpResponse.ok("Hello " + name + "!!")
                .header("X-My-Header", "Foo");
    }


    @Get("/hello-reactor")
    public Mono<HttpResponse<String>> helloReactor() {
        return Mono.deferContextual(ctx -> { // (1)
            HttpRequest<?> request = ctx.get(ServerRequestContext.KEY); // (2)
            String name = request.getParameters()
                    .getFirst("name")
                    .orElse("Nobody");

            return Mono.just(HttpResponse.ok("Hello " + name + "!!")
                    .header("X-My-Header", "Foo"));
        });
    }

    /**
     * status 开始
     */
    @Get(value = "/http-response", produces = MediaType.TEXT_PLAIN)
    public HttpResponse<?> httpResponse() {
        return HttpResponse.status(HttpStatus.CREATED).body("success");
    }

    @Status(HttpStatus.CREATED)
    @Get(produces = MediaType.TEXT_PLAIN)
    public String success() {
        return "success";
    }

    @Get("/http-status")
    @ExecuteOn(TaskExecutors.IO)
    public HttpStatus httpStatus() {
        return HttpStatus.CREATED;
    }
    /**
     * status 结束
     */
}
