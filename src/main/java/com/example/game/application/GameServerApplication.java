package com.example.game.application;

import com.example.game.http.HttpCommandFactory;
import com.example.game.http.HttpCommandFactoryImpl;
import com.example.game.http.RootHttpContextHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Game server application.
 * All wiring comes here :(
 */
public class GameServerApplication implements Application {

  private HttpServer httpServer;
  private ScheduledExecutorService executor;

  @Override
  public void run() {


    ApplicationContext applicationContext = new GameServerApplicationContext(this);
    this.executor = applicationContext.executor();
    HttpCommandFactory httpCommandFactory = new HttpCommandFactoryImpl(applicationContext);
    HttpHandler httpHandler = new RootHttpContextHandler(httpCommandFactory);

    try {
      httpServer = HttpServer.create();
      httpServer.bind(new InetSocketAddress(8080), 500);
      httpServer.createContext("/", httpHandler);
      httpServer.setExecutor(applicationContext.executor());
      httpServer.start();
    } catch (IOException e) {
      e.printStackTrace();
      shutdown();
      System.exit(1);
    }
  }

  @Override
  public void shutdown() {
    httpServer.stop(10);
    executor.shutdown();
  }
}
