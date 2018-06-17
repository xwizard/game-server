package com.example.game.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.logging.Logger;

public class RootHttpContextHandler implements HttpHandler {

  private final static Logger LOG = Logger.getLogger(RootHttpContextHandler.class.getCanonicalName());

  private final HttpCommandFactory httpCommandFactory;

  public RootHttpContextHandler(HttpCommandFactory httpCommandFactory) {
    this.httpCommandFactory = httpCommandFactory;
  }


  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    HttpCommand command = httpCommandFactory.create(httpExchange);

    LOG.fine("Handling command: " + command.toString());

    CommandResult result = command.execute();
    String responseBody = result.toResponse();

    httpExchange.getResponseHeaders().put("Content-Type", Collections.singletonList("text/plain; charset=utf-8"));

    httpExchange.sendResponseHeaders(result.httpStatus(), responseBody.length());
    PrintWriter printWriter = new PrintWriter(httpExchange.getResponseBody());
    printWriter.print(responseBody);
    printWriter.close();
  }
}
