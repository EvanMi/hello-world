package hello.world.http.server.version;

import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/versioned")
class VersionedController {

    @Version("1") // (1)
    @Get("/hello")
    String helloV1() {
        return "helloV1";
    }

    @Version("2") // (2)
    @Get("/hello")
    String helloV2() {
        return "helloV2";
    }

}
