package com.example.game.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class RootHttpContextHandler implements HttpHandler {
  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    System.out.println(httpExchange.toString());
    httpExchange.sendResponseHeaders(200, 200);
    httpExchange.close();
  }
}
