package com.example.game.http;

import com.example.game.application.ApplicationContext;
import com.sun.net.httpserver.HttpExchange;

public class HttpCommandFactoryImpl implements HttpCommandFactory {

  private final ApplicationContext applicationContext;

  public HttpCommandFactoryImpl(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Override
  public HttpCommand create(HttpExchange exchange) {
    URIWrapper uriWrapper = URIWrapper.of(exchange.getRequestURI());

    if (!uriWrapper.getId().isPresent()) {
      return InvalidHttpCommand.of("Id is missing in request: " + exchange.getRequestURI());
    }
    if (!uriWrapper.getCommand().isPresent()) {
      return InvalidHttpCommand.of("Command is missing in request: " + exchange.getRequestURI());
    }

    switch (uriWrapper.getCommand().get()) {
      case "login":
        return LoginHttpCommand.of(applicationContext.sessionService(), uriWrapper.getId().get(), HttpMethod.GET);
      default:
        return InvalidHttpCommand.of("Invalid request: " + exchange.getRequestURI());
    }

  }
}
