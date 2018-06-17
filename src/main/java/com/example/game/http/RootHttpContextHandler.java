package com.example.game.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

public class RootHttpContextHandler implements HttpHandler {

  private final HttpCommandFactory httpCommandFactory;

  public RootHttpContextHandler(HttpCommandFactory httpCommandFactory) {
    this.httpCommandFactory = httpCommandFactory;
  }


  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    HttpCommand command = httpCommandFactory.create(httpExchange);
    CommandResult result = command.execute();
    String responseBody = result.toString();

    httpExchange.getResponseHeaders().put("Content-Type", Collections.singletonList("text/plain; charset=utf-8"));

    httpExchange.sendResponseHeaders(200, responseBody.length());
    PrintWriter printWriter = new PrintWriter(httpExchange.getResponseBody());
    printWriter.print(responseBody);
    printWriter.close();
  }
}
