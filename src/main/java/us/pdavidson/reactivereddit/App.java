package us.pdavidson.reactivereddit;

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
 *
 */
public class App 
{
    private int port = 8080;

    public static void main( String[] args )
    {
        new App().createServer().startAndWait();
    }


    public HttpServer<ByteBuf, ByteBuf> createServer() {
        HttpServer<ByteBuf, ByteBuf> httpServer = RxNetty.newHttpServerBuilder(port, new RequestHandler<ByteBuf, ByteBuf>() {
            @Override
            public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
                response.writeString("Welcome!!");
                return response.close();
//                return getObservable(response);
            }
        }).pipelineConfigurator(PipelineConfigurators.<ByteBuf, ByteBuf>httpServerConfigurator()).build();
        System.out.println("Server Started on 8080");

        return httpServer;
    }

//    private Observable<Void> getObservable(HttpServerResponse<ByteBuf> response) {
//        return null;
//    }
}
