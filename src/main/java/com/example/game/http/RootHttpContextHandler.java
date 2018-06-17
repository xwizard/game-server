package com.example.game.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class RootHttpContextHandler implements HttpHandler {

  private final HttpCommandFactory httpCommandFactory;

  public RootHttpContextHandler(HttpCommandFactory httpCommandFactory) {
    this.httpCommandFactory = httpCommandFactory;
  }


  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    System.out.println(httpExchange.toString());
    httpExchange.sendResponseHeaders(200, 200);
    httpExchange.close();
  }
}
