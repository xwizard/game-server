package com.example.game.application;

import com.example.game.application.session.SessionService;
import com.example.game.application.session.SessionServiceImpl;
import com.example.game.core.repository.MemorySessionRepository;
import com.example.game.core.repository.SessionRepository;
import com.example.game.http.RootHttpContextHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Game server application.
 */
public class GameServerApplication implements Application {
  private ScheduledExecutorService scheduler;
  private SessionRepository sessionRepository;
  private SessionService sessionService;

  @Override
  public void run() {
    scheduler = new ScheduledThreadPoolExecutor(50);
    sessionRepository = new MemorySessionRepository();
    sessionService = new SessionServiceImpl(sessionRepository, scheduler);

    try {
      HttpServer httpServer = HttpServer.create();
      httpServer.bind(new InetSocketAddress(8080), 500);
      httpServer.createContext("/", new RootHttpContextHandler());
      httpServer.setExecutor(scheduler);
      httpServer.start();
    } catch (IOException e) {
      e.printStackTrace();
      shutdown();
      System.exit(1);
    }
  }

  @Override
  public void shutdown() {
    scheduler.shutdown();
  }
}
