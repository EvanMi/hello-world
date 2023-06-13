package hello.world.http.server.file;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.http.multipart.CompletedPart;
import io.micronaut.http.server.multipart.MultipartBody;
import io.micronaut.http.server.types.files.StreamedFile;
import io.micronaut.http.server.types.files.SystemFile;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Mono;
import io.micronaut.core.async.annotation.SingleResult;

import java.io.File;
import java.io.InputStream;

import static io.micronaut.http.MediaType.MULTIPART_FORM_DATA;
import static io.micronaut.http.MediaType.TEXT_PLAIN;

@Controller("/upload")
public class WholeBodyUploadController {

    @Post(value = "/whole-body", consumes = MULTIPART_FORM_DATA, produces = TEXT_PLAIN)
    @SingleResult
    public Publisher<String> uploadBytes(@Body MultipartBody body) {

        return Mono.create(emitter -> {
            body.subscribe(new Subscriber<CompletedPart>() {
                private Subscription s;

                @Override
                public void onSubscribe(Subscription s) {
                    this.s = s;
                    s.request(1);
                }

                @Override
                public void onNext(CompletedPart completedPart) {
                    String partName = completedPart.getName();
                    if (completedPart instanceof CompletedFileUpload) {
                        String originalFileName = ((CompletedFileUpload) completedPart).getFilename();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    emitter.error(t);
                }

                @Override
                public void onComplete() {
                    emitter.success("Uploaded");
                }
            });
        });
    }


    /**
     * 直接返回File就可以了，但是如果想过更多的控制，就使用SystemFile
     */
    @Get
    public SystemFile download0() {
        File file = null;
        return new SystemFile(file).attach("myfile.txt");
        // or new SystemFile(file, MediaType.TEXT_HTML_TYPE)
    }


    @Get
    public StreamedFile download1() {
        InputStream inputStream = null;
        return new StreamedFile(inputStream, MediaType.TEXT_PLAIN_TYPE);
        // An attach(String filename) method is also available to set the Content-Disposition
    }


}
