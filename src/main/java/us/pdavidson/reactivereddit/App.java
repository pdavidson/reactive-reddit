package us.pdavidson.reactivereddit;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.pipeline.PipelineConfigurators;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import rx.Observable;

/**
 * Hello world!
 */
public class App {
    private int port = 8080;
    private Gson serializer = new Gson();

    public static void main(String[] args) {
        new App().createServer().startAndWait();
    }


    public HttpServer<ByteBuf, ByteBuf> createServer() {
        HttpServer<ByteBuf, ByteBuf> httpServer = RxNetty.newHttpServerBuilder(port, new RequestHandler<ByteBuf, ByteBuf>() {
            @Override
            public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {

                return new HotSubredditCommand(("programming")).observe()
                        .flatMap(listingBean -> {
                            System.out.println("Serializing to json");
                            return Observable.just(serializer.toJson(listingBean));
                        }).flatMap(msg -> {
                            System.out.println("Writing out to the response");

                            response.writeString(msg);
                            response.getHeaders().add("Content-Type", "application/json");
                            System.out.println("Wrote " + msg + " out to the response");
                            return response.close();
                        });


// Works
//                return Observable.just("hello world").flatMap(msg ->{
//                    response.writeString(msg);
//                    return response.close(false);
//                });

// Works
//                response.writeString("hello");
//                return response.close(false);
            }
        })

                .pipelineConfigurator(PipelineConfigurators.<ByteBuf, ByteBuf>httpServerConfigurator())
                .build();
        System.out.println("Server Started on 8080");

        return httpServer;
    }

}
