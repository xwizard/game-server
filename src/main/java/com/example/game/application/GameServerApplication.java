package com.example.game.application;

import com.example.game.http.HttpCommandFactory;
import com.example.game.http.HttpCommandFactoryImpl;
import com.example.game.http.RootHttpContextHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

/**
 * Game server application.
 * All wiring comes here :(
 */
public class GameServerApplication implements Application {

  private final static Logger LOG = Logger.getLogger(GameServerApplication.class.getCanonicalName());

  private HttpServer httpServer;
  private ScheduledExecutorService executor;

  @Override
  public void run() {
    LOG.info("Starting game server…");
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
    LOG.info("Game server started.");
  }

  @Override
  public void shutdown() {
    LOG.info("Shutting down…");
    httpServer.stop(1);
    executor.shutdownNow();
    LOG.info("Shutdown complete.");
  }
}
