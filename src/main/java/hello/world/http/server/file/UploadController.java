package hello.world.http.server.file;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.StreamingFileUpload;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import io.micronaut.core.async.annotation.SingleResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static io.micronaut.http.HttpStatus.CONFLICT;
import static io.micronaut.http.MediaType.MULTIPART_FORM_DATA;
import static io.micronaut.http.MediaType.TEXT_PLAIN;

@Controller("/upload")
public class UploadController {

    @Post(value = "/", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN) // (1)
    @SingleResult
    public Publisher<HttpResponse<String>> upload0(StreamingFileUpload file) { // (2)

        File tempFile;
        try {
            tempFile = File.createTempFile(file.getFilename(), "temp");
        } catch (IOException e) {
            return Mono.error(e);
        }
        Publisher<Boolean> uploadPublisher = file.transferTo(tempFile); // (3)

        return Mono.from(uploadPublisher)  // (4)
                .map(success -> {
                    if (success) {
                        return HttpResponse.ok("Uploaded");
                    } else {
                        return HttpResponse.<String>status(CONFLICT)
                                .body("Upload Failed");
                    }
                });
    }

    @Post(value = "/outputStream", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN) // (1)
    @SingleResult
    public Mono<HttpResponse<String>> uploadOutputStream(StreamingFileUpload file) { // (2)

        OutputStream outputStream = new ByteArrayOutputStream(); // (3)

        Publisher<Boolean> uploadPublisher = file.transferTo(outputStream); // (4)

        return Mono.from(uploadPublisher)  // (5)
                .map(success -> {
                    if (success) {
                        return HttpResponse.ok("Uploaded");
                    } else {
                        return HttpResponse.<String>status(CONFLICT)
                                .body("Upload Failed");
                    }
                });
    }



    @Post(value = "/", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN) // (1)
    @SingleResult
    public Publisher<HttpResponse<String>> upload1(StreamingFileUpload file) { // (2)

        File tempFile;
        try {
            tempFile = File.createTempFile(file.getFilename(), "temp");
        } catch (IOException e) {
            return Mono.error(e);
        }
        Publisher<Boolean> uploadPublisher = file.transferTo(tempFile); // (3)

        return Mono.from(uploadPublisher)  // (4)
                .map(success -> {
                    if (success) {
                        return HttpResponse.ok("Uploaded");
                    } else {
                        return HttpResponse.<String>status(CONFLICT)
                                .body("Upload Failed");
                    }
                });
    }


    @Post(value = "/bytes", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN)
    public HttpResponse<String> uploadBytes(byte[] file, String fileName) {
        try {
            File tempFile = File.createTempFile(fileName, "temp");
            Path path = Paths.get(tempFile.getAbsolutePath());
            Files.write(path, file);
            return HttpResponse.ok("Uploaded");
        } catch (IOException e) {
            return HttpResponse.badRequest("Upload Failed");
        }
    }


    @Post(value = "/completed", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN)
    public HttpResponse<String> uploadCompleted(CompletedFileUpload file) {
        try {
            File tempFile = File.createTempFile(file.getFilename(), "temp");
            Path path = Paths.get(tempFile.getAbsolutePath());
            Files.write(path, file.getBytes()); //(3)
            return HttpResponse.ok("Uploaded");
        } catch (IOException e) {
            return HttpResponse.badRequest("Upload Failed");
        }
    }

}
